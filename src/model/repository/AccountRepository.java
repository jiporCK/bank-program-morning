package model.repository;

import model.Account;
import model.db.DbConnection;
import model.dto.TransactionResponse;
import model.dto.TransferRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {

//    private Connection connection;
//
//    public AccountRepository(Connection connection) {
//        this.connection = connection;
//    }

    public void createAccount(Account account) throws SQLException {
        Connection connection = DbConnection.getInstance();
        String sql = """
                insert into accounts (owner_name, balance)
                values (?, ?)
                """;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, account.getOwnerName());
        ps.setDouble(2, account.getBalance());

        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Inserted successfully");
        } else {
            System.out.println("Failed to insert");
        }

        connection.close();
        ps.close();
    }

    public Account findById(Integer id) throws SQLException {
        if (!existsById(id)) {
            throw new SQLException("ID does not exist");
        }

        try (Connection conn = DbConnection.getInstance()) {
            String sql = """
                    select * from accounts
                    where account_id = ?
                    """;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return new Account(
                                rs.getInt("account_id"),
                                rs.getString("owner_name"),
                                rs.getDouble("balance")
                        );
                    }
                }
            }
        }
        return null;
    }

    public boolean existsById(Integer id) throws SQLException {
        try (Connection conn = DbConnection.getInstance()) {
            String sql = "select 1 from accounts where account_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next();
                }
            }
        }
    }

    public void transferMoney(TransferRequest request) throws SQLException {
        try (Connection conn = DbConnection.getInstance()) {
            conn.setAutoCommit(false); // start transaction

            try {
                // Deduct money from sender
                String sql = """
                        update accounts
                        set balance = balance - ?
                        where account_id = ?
                        """;
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setDouble(1, request.amount());
                    ps.setInt(2, request.fromAccountId());
                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected == 0) {
                        throw new SQLException("Error send money");
                    }
                }

                // Add money to receiver
                String sql2 = """
                        update accounts
                        set balance = balance + ?
                        where account_id = ?
                        """;
                try (PreparedStatement ps = conn.prepareStatement(sql2)) {
                    ps.setDouble(1, request.amount());
                    ps.setInt(2, request.toAccountId());
                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected == 0) {
                        throw new SQLException("Error receive money");
                    }
                }

                // Add transaction record
                String sql3 = """
                        insert into transactions (from_account, to_account, amount)
                        values (?, ?, ?)
                        """;
                try (PreparedStatement ps = conn.prepareStatement(sql3)) {
                    ps.setInt(1, request.fromAccountId());
                    ps.setInt(2, request.toAccountId());
                    ps.setDouble(3, request.amount());
                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected == 0) {
                        throw new SQLException("Error save transaction record");
                    }
                }
                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
            } finally {
                conn.setAutoCommit(true);
            }

        }
    }

    public List<TransactionResponse> viewTransactionDetails() throws SQLException {
        try (Connection conn = DbConnection.getInstance()) {
            String sql = """
                    select t.transaction_id as id,
                    sender.owner_name as sender_name,
                    receiver.owner_name as receiver_name,
                    t.amount as transfer_amount,
                    t.created_at as timestamp
                    from transactions t
                    join accounts sender
                    on t.from_account = sender.account_id
                    join accounts receiver
                    on t.to_account = receiver.account_id
                    """;
            try (PreparedStatement ps = conn.prepareStatement(sql);

                 ResultSet rs = ps.executeQuery()) {
                List<TransactionResponse> responses = new ArrayList<>();
                while (rs.next()) {
                    responses.add(
                            new TransactionResponse(
                                    rs.getInt("id"),
                                    rs.getString("sender_name"),
                                    rs.getString("receiver_name"),
                                    rs.getDouble("transfer_amount"),
                                    String.valueOf(
                                            rs.getTimestamp("timestamp")
                                    )
                            )
                    );
                }
                return responses;
            }
        }
    }

}

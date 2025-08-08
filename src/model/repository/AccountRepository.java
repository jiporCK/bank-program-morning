package model.repository;

import model.Account;
import model.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}

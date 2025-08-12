package view;

import model.Account;
import model.dto.TransactionResponse;
import model.dto.TransferRequest;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;
import java.util.Scanner;

public class AccountView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public Account showAccountCreation() {
        System.out.print("Enter name: ");
        String name = SCANNER.nextLine();

        System.out.print("Enter balance: ");
        Double balance = Double.parseDouble(SCANNER.nextLine());

        return new Account(name, balance);
    }

    public Integer showEnterId() {
        System.out.print("Enter an id: ");
        return Integer.parseInt(SCANNER.nextLine());
    }

    public void showAccountDetail(Account account) {
        Table table = new Table(
                3,
                BorderStyle.UNICODE_BOX_DOUBLE_BORDER);
        table.addCell("Account Detail   ", 3);
        table.addCell("Account ID  ");
        table.addCell(String.valueOf(account.getId()), 2);
        table.addCell("Owner Name  ");
        table.addCell(account.getOwnerName(), 2);
        table.addCell("Balance  ");
        table.addCell(String.valueOf(account.getBalance()), 2);

        System.out.println(table.render());
    }

    public TransferRequest showTransactionRequest() {
        System.out.print("Enter sender ID: ");
        int senderId = Integer.parseInt(SCANNER.nextLine());

        System.out.print("Enter receiver ID: ");
        int receiverId = Integer.parseInt(SCANNER.nextLine());

        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(SCANNER.nextLine());

        return new TransferRequest(senderId, receiverId, amount);
    }

    public void showTransactionsRecord(List<TransactionResponse> responses) {
        Table table = new Table(
                5,
                BorderStyle.UNICODE_BOX_DOUBLE_BORDER
        );
        table.addCell("ID   ");
        table.addCell("Sender Name   ");
        table.addCell("Receiver Name   ");
        table.addCell("Amount   ");
        table.addCell("Timestamp   ");
        responses.forEach(
                t -> {
                    table.addCell(String.valueOf(t.id()));
                    table.addCell(t.senderName());
                    table.addCell(t.receiverName());
                    table.addCell(String.valueOf(t.amount()));
                    table.addCell(t.timestamp());
                }
        );
        System.out.println(table.render());
    }


}

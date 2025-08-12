package view;

import model.Account;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.Table;

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

}

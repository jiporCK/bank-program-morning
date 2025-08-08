package view;

import model.Account;

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

}

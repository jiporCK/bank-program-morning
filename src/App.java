import controller.AccountController;
import model.repository.AccountRepository;
import model.service.AccountService;
import util.Singleton;
import view.AccountView;

import java.util.Scanner;

public class App {

    private static final AccountController controller = Singleton.getControllerInstance();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    1. Create account
                    2. Find account by id
                    3. Transfer money
                    4. View transaction details
                    0. Exit""");
            System.out.print("\uD83D\uDCA1 Enter an option: ");
            int op = Integer.parseInt(scanner.nextLine());

            if (op == 0) break;

            switch (op) {
                case 1 -> controller.createAccount();
                case 2 -> controller.findAccountById();
                case 3 -> controller.transferMoney();
                case 4 -> controller.showTransactionDetails();
                default -> System.out.println("Invalid option!");
            }

        }


    }

}




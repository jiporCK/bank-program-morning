import controller.AccountController;
import model.Account;
import model.db.DbConnection;
import model.repository.AccountRepository;
import model.service.AccountService;
import view.AccountView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class App {

    private static final AccountRepository repository = new AccountRepository(
//            DbConnection.getInstance()
    );
    private static final AccountService service = new AccountService(repository);
    private static final AccountView view = new AccountView();
    private static final AccountController controller = new AccountController(
            view, service
    );

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
                default -> System.out.println("Invalid option!");
            }

        }


    }

}




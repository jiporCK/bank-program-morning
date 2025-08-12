package controller;

import model.Account;
import model.dto.TransferRequest;
import model.service.AccountService;
import view.AccountView;

public class AccountController {

    private final AccountView view;
    private final AccountService service;

    public AccountController(AccountView view, AccountService service) {
        this.view = view;
        this.service = service;
    }

    public void createAccount() {
        Account newAccount = view.showAccountCreation();
        service.createAccount(newAccount);
    }

    public void findAccountById() {
        Integer id = view.showEnterId();
        Account account = service.findById(id);
        view.showAccountDetail(account);
    }

    public void transferMoney() {
        TransferRequest request = view.showTransactionRequest();
        service.transferMoney(request);
    }

    public void showTransactionDetails() {
        view.showTransactionsRecord(
                service.getTransactions()
        );
    }

}

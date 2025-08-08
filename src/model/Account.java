package model;

import model.repository.AccountRepository;

public class Account {

    private Integer id;
    private String ownerName;
    private Double balance;

    public Account(String ownerName, Double balance) {
        this.ownerName = ownerName;
        this.balance = balance;
    }


    public Account(Integer id, String ownerName, Double balance) {
        this.id = id;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", ownerName='" + ownerName + '\'' +
                ", balance=" + balance +
                '}';
    }

}

package hhami.test;

public class Customer {

    private int id;
    private String firstName;
    private String surname;
    private int balance;

    public Customer(int id, String firstName, String surname, int balance) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}

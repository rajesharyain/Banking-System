package banking;

/**
 * Abstract bank account.
 */
public abstract class Account implements AccountInterface {
    private AccountHolder accountHolder;
    private Long accountNumber;
    private int pin;
    private double balance;

    protected Account(AccountHolder accountHolder, Long accountNumber, int pin, double startingDeposit) {
        // TODO: complete the constructor
    }

    public AccountHolder getAccountHolder() {
        // TODO: complete the method
        throw new RuntimeException("TODO");
    }

    public boolean validatePin(int attemptedPin) {
        // TODO: complete the method
        throw new RuntimeException("TODO");
    }

    public double getBalance() {
        // TODO: complete the method
        throw new RuntimeException("TODO");
    }

    public Long getAccountNumber() {
        // TODO: complete the method
        throw new RuntimeException("TODO");
    }

    public void creditAccount(double amount) {
        // TODO: complete the method
    }

    public boolean debitAccount(double amount) {
        // TODO: complete the method
        throw new RuntimeException("TODO");
    }
}

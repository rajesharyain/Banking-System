package banking;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The Bank implementation.
 */
public class Bank implements BankInterface {
    private LinkedHashMap<Long, Account> accounts;

    public Bank() {
       this.accounts = new LinkedHashMap<Long, Account>();
    }

    private Account getAccount(Long accountNumber) {
        return accounts.get(accountNumber);
    }

    public Long openCommercialAccount(Company company, int pin, double startingDeposit) {
        Long accountNumber = generateAccountNumber();
        CommercialAccount commercialAccount = new CommercialAccount(company, accountNumber, pin, startingDeposit);
        accounts.put(accountNumber, commercialAccount);
        return accountNumber;
    }

    public Long openConsumerAccount(Person person, int pin, double startingDeposit) {
        Long accountNumber = generateAccountNumber();
        ConsumerAccount consumerAccount = new ConsumerAccount(person, accountNumber, pin, startingDeposit);
        accounts.put(accountNumber, consumerAccount);
        return accountNumber;
    }

    public double getBalance(Long accountNumber) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            return account.getBalance();
        }
        throw new IllegalArgumentException("Account not found");
    }

    public void credit(Long accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            account.creditAccount(amount);
        } else {
            throw new IllegalArgumentException("Account not found");
        }
    }

    public boolean debit(Long accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            return account.debitAccount(amount);
        }
        throw new IllegalArgumentException("Account not found");
    }

    public boolean authenticateUser(Long accountNumber, int pin) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            return account.validatePin(pin);
        }
        throw new IllegalArgumentException("Account not found");
    }
    
    public void addAuthorizedUser(Long accountNumber, Person authorizedPerson) {
        Account account = getAccount(accountNumber);
        if (account != null && account instanceof CommercialAccount) {
            CommercialAccount commercialAccount = (CommercialAccount) account;
            commercialAccount.addAuthorizedUser(authorizedPerson);
        } else {
            throw new IllegalArgumentException("Account not found or not a commercial account");
        }
    }

    public boolean checkAuthorizedUser(Long accountNumber, Person authorizedPerson) {
        Account account = getAccount(accountNumber);
        if (account != null && account instanceof CommercialAccount) {
            CommercialAccount commercialAccount = (CommercialAccount) account;
            return commercialAccount.isAuthorizedUser(authorizedPerson);
        }
        throw new IllegalArgumentException("Account not found or not a commercial account");
    }

    public Map<String, Double> getAverageBalanceReport() {
        double totalBalance = 0.0;
        int count = 0;

        for (Account account : accounts.values()) {
            totalBalance += account.getBalance();
            count++;
        }

        double averageBalance = totalBalance / count;

        Map<String, Double> report = new HashMap<>();
        report.put("Average Balance", averageBalance);
        report.put("Total Accounts", (double) count);

        return report;
    }
    private Long generateAccountNumber() {
        return (long) accounts.size() + 1;
    }
}

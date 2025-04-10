package Bank;

public class Account {
    private int accountId;
    private int customerId;
    private double balance;
    private AccountType accountType;

    public Account() {
        setAccountId(accountId);
        setAccountType(accountType);
    }
    public Account(int accountId, int customerId, AccountType accountType, double balance) {
        setAccountId(accountId);
        setCustomerId(customerId);
        setAccountType(accountType);
        setBalance(balance);
    }
    public int getAccountId() {
        return accountId;
    }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public AccountType getAccountType() {
        return accountType;
    }
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
    public void depositToAmount(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println(amount + " $ was deposited.. New balance: " + this.balance + " $");
        } else {
            System.out.println("Invalid amount! Please enter a positive amount.");
        }
    }
    public void withdrawToAmount(double amount) {
        if (amount > 0 && amount < balance) {
            balance -= amount;
            System.out.println(amount + " $ withdrew. New balance: " + balance + " $");
        } else {
            System.out.println("Invalid transaction! Insufficient balance or invalid amount.");
        }
    }
    @Override
    public String toString() {
        return super.toString() + ", Account Id       : " + accountId + ", Account Balance  : " + balance + ", Account Type     : " + accountType;
    }
}


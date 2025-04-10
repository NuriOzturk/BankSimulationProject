package Bank;

import java.util.*;

public class BankSimulation {
    private final Map<Integer, Customer> customers;
    private final List<Account> accounts;
    private final Scanner scanner;

    public BankSimulation() {
        customers = new HashMap<>();
        accounts = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    public Scanner getScanner() {
        return scanner;
    }
    public static void main(String[] args) {
        BankSimulation team4Bank = new BankSimulation();
        team4Bank.run();
    }
    public void run() {
        int choice;
        do {
            System.out.print("""
                    
                    ----- BANKING TRANSACTIONS -----
                    
                    1--> Customer Listing
                    2--> Adding New Customer
                    3--> Customer Transactions
                    0--> Exit
                    
                    Make your choice:\s""");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    listCustomers();
                    break;
                case 2:
                    addCustomer();
                    break;
                case 3:
                    customerOperationsMenu();
                    break;
                case 0:
                    System.out.println("Exit is in progress...");
                    break;
                default:
                    System.out.println("Invalid selection! Try again.");
                    break;
            }
        }
        while (choice != 0);
    }
    public void listCustomers() {
        System.out.println("\n----- CUSTOMERS -----");
        if (customers.isEmpty()) {
            System.out.println("There are no registered customers yet.");
        } else {
            for (Customer customer : customers.values()) {
                System.out.println(customer);
            }
        }
    }
    public void listCustomerAccounts(Customer customer) {
        System.out.println("----- " + customer.getCustomerId() + " - " + customer.getFirstName() + " " + customer.getLastName() + " HESAPLARI -----");
        boolean hasAccounts = false;
        for (Account account : accounts) {
            if (account != null && account.getCustomerId() == customer.getCustomerId()) {
                System.out.println(account.getAccountId() + " - " + account.getAccountType() + " - " + account.getBalance() + " TL");
                hasAccounts = true;
            }
        }
        System.out.println("===============================================");
        if (!hasAccounts) {
            System.out.println("The customer does not have an account opened..\n" + "===============================================");
        }
    }
    private static int customerIdCount = 1;
    public void addCustomer() {
        System.out.println("\n----- ADD NEW CUSTOMER -----");
        System.out.print("Enter customer firstname : ");
        String firstName = scanner.nextLine();
        System.out.print("Enter customer lastname  : ");
        String lastName = scanner.nextLine();
        System.out.print("Enter customer city      : ");
        String city = scanner.nextLine();
        int customerId = customerIdCount;
        customerIdCount++;
        Customer customer1 = new Customer(firstName, lastName, city);
        customer1.setCustomerId(customerId);
        customers.put(customerId, customer1);
        System.out.println("Customer added successfully. Customer ID: " + customer1.getCustomerId());
    }
    public void customerOperationsMenu() {
        int customerId;
        do {
            System.out.println("\n----- CUSTOMER TRANSACTIONS -----");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter Customer ID (0 exits): ");
            customerId = scanner.nextInt();
            scanner.nextLine();
            if (customerId != 0) {
                Customer customer = customers.get(customerId);
                if (customer != null) {
                    customerOperations(customer);
                } else {
                    System.out.println("Invalid Customer ID. Try again.");
                }
            }
        }
        while (customerId != 0);
    }
    public void customerOperations(Customer customer) {
        int choice;
        do {
            System.out.println("\n----- " + customer.getFirstName() + " " + customer.getLastName() + " Account Transactions -----");
            System.out.print("""
                    
                    1--> Open a New Account
                    2--> List Accounts
                    3--> Deposit Money
                    4--> Withdraw Money
                    5--> Check Balance
                    0--> Return to Main Menu
                    
                    Make your choice:\s""");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    openNewAccount(customer);
                    break;
                case 2:
                    listCustomerAccounts(customer);
                    break;
                case 3:
                    depositToAccount(customer);
                    break;
                case 4:
                    withdrawToAccount(customer);
                    break;
                case 5:
                    checkBalance(customer);
                    break;
                case 0:
                    System.out.println("Returning to the main menu...");
                    break;
                default:
                    System.out.println("Invalid selection! Try again.");
            }
        }
        while (choice != 0);
    }
    private static int accountCount = 1000;
    public void openNewAccount(Customer customer) {
        Account account = new Account();
        System.out.print("""
                
                *** OPEN NEW ACCOUNT ***
                
                1 --> CHECKING
                2 --> SAVINGS
                3 --> CREDIT
                
                Select Account Type:\s""");
        int choiceNewAccount = scanner.nextInt();
        scanner.nextLine();
        double initialBalance = 0.0;
        switch (choiceNewAccount) {
            case 1:
                account.setAccountType(AccountType.CHECKING);
                initialBalance = 0.0;
                break;
            case 2:
                account.setAccountType(AccountType.SAVINGS);
                initialBalance = 0.0;
                break;
            case 3:
                account.setAccountType(AccountType.CREDIT);
                initialBalance = 0.0;
                break;
            default:
                System.out.println("Invalid selection! New account opening process cancelled.");
                return;
        }
        account.setAccountId(++accountCount);
        System.out.println("Account opened successfully. Account Number: " + account.getAccountId());
        Account accountNew = new Account(accountCount, customer.getCustomerId(), account.getAccountType(), initialBalance);
        accounts.add(accountNew);
    }
    public void depositToAccount(Customer customer) {
        System.out.println("\n*** DEPOSIT MONEY ***");
        System.out.print("Enter Account Number: ");
        int accountCount = scanner.nextInt();
        scanner.nextLine();
        Account account = getAccountById(accountCount);
        if (account != null && account.getCustomerId() == customer.getCustomerId()) {
            System.out.print("Amount to be deposited: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            account.depositToAmount(amount);
        } else {
            System.out.println("Invalid Account Number!");
        }
    }
    public void withdrawToAccount(Customer customer) {
        System.out.println("\n*** WITHDRAW MONEY ***");
        System.out.print("Enter Account Number: ");
        int accountId = scanner.nextInt();
        scanner.nextLine();
        Account account = getAccountById(accountId);
        if (account != null && account.getCustomerId() == customer.getCustomerId()) {
            System.out.print("Amount to be withdrawn: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            account.withdrawToAmount(amount);
        } else {
            System.out.println("Invalid Account Number!");
        }
    }
    public void checkBalance(Customer customer) {
        System.out.println("\n----- BALANCE INQUIRY -----");
        System.out.print("Enter Account Number: ");
        int accountId = scanner.nextInt();
        scanner.nextLine();
        Account account = getAccountById(accountId);
        if (account != null && account.getCustomerId() == customer.getCustomerId()) {
            System.out.println("Current balance: " + account.getBalance() + " $");
        } else {
            System.out.println("Invalid Account Number or account is not yours! Transaction cancelled.");
        }
    }
    public Account getAccountById(int accountId) {
        for (Account account : accounts) {
            if (account.getAccountId() == accountId) {
                return account;
            }
        }
        return null;
    }
}


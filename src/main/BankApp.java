//package main;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.Scanner;
//
//public class BankApp {
//    Scanner sc = new Scanner(System.in);
//    Bank bank = new Bank();
//
//    public static void main(String[] args) {
//        BankApp bankApp = new BankApp();
//        bankApp.menu();
//    }
//
//    public void menu() {
//
//        int choice = 0;
//        do {
//            System.out.println("1.Create new account" +
//                    "\n2.Deposit \n3.Withdraw \n4.Transfer\n5.Transaction History");
//
//            choice = sc.nextInt();
//            if (choice < 0 || choice > 5) {
//                System.out.println("Invalid selection");
//            }
//            switch (choice) {
//                case 1:
//                    createAccount();
//                    break;
//                case 2:
//                    deposit();
//                    break;
//                case 3:
//                    withdrawal();
//                    break;
//                case 4:
//                    transfer();
//                    break;
//                case 5:
//                    transactionHistory();
//                    break;
//                default:
//                    System.out.println("Invalid option");
//            }
//            System.out.println("------------------------------");
//        }
//        while (choice != 6);
//
//    }
//
//    private void createAccount() {
//        String name, nic, address;
//        System.out.print("Enter your name : ");
//        name = sc.next();
//        System.out.print("Enter your NIC No : ");
//        nic = sc.next();
//        System.out.print("Enter your address : ");
//        address = sc.next();
//
//        Account accountNo = new Account();
//        Customer customer = new Customer(name, nic, address, accountNo);
//        bank.addCustomer(customer);
//        System.out.println(customer.toString());
//    }
//
//    private void deposit() {
//        int accountNo;
//        double amount;
//        String name;
//        System.out.print("Enter your name : ");
//        name = sc.next();
//        System.out.print("Enter your account No : ");
//        accountNo = sc.nextInt();
//        System.out.print("Enter amount : ");
//        amount = sc.nextDouble();
//        if (bank.getCustomerIndex(accountNo) < 0) {
//            System.out.println("Invalid account Number");
//        } else {
//            boolean b = bank.getCustomer(accountNo).getAccountNo().depositt(amount);
//            if (b == true) {
//                Transaction t = new Transaction();
//                LocalDateTime myDateObj = LocalDateTime.now();
//                Transaction t1 = new Transaction(t.getTransactionid(), accountNo, "Deposit", amount, myDateObj);
//                bank.addTransaction(t1);
//            }
//        }
//
//
//    }
//
//    private void withdrawal() {
//        int accountNo;
//        double amount;
//        String name;
//        System.out.print("Enter your name : ");
//        name = sc.next();
//        System.out.print("Enter your account No : ");
//        accountNo = sc.nextInt();
//        System.out.print("Enter amount : ");
//        amount = sc.nextDouble();
//        if (bank.getCustomerIndex(accountNo) < 0) {
//            System.out.println("Invalid account Number");
//        } else {
//            boolean a = bank.getCustomer(accountNo).getAccountNo().withdraw(amount);
//            if (a == true) {
//                Transaction t = new Transaction();
//                LocalDateTime myDateObj = LocalDateTime.now();
//                Transaction t1 = new Transaction(t.getTransactionid(), accountNo, "Withdrawal", amount, myDateObj);
//                bank.addTransaction(t1);
//            }
//
//        }
//    }
//
//
//    private void transfer() {
//        int fromAccountNo;
//        int toAccountNo;
//        double amount;
//        System.out.print("Enter your account number : ");
//        fromAccountNo = sc.nextInt();
//        System.out.print("Enter Transfer account number  : ");
//        toAccountNo = sc.nextInt();
//        System.out.print("Enter amount : ");
//        amount = sc.nextDouble();
//        if (bank.getCustomerIndex(fromAccountNo) < 0 || bank.getCustomerIndex(toAccountNo) < 0) {
//            System.out.println("Invalid account Number");
//        } else {
//            BigDecimal d1 = bank.getCustomer(fromAccountNo).getAccountNo().getBalance();
//            BigDecimal d2 = BigDecimal.valueOf(amount);
//            //double d = bank.getCustomer(fromAccountNo).getAccountNo().getBalance();
//            BigDecimal d3 = d1.max(d2);
//            if (d3 == d2) {
//                System.out.println("insufficient balance");
//            } else {
//                Transaction t = new Transaction();
//                LocalDateTime myDateObj = LocalDateTime.now();
//                Transaction t1 = new Transaction(t.getTransactionid(), fromAccountNo, "Transfer-Debit", amount, myDateObj);
//                bank.addTransaction(t1);
//                System.out.print("Sender ");
//                bank.getCustomer(fromAccountNo).getAccountNo().withdraw(amount);
//                Transaction t2 = new Transaction(t.getTransactionid(), toAccountNo, "Transfer-Credit", amount, myDateObj);
//                bank.addTransaction(t2);
//                System.out.print("Receiver ");
//                bank.getCustomer(toAccountNo).getAccountNo().depositt(amount);
//            }
//        }
//    }
//
//
//    private void transactionHistory() {
//        int accountNo;
//        String name;
//        System.out.print("Enter your name : ");
//        name = sc.next();
//        System.out.print("Enter your account No : ");
//        accountNo = sc.nextInt();
//        bank.getTransaction(accountNo);
//    }
//
//
//}

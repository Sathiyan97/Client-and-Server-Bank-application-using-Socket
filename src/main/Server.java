package main;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class Server {

    Bank banks = new Bank();


    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(6666);
        Socket s = ss.accept();
        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String string1 = "", string2 = "";
        Server obj = new Server();


        while (!string1.equals("0")) {
            string1 = in.readUTF();
            String[] values = string1.split("\\|", 5);

            switch (values[0]) {
                case "1":
                    string2 = obj.accountCreate(values[1], values[2], values[3]);
                    break;
                case "2":
                    int acc = Integer.parseInt(values[2]);
                    double amount = Double.parseDouble(values[3]);
                    string2 = obj.makeDeposit(acc, amount);
                    break;

                case "3":
                    int acc1 = Integer.parseInt(values[2]);
                    double amount1 = Double.parseDouble(values[3]);
                    string2 = obj.makeWithdrawal(acc1, amount1);
                    break;

                case "4":
                    int fromAccNo = Integer.parseInt(values[1]);
                    int toAcc = Integer.parseInt(values[2]);
                    double amount2 = Double.parseDouble(values[3]);
                    string2 = obj.makeTransfer(fromAccNo, toAcc, amount2);
                    break;
//                case "5" -> {
//                    int acc2 = Integer.parseInt(values[2]);
//                    string2 = obj.banks.printTransaction(acc2);
//                }
                default:
                    System.out.println("Invalid option");
                    break;
            }
            out.writeUTF(string2);
            out.flush();

        }

        in.close();
        s.close();
        ss.close();
    }

    public String accountCreate(String name, String nic, String address) {
        Account accountNo = new Account();
        Customer customs = new Customer(name, nic, address, accountNo);
        banks.addCustomer(customs);
        return customs.toString();
    }

    public String makeDeposit(int accountNo, double amount) {
        if (banks.getCustomerIndex(accountNo) < 0) {
            return "Invalid account number";
        } else {
            boolean b = banks.getCustomer(accountNo).getAccountNo().depositt(amount);
            if (b) {
                Transaction t = new Transaction();
                LocalDateTime myDateObj = LocalDateTime.now();
                Transaction t1 = new Transaction(t.getTransactionid(), accountNo, "Deposit", amount, myDateObj);
                banks.addTransaction(t1);
                return "Account No : " + banks.getCustomer(accountNo).getAccountNo().toString();
            }
            return "You can not deposit negative values";

        }


    }

    public String makeWithdrawal(int accountNo, double amount) {
        if (banks.getCustomerIndex(accountNo) < 0) {
            return "Invalid account number";
        } else {
            boolean b = banks.getCustomer(accountNo).getAccountNo().withdraw(amount);
            if (b) {
                Transaction t = new Transaction();
                LocalDateTime myDateObj = LocalDateTime.now();
                Transaction t1 = new Transaction(t.getTransactionid(), accountNo, "Deposit", amount, myDateObj);
                banks.addTransaction(t1);
                return "Account No : " + banks.getCustomer(accountNo).getAccountNo().toString();
            }
            return "Insufficient Balance";

        }
    }

    public String makeTransfer(int fromAcc, int toAcc, double amount) {
        if (banks.getCustomerIndex(fromAcc) < 0 || banks.getCustomerIndex(toAcc) < 0) {
            return "Invalid account Number";
        } else {
            BigDecimal d1 = banks.getCustomer(fromAcc).getAccountNo().getBalance();
            BigDecimal d2 = BigDecimal.valueOf(amount);
            //double d = bank.getCustomer(fromAccountNo).getAccountNo().getBalance();
            BigDecimal d3 = d1.max(d2);
            if (d3.equals(d2)) {
                return "insufficient balance";
            } else {
                Transaction t = new Transaction();
                LocalDateTime myDateObj = LocalDateTime.now();
                Transaction t1 = new Transaction(t.getTransactionid(), fromAcc, "Transfer-Debit", amount, myDateObj);
                banks.addTransaction(t1);
                banks.getCustomer(fromAcc).getAccountNo().withdraw(amount);
                Transaction t2 = new Transaction(t.getTransactionid(), toAcc, "Transfer-Credit", amount, myDateObj);
                banks.addTransaction(t2);
                banks.getCustomer(toAcc).getAccountNo().depositt(amount);

            }
            return "From Account No : " + banks.getCustomer(fromAcc).getAccountNo().toString() + "\nTo Account No : " + banks.getCustomer(toAcc).getAccountNo().toString();
        }
    }
}





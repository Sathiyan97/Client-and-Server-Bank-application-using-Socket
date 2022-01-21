package main;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Scanner sc = new Scanner(System.in);
    int choice;

    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 6666);
        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String string1 = "", string2 = "";
        while (!string1.equals("0")) {
            Client cc = new Client();

            int x = cc.bankMenu();
            string1 = br.readLine();
            String dump = x + "|";
            String string3 = dump + string1;
            out.writeUTF(string3);
            out.flush();
            string2 = in.readUTF();
            System.out.println("Your Account details: " + string2);
        }


        out.close();
        s.close();
    }

    public int bankMenu() {
        System.out.println("\nEnter your choice\n ");
        System.out.println("1.Create new account" +
                "\n2.Deposit \n3.Withdraw \n4.Transfer\n5.Transaction History\n0.Exit");
        choice = sc.nextInt();
        switch (choice) {
            case 0:
                System.exit(0);
                break;
            case 1:
                System.out.println("Enter name | NIC No | Address");
                break;
            case 2:
                System.out.println("Enter name | Account No | amount ");
                break;
            case 3:
                System.out.println("Enter name | Account No | amount ");
                break;
            case 4:
                System.out.println("From Account No |To Account No | amount ");
                break;
            case 5:
                System.out.println("Name |Account No ");
                break;

            default:
                System.out.println("Invalid option");
        }
        return choice;
    }


}

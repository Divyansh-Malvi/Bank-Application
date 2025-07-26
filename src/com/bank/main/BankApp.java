package com.bank.main;

import com.bank.bean.AccountBean;
import com.bank.dao.AccountDAO;
import com.bank.dao.TransferDAO;
import com.bank.enums.AccountStatus;
import com.bank.enums.AccountType;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Scanner;

import static com.bank.utility.ConnectionPool.connectDB;

public class BankApp {
    public static void main(String[] args) throws SQLException {
        AccountBean ab = new AccountBean();
        AccountDAO ad = new AccountDAO();
        TransferDAO td = new TransferDAO();
        Scanner scanner = new Scanner(System.in);
//        String accNo;
//        do {
//            System.out.println("Enter Account Number (Account number must be greater than 9 and less than 12");
//            accNo = scanner.nextLine().trim();
//            if (!accNo.matches("\\d{9,12}")) {
//                System.out.println("Invalid input use numbers only");
//            }
//
//        } while (!accNo.matches("\\d{9,12}"));
//        ab.setAcc_no(accNo);
//
//
//        System.out.println("Enter Customer Name");
//        String name;
//        while (true) {
//            name = scanner.nextLine().trim();
//            if (name.matches("[a-zA-Z\\s]+")) {
//                ab.setCustomer_name(name);
//                break;
//            } else {
//                System.out.println("Invalid input , enter alphabets only ");
//            }
//        }
//        //ab.setCustomer_name(scanner.nextLine());
//        String pin;
//        do {
//            System.out.println("Enter Account Pin (max 6 digits)");
//            pin = scanner.nextLine();
//            if (!pin.matches("\\d{1,6}")) {
//                System.out.println("Invalid input , use numbers and max digit 6");
//            }
//
//        } while (!pin.matches("\\d{1,6}"));
//        ab.setPin(pin);
//
//        double balance = -1;
//        while (balance < 0) {
//            System.out.println("Enter Account Balance");
//            if (scanner.hasNextDouble()) {
//                balance = scanner.nextDouble();
//                if (balance < 0) {
//                    System.out.println("Balance cant be in negative amount");
//                }
//            } else {
//                System.out.println("Invalid input enter a number");
//                scanner.next();
//            }
//
//        }
//
//        ab.setBalance(balance);
//        scanner.nextLine();
//        System.out.println("Enter Account Type (savings/current)");
//        ab.setAccountType(AccountType.valueOf(scanner.nextLine().toUpperCase()));
//        System.out.println("Enter Account Status (Active/Inactive");
//        ab.setAccountStatus(AccountStatus.valueOf(scanner.nextLine().toUpperCase()));
//        ab.setAcc_created(new Timestamp(System.currentTimeMillis()));
//
//        int result = ad.createAccount(ab);
//
//        if (result > 0) {
//            System.out.println("Account Created");
//        } else {
//            System.out.println("Error....");
//        }

//            System.out.println("Enter account number to see the information");
//            String num = scanner.nextLine();
//            AccountBean acb = ad.getAccountByNo(num);
//            if (acb != null){
//                System.out.println("Account Number : " + acb.getAcc_no());
//                System.out.println("Customer Name : " + acb.getCustomer_name());
//                System.out.println("Pin : " + acb.getPin());
//                System.out.println("Account Type : " + acb.getAccountType());
//                System.out.println("Account Status : " + acb.getAccountStatus());
//                System.out.println("Account Created : " + acb.getAcc_created());
//            } else {
//                System.out.println("Empty Data , Try Again");
//            }
//        AccountBean ab1 = new AccountBean();
//        System.out.println("Enter Account Number to update the record");
//        String num;
//        while (true){
//            num = scanner.nextLine();
//            if (num.matches("\\d{9,12}")){
//                ab1.setAcc_no(num);
//                break;
//            } else {
//                System.out.println("Invalid Input ");
//                scanner.next();
//            }
//        }
//        //ab1.setAcc_no(scanner.nextLine());
//        System.out.println("Enter Customer Name");
//        String name;
//        while (true) {
//            name = scanner.nextLine();
//            if (name.matches("[a-zA-Z\\s]+")) {
//                ab1.setCustomer_name(name);
//                break;
//            } else {
//                System.out.println("Invalid Input, use alphabets");
//            }
//        }
//        System.out.println("Enter Pin");
//        String pin;
//        while (true) {
//            pin = scanner.nextLine();
//            if (pin.matches("\\d{1,6}")) {
//                ab1.setPin(pin);
//                break;
//            } else {
//                System.out.println("Invalid input use digits , max 6 digits");
//            }
//        }
//        System.out.println("Enter Balance");
//        double balance;
//        while (true) {
//            if (scanner.hasNextDouble()) {
//                balance = scanner.nextDouble();
//                scanner.nextLine();
//                if (balance > 0) {
//                    ab1.setBalance(balance);
//                    break;
//                } else {
//                    System.out.println("Invalid input balance can't be negative");
//                }
//
//            } else {
//                System.out.println("Invalid input ");
//                scanner.next();
//            }
//        }
//        System.out.println("Enter Account Type");
//        while (true) {
//            try {
//
//                ab1.setAccountType(AccountType.valueOf(scanner.nextLine().toUpperCase()));
//                break;
//            } catch (IllegalArgumentException e) {
//                System.out.println("Invalid Input " + e.getMessage());
//            }
//        }
//        System.out.println("Enter Account Status");
//        while (true) {
//            try {
//                ab1.setAccountStatus(AccountStatus.valueOf(scanner.nextLine().toUpperCase()));
//                break;
//            } catch (IllegalArgumentException e) {
//                System.out.println("Invalid Input " + e.getMessage());
//            }
//        }
//
//        int result = ad.updateAccount(ab1);
//        if (result > 0) {
//            System.out.println("Account Updated");
//        } else {
//            System.out.println("Error....");
//        }
//        System.out.println("Enter account number to delete the account");
//        String acc = scanner.nextLine();
//        int result = ad.deleteAccount(acc);
//        if (result > 0){
//            System.out.println("Account deleted");
//        } else {
//            System.out.println("Error.....");
//        }
//        List<AccountBean> list = ad.getAllAccount();
//        if (list.isEmpty()) {
//            System.out.println("Empty data");
//        } else {
//            System.out.printf("%-10s %-20s %-10s %-10s %-15s %-15s %-20s \n",
//                    "Acc. No.", "Cus. Name", "Pin", "Balance", "Acc. Type", "Status", "Acc Created");
//            System.out.println("------------------------------------------------------------------------------------------------------------");
//            for (AccountBean a : list) {
//                System.out.printf("%-10s %-20s %-10s %-10s %-15s %-15s %-20s \n",
//                        a.getAcc_no(), a.getCustomer_name(), a.getPin(), a.getBalance(), a.getAccountType(), a.getAccountStatus(), a.getAcc_created());
//            }
//        }
//        System.out.println("Enter Account Number");
//        String num = scanner.nextLine();
//        System.out.println("Enter Pin to validate account");
//        String pin = scanner.nextLine();
//        boolean valid = ad.validAccount(num, pin);
//        if (valid){
//            System.out.println("Account is valid, Welcome");
//        } else {
//            System.out.println("Invalid account number or pin , try again....");
//        }
//        System.out.println("Enter Account Number to check Balance");
//        String num = scanner.nextLine();
//        System.out.println("Enter Pin to check Balance");
//        String pin = scanner.nextLine();
//        AccountBean ab1 = ad.checkBalance(num, pin);
//        if (ab1 != null){
//            System.out.println("Account Balance = " + ab1.getBalance());
//        } else {
//            System.out.println("Error , try again.....");
//        }
//        System.out.println("Enter Account Number to update the balance");
//        String num = scanner.nextLine();
//        System.out.println("Enter New Balance");
//        double newBal = scanner.nextDouble();
//
//        int result = ad.updateBalance(num, newBal);
//
//        if (result > 0){
//            System.out.println("Balance Updated");
//        } else {
//            System.out.println("Error.......");
//        }

//        System.out.println("Enter Account Number To Change The Pin");
//        String num = scanner.nextLine();
//        System.out.println("Enter Old Pin");
//        String oldPin = scanner.nextLine();
//        System.out.println("Enter New Pin");
//        String newPin = scanner.nextLine();
//
//        int result = ad.changePin(num, oldPin, newPin);
//        if (result > 0){
//            System.out.println("Pin Changed");
//        } else {
//            System.out.println("Error.....");
//        }

        System.out.println("Enter Your Account Number");
        String acc_no = scanner.nextLine();
        System.out.println("Enter Beneficiary Account Number");
        String ben_acc_no = scanner.nextLine();
        System.out.println("Enter Amount To Transfer");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter You Pin");
        String pin = scanner.nextLine();
        td.transferMoney(acc_no, pin, ben_acc_no, amount);

    }


}



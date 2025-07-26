package com.bank.dao;

import com.bank.bean.TransferBean;
import com.bank.utility.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransferDAO {
    public boolean transferMoney(String SenderAcc_no , String pin, String ReceiverAcc_no, Double amount){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // 1. checking senders acc_no and pin is valid or not
            conn = ConnectionPool.connectDB();
            String checkSender = "select pin, balance from account where acc_no = ? and status = 'active' ";
            ps = conn.prepareStatement(checkSender);
            ps.setString(1, SenderAcc_no);
            rs = ps.executeQuery();
            if (!rs.next()){
                System.out.println("Sender's account do not exist or is inactive");
                return false;
            }

            String actualPin = rs.getString("pin");
            Double senderBalance = rs.getDouble("balance");

            if (!actualPin.equals(pin)){
                System.out.println("Incorrect Pin , transaction failed");
                return false;
            }

            if (senderBalance < amount){
                System.out.println("Insufficient balance , transaction failed");
                return false;
            }

            rs.close();
            ps.close();

            // 2. checking receivers acc_no
            String checkReceiver = "select acc_no from account where acc_no = ? and status = 'active' ";
            ps = conn.prepareStatement(checkReceiver);
            ps.setString(1, ReceiverAcc_no);
            rs = ps.executeQuery();
            if (!rs.next()){
                System.out.println("Receiver's account to not exist");
                return false;
            }
            rs.close();
            ps.close();

            //Transaction begins
            conn.setAutoCommit(false);

            //3. update senders balance
            String updateSender = "update account set balance = balance - ? where acc_no = ?";
            ps = conn.prepareStatement(updateSender);
            ps.setDouble(1, amount);
            ps.setString(2, SenderAcc_no);
            ps.executeUpdate();
            ps.close();

            //4. update receivers balance
            String updateReceiver = "update account set balance = balance + ? where acc_no = ?";
            ps = conn.prepareStatement(updateReceiver);
            ps.setDouble(1, amount);
            ps.setString(2, ReceiverAcc_no);
            ps.executeUpdate();
            ps.close();

            //5. Enter Senders Transaction
            String senderTxn = "insert into transfer(acc_no, beneficiary_acc_no, txn_type, txn_amount)" +
                    "values(?, ?, ?, ?)";
            ps = conn.prepareStatement(senderTxn);
            ps.setString(1, SenderAcc_no);
            ps.setString(2, ReceiverAcc_no);
            ps.setString(3, "debit");
            ps.setDouble(4, amount);
            ps.executeUpdate();
            ps.close();

            //6. Enter Receivers Transaction
            String receiverTxn = "insert into transfer(acc_no, beneficiary_acc_no, txn_type, txn_amount)" +
                    "values(?, ?, ?, ?)";
            ps = conn.prepareStatement(receiverTxn);
            ps.setString(1, ReceiverAcc_no);
            ps.setString(2, SenderAcc_no);
            ps.setString(3, "credit");
            ps.setDouble(4, amount);
            ps.executeUpdate();

            //Commit Transaction
            conn.commit();
            System.out.println("Transaction Successful");
            return true;

        } catch (Exception e) {
            try {
                if (conn != null)conn.rollback();
                System.out.println("Error occurred . Transaction rolled back");
            } catch (SQLException se) {
            se.printStackTrace();
        }
            e.printStackTrace();
            return false;

    } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.setAutoCommit(true);
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        }
}

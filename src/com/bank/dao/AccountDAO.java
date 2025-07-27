package com.bank.dao;

import com.bank.bean.AccountBean;
import com.bank.bean.TransferBean;
import com.bank.enums.AccountStatus;
import com.bank.enums.AccountType;
import com.bank.utility.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    public int createAccount(AccountBean ab) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;

        conn = ConnectionPool.connectDB();
        String sql = "insert into account(acc_no, customer_name, pin, balance, acc_type, status, acc_created)" +
                "values(?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, ab.getAcc_no());
            ps.setString(2, ab.getCustomer_name());
            ps.setString(3, ab.getPin());
            ps.setDouble(4, ab.getBalance());
            ps.setString(5, ab.getAccountType().name());
            ps.setString(6, ab.getAccountStatus().name());
            ps.setTimestamp(7, ab.getAcc_created());

            result = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public AccountBean getAccountByNo(String num) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        AccountBean ab = null;

        conn = ConnectionPool.connectDB();
        String sql = "select * from account where acc_no = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, num);
            rs = ps.executeQuery();
            if (rs.next()) {
                ab = new AccountBean();
                ab.setAcc_no(rs.getString("acc_no"));
                ab.setCustomer_name(rs.getString("customer_name"));
                ab.setPin(rs.getString("pin"));
                ab.setBalance(rs.getDouble("balance"));
                try {
                    ab.setAccountType(AccountType.valueOf(rs.getString("acc_type").toUpperCase()));
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid input " + e.getMessage());
                }
                try {
                    ab.setAccountStatus(AccountStatus.valueOf(rs.getString("status").toUpperCase()));
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid input " + e.getMessage());
                }
                ab.setAcc_created(rs.getTimestamp("acc_created"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return ab;
    }

    public int updateAccount(AccountBean ab) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        conn = ConnectionPool.connectDB();
        String sql = "update account set customer_name = ?, pin = ?, balance = ?, acc_type = ?, status = ? where acc_no = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, ab.getCustomer_name());
            ps.setString(2, ab.getPin());
            ps.setDouble(3, ab.getBalance());
            ps.setString(4, ab.getAccountType().name());
            ps.setString(5, ab.getAccountStatus().name());
            ps.setString(6, ab.getAcc_no());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public int deleteAccount(String acc) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        conn = ConnectionPool.connectDB();
        String sql = "delete from account where acc_no = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, acc);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public List<AccountBean> getAllAccount() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        AccountBean ab = null;
        List<AccountBean> list = new ArrayList<>();
        conn = ConnectionPool.connectDB();
        String sql = "select * from account";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ab = new AccountBean();
                ab.setAcc_no(rs.getString("acc_no"));
                ab.setCustomer_name(rs.getString("customer_name"));
                ab.setPin(rs.getString("pin"));
                ab.setAccountType(AccountType.valueOf(rs.getString("acc_type").toUpperCase()));
                ab.setAccountStatus(AccountStatus.valueOf(rs.getString("status").toUpperCase()));
                ab.setAcc_created(rs.getTimestamp("acc_created"));
                list.add(ab);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }

    public boolean validAccount(String acc_no, String pin) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean isValid = false;

        conn = ConnectionPool.connectDB();
        String sql = "select * from account where acc_no = ? and pin = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, acc_no);
            ps.setString(2, pin);
            rs = ps.executeQuery();
            if (rs.next()) {
                isValid = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return isValid;
    }

    public AccountBean checkBalance(String acc_no, String pin) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        AccountBean ab = null;

        conn = ConnectionPool.connectDB();
        String sql = "select balance from account where acc_no = ? and pin = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, acc_no);
            ps.setString(2, pin);
            rs = ps.executeQuery();
            if (rs.next()) {
                ab = new AccountBean();
                ab.setBalance(rs.getDouble("balance"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return ab;
    }

    public int updateBalance(String acc_no, double newBal) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;

        conn = ConnectionPool.connectDB();
        String sql = "update account set balance = ? where acc_no = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, newBal);
            ps.setString(2, acc_no);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public int changePin(String acc_num, String oldPin, String newPin) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;

        conn = ConnectionPool.connectDB();
        String sql = "update account set pin = ? where acc_no = ? and pin = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, newPin);
            ps.setString(2, acc_num);
            ps.setString(3, oldPin);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
}


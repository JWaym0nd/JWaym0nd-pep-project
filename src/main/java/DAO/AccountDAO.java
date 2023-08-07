package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    // Utility method that I initially was going to
    // use for login verification.
    public List<Account> getAllAccounts(){
        Connection connection = ConnectionUtil.getConnection();
        List<Account> accounts = new ArrayList<>();
        try {
            //Write SQL logic here
            String sql = "Select * From account;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Account account = new Account(rs.getInt("account_id"), rs.getString("username"),
                        rs.getString("password"));
                accounts.add(account);
            }
        }catch(SQLException e){
                    System.out.println(e.getMessage());
        }
    return accounts;
    }

    // Req # 1
   public Account insertNewUserAccount(Account account){
       Connection connection = ConnectionUtil.getConnection();
       try {
           String sql = "Insert Into Account (username, password) values (?, ?);" ;
           PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           preparedStatement.setString(1, account.getUsername());
           preparedStatement.setString(2, account.getPassword());

           preparedStatement.executeUpdate();
           ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
           if(pkeyResultSet.next()){
               int generated_account_id = (int) pkeyResultSet.getLong(1);
               return new Account(generated_account_id, account.getUsername(), account.getPassword());
           }
       }catch(SQLException e){
           System.out.println(e.getMessage());
       }
       return null;
   }

   // Req 2
       /**
     * Querying account table 
     * for record with username, password matching the
     * given account object's. Then building a new object from the result set.
     * @param account
     */
   public Account verifyLogin(Account account){
    Connection connection = ConnectionUtil.getConnection();
    try {
        String sql = "Select * From Account Where username = ? AND  password = ?;" ;
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, account.getUsername());
        preparedStatement.setString(2, account.getPassword());

        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            account = new Account(rs.getInt("account_id"),
                rs.getString("username"),
                rs.getString("password"));
            return account;
        }
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return null;
}



}
package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    AccountDAO accountDAO;
    //TODO: Retrieve a specific Account  using its account ID.
    public Account register(Account account){
        Connection connection = ConnectionUtil.getConnection();
        //List<Account> accounts = new ArrayList<Account>();
        try {
            String sql = "INSERT INTO Account(account_id,username,password) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1,account.getAccount_id());
            preparedStatement.setString(2,account.getUsername());
            preparedStatement.setString(3,account.getPassword());
            preparedStatement.executeUpdate();

            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()) {
                int generated_account_id = (int) pkeyResultSet.getLong(1);
                return  new Account(generated_account_id, account.account_id,
                        account.getUsername(),account.getPassword());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return null;
    }
    public Account login(String username, String password){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "select username,password(?,?) from Account";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

//            preparedStatement.setString(1, account.getUsername());
//            preparedStatement.setString(2,account.getPassword());
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.executeQuery("select username, password(?,? " +
                    "from Account)");
            while (resultSet.next()){ //Until next row is present otherwise it return false
                resultSet.getString("username");//fetch the values present in database
                resultSet.getString("password");
            if(username.equals(username) && password.equals(password))
            {
                return null;
            }else
            {//return null;
            }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public List<Account> getAllAccounts() {
        Connection connection = ConnectionUtil.getConnection();
        List<Account> account = new ArrayList<>();
        try {
            //SQL logic here
            String sql = "select * from Account";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                Account accounts = new Account(rs.getInt("account_id"),
                        rs.getString("username"),
                        rs.getString("password"));
                account.add(accounts);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return account;
    }
    public Account getAccountById(int account_id){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "SELECT * FROM Account WHERE Account_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,account_id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Account account= new Account(rs.getInt("account_id"),
                        rs.getString("username"),
                        rs.getString("password"));
                return account;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());

        }
        return null;
    }

    public  Account insertAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //sql logic
            String sql = "INSERT INTO Account(account_id,username,password) VALUES (?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,account.getAccount_id());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.setString(3,account.getPassword());
            preparedStatement.executeUpdate();
            return account;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}

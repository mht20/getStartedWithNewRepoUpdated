package Service;

import DAO.AccountDAO;
import  java.util.List;
import Model.Account;

import javax.swing.event.ListDataEvent;

public class AccountService {
    static AccountDAO accountDAO;
    //The purpose of a Service class is to contain "business logic" that sits between the web layer (controller) and
    //persistence layer (DAO). That means that the Service class performs tasks that aren't done through the web or SQL:
    //programming tasks like checking that the input is valid, conducting additional security checks, or saving the
    //actions undertaken by the API to a logging file.
    public  AccountService(){
        accountDAO = new AccountDAO();
    }
    public AccountService(AccountDAO accountDAO){
        this.accountDAO= accountDAO;
    }
    //param account an object representing a new account.
    //return the newly added account if the add operation was successful
    public static Account addAccount(Account account){
        return accountDAO.register(account);
    }
    public Account login( Account account){
        return (Account) accountDAO.getAllAccounts();
    }


    public Account updateAccount(int account_id, Account account){
        if(accountDAO.getAccountById(account_id)== null ){
           // return null;
        }else {
            return (Account) accountDAO.getAllAccounts();
        }
        return null;
    }
    //TODO: Use the AccountDAO to retrieve a List containing all accounts.
    // You could use the accountDAO.getAllAccounts method.
    public List<Account> getAllAccounts(){
        return accountDAO.getAllAccounts();
    }
    public List<Account>getAllAccountsDetail(int account_id, String username, String password){
        return accountDAO.getAllAccounts();
    }


}

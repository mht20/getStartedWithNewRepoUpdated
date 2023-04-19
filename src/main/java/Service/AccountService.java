package Service;

import DAO.AccountDAO;
import  java.util.List;
import Model.Account;

public class AccountService {
    AccountDAO accountDAO;
    //The purpose of a Service class is to contain "business logic" that sits between the web layer (controller) and
    //persistence layer (DAO). That means that the Service class performs tasks that aren't done through the web orSQL: programming tasks like checking that the input is valid, conducting additional security checks, or saving the
    //actions undertaken by the API to a logging file.
    public  AccountService(){
        accountDAO = new AccountDAO();
    }
    public AccountService(AccountDAO accountDAO){
        this.accountDAO= accountDAO;
    }
    public List<Account> getAllAccounts(){
        return accountDAO.getAllAccounts();

    }
    public static Account addAccount(Account account){
        //if(account.getUsername() == accountDAO.getAllAccounts() && !account.getUsername())
       // return  accountDAO.getAllAccounts();
        return null;
    }



}

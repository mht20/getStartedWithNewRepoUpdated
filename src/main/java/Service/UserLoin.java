package Service;

import java.util.Scanner;

public class UserLoin {
    //public void UserLoin(){}
    public void login(){
        //String username;
        //String password;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username:"); //username: user
       String  username =  scanner.nextLine();

        System.out.println("Enter password"); //password :user
        String password = scanner.nextLine();

        boolean  pass= validatePassword(password);

        if(pass ){ //true
            System.out.println("Authentication Successful");
        }
        else if(!pass)// false
        {
            System.out.println("Authentication Fail");
        }
    }
    public  static  boolean validatePassword(String password) {
        if (password.length() < 4) {
            return false;
        }
        return true;
    }
}

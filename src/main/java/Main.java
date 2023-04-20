import Controller.SocialMediaController;
import io.javalin.Javalin;

import java.util.Scanner;

//import static Service.AccountRegistration.validatePassword;


/**
 * This class is provided with a main method to allow you to manually run and test your application. This class will not
 * affect your program in any way and you may write whatever code you like here.
 */
public class Main {

    String name;
    String password;
    public static void main(String[] args) {
        SocialMediaController controller = new SocialMediaController();
        Javalin app = controller.startAPI();
        app.start(8080);

        //--------------
        String name;
        String password;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user name:");
        name = scanner.nextLine();
        System.out.println("\n" + name);

        System.out.println("Enter user password:");
        password = scanner.nextLine();
        System.out.println("\n"+password);

        boolean  result = validatePassword(password);
        if(result == true){
            System.out.println("Validation is successful:");
        } else if ( result == false) {
            System.out.println("Validation is fail:");

        }

    }

    public  static  boolean validatePassword(String password){
            if(password.length() < 4) {
                return false;
            }
            return true;
        }
}

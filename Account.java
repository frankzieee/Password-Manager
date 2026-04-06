import java.io.File;
import java.io.IOException;

public class Account {
    String username;
    String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        //create File with every new Account Object
        try {
            File myObj = new File(username + ".txt"); // Create File object
            if (myObj.createNewFile()) { // Try to create the file
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace(); // Print error details
        }
    }

    public boolean login(String password) {
        if (this.password.equals(password)) {
            return true;
        } else
            return false;
    }

    
}

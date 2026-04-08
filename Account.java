import java.io.File;
import java.io.IOException;

public class Account {
    String username;
    String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        //create File with every new Account Object
        this.password.trim();
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
        password.trim();
        if (this.password.trim().equals(password.trim())) {
            System.out.println("login was successful");
            return true;
        }
        else {
            System.out.println("login failed, password was incorrect, please try again: ");
              return false; 
        }
    }
}        
//login isnt working doesnt recognize the password
//once logged in print all passwords (stored in db)
//then again allow to select etc
//eventually encrypt absolutely everything
//in Strings add the space bar out

    


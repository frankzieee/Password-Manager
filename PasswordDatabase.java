import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class PasswordDatabase {
    HashMap<String, Credentials> passwords = new HashMap<>();

    public PasswordDatabase() {

    }

    public void add_Credential(String service, String password, String username) {
        Credentials credential = new Credentials(password, username);
        passwords.put(service, credential);
    }

    public Credentials get_password(String service) {
        return passwords.get(service);

    }

    public String loadFromFile(File file) {
        // File myObj = new File(account.username + ".txt");
        String accountPassword = "";
        // try-with-resources: Scanner will be closed automatically
        try (Scanner myReader = new Scanner(file)) {
            //case the file is empty
            if (!myReader.hasNextLine()) {
                System.out.println("File is empty or corrupted");
                return "";
            }
            //file isnt empty, reads account-password since is the first line of the file
            accountPassword = myReader.nextLine();
            System.out.println("Account password: " + accountPassword);
            //read the rest of the file, in order: Service, username, password
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                int firstIndex = 0;
                int cnt = 0;
                String serviceString = "";
                String credentialUsername = "";
                String credentialPassword = "";
                for (int i = 0; i < data.length(); i++) {
                    if (data.charAt(i) == ',') {
                        if (cnt == 0) {
                            serviceString = data.substring(firstIndex, i);
                            System.out.println("service: " + serviceString);
                            firstIndex = i + 1;
                            cnt++;
                        }
                        if (cnt == 1) {
                            credentialUsername = data.substring(firstIndex, i);
                            System.out.println("username: " + credentialUsername);
                            firstIndex = i + 1;
                            cnt++;
                        }
                    }
                }
                if (cnt == 2) {
                    credentialPassword = data.substring(firstIndex, data.length());
                    Credentials credential = new Credentials(credentialPassword, credentialUsername);
                    passwords.put(serviceString, credential);
                    System.out.println("password: " + credentialPassword);
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return accountPassword;

    }

    public void saveToFile(Account account) {
        try {
            FileWriter myWriter = new FileWriter(account.username + ".txt");
            myWriter.write(account.password); // first line of File is account-password
            myWriter.write("\n");
            // if the passwords key set isnt empty write all services + username + passwords
            if (!passwords.keySet().isEmpty()){
                for (String i : passwords.keySet()) {
                myWriter.write(i + "," + passwords.get(i).username + "," + passwords.get(i).password);
                // i = service, Credentials username, Credentials password
                myWriter.write("\n");
            }
            myWriter.close(); // must close manually
            System.out.println("Successfully wrote to the file.");
            }
            else {
                myWriter.close();
                return; 
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void printFiles() {
        File directory = new File(".");
        File[] files = directory.listFiles();

        if (files != null) {
            System.out.println("following accounts have already been created: ");
            System.out.println("----------------------");
            for (File file : files) {
                String fileString = file.getName();
                if (fileString.endsWith(".txt")) {
                    System.out.println(file.getName());
                }
            }
        }
        else {
            System.out.println("first file has been created: ");
            System.out.println(files);
        }

    }

    public static void selectFiles(String choice) {
        // if file exists then conitnue else throw exception
        File file = new File(choice + ".txt");

        if (file.exists()) {
            PasswordDatabase db = new PasswordDatabase();
            String account_password = db.loadFromFile(file);
            Account account = new Account(choice, account_password); 
            System.out.println("Account selected:");
            System.out.println("Username: " + account.username + ": ");
            System.out.println("please enter the password: ");
            String entered_password = StdIn.readLine();
            while (!account.login(entered_password)){
                entered_password = StdIn.readLine();
            } 
            
        } else {
            System.out.println("the file does not exist or was written incorrecly: Please try again");

        }
    }
}


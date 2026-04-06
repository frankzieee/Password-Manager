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
            accountPassword = myReader.nextLine();
            System.out.println("Account password: " + accountPassword);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                int firstIndex = 0;
                int cnt = 0;
                String serviceString = "";
                String credentialUsername = "";
                String credentialPassword = "";

                // first line needs to be read individually
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
            myWriter.write(account.password); // first line of File ist account password
            myWriter.write("\n");
            for (String i : passwords.keySet()) {
                myWriter.write(i + "," + passwords.get(i).username + "," + passwords.get(i).password);
                // i = service, Credentials username, Credentials password
                myWriter.write("\n");
            }
            myWriter.close(); // must close manually
            System.out.println("Successfully wrote to the file.");
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
                while (fileString.contains(".txt")) {
                    System.out.println(file.getName());
                }
            }
        }

    }

    public static void selectFiles(String choice) {
        // if file exists then conitnue else throw exception
        File file = new File(choice + ".txt");

        if (file.exists()) {
            PasswordDatabase db = new PasswordDatabase();
            String account_password = db.loadFromFile(file);
            Account account = new Account(choice, account_password); // PASSWORD INSTEAD OF CHOICE
            System.out.println("Account selected:");
            System.out.println("Username: " + account.username + ": ");
            System.out.println("please enter the password: ");
            String entered_password = StdIn.readLine();
            account.login(entered_password);
        } else {
            System.out.println("the file does not exist or was written incorrecly: Please try again");

        }
    }
}

// i need to fix this right now... maybe later sometimes
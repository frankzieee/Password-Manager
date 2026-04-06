public class Credentials {
    String password; 
    String username;

    public Credentials(String password, String username){
        this.password = password; 
        this.username = username; 
    }

    
}

/*
public void loadFromFile(Account account) {
        File myObj = new File(account.username + ".txt");

        // try-with-resources: Scanner will be closed automatically
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                int firstIndex = 0;
                int cnt = 0;
                String accountPassword = "";
                String serviceString = "";
                String credentialUsername = "";
                String credentialPassword = "";

                for (int i = 0; i < data.length(); i++) {
                    if (data.charAt(i) == ',') {
                        if (cnt == 0) {
                            serviceString = data.substring(firstIndex, i);
                            System.out.println(serviceString);
                            firstIndex = i + 1;
                            cnt++;
                        }
                        if (cnt == 1) {
                            credentialUsername = data.substring(firstIndex, i);
                            System.out.println(credentialUsername);
                            firstIndex = i + 1;
                            cnt++;
                        }
                    }
                }
                if (cnt == 2){
                    credentialPassword = data.substring(firstIndex, data.length());
                    Credentials credential = new Credentials(credentialPassword, credentialUsername);
                    passwords.put(serviceString, credential);
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

*/
public class Main {

    public static void main(String[] args) {
        System.out.println("Password Manager.");
        System.out.println();
        System.out.println("(1) Create Account");
        System.out.println("(2) Use existing Account");
        System.out.println("(EXIT)");
        System.out.println("----------------------");
        String choice = StdIn.readLine();
            boolean part2 = false;
            if (choice.equals("1")) {
                System.out.println("Please enter Username: ");
                String username = StdIn.readLine();
                System.out.println("Please enter Password");
                String password = StdIn.readLine();
                Account account = new Account(username, password);
                PasswordDatabase db = new PasswordDatabase(); 
                db.saveToFile(account);
                // here is the mistake saveToFile(Account);
                //-----------------------------------------------------
                //here i want to immediately go to the things that happen in if "2"
                part2 = true;   
            } 

            if (choice.equals("2") || part2) {
                    PasswordDatabase.printFiles();
                    //"select" a File and then tie that to an Account/File and login to it
                   //Std.readLine() --> Account Object
                   String choice2 = StdIn.readLine();
                   System.out.println("Please enter the name of the file, without .txt");
                   PasswordDatabase.selectFiles(choice2); 
                   //login
                    
  
            }
            
            if (choice.equals("exit")){
                System.exit(0);
            }
        }
    }

        /*
         * i make a new account via the 1 
         * a new empty file is created but its linked to the account instance
         * issue: empty file with no chance to log into it afterwards
         * solution: as soon as a file is created the password should be written in it
         * 
         */

    


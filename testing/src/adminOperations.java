import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class adminOperations {
        public Scanner scanner = new Scanner(System.in);
        public void main() throws SQLException{
            // TODO format
            System.out.println("Press 1 to create new employees.. 2 to delete.. 3 to update ... 4 to check worksheet from employees 5 for exit");
            boolean isContinue = true;
            while(isContinue)
            switch (scanner.nextInt()){
                case 1:create();break;
                case 2:delete();break;
                case 3:update();break;
                case 5:isContinue=false;break;
            }
        }

    public void create() throws SQLException {
        System.out.println("Please enter the employee information");
        System.out.println("Name: ");
        String newName = scanner.nextLine();
        System.out.println("Surname: ");
        String newSurname = scanner.nextLine();
        System.out.println("Age: ");
        int newAge = scanner.nextInt();
        System.out.println("Email: ");
        String newEmail = scanner.nextLine();
        System.out.println("TC NO: ");
        int newTC_NO = scanner.nextInt();
        System.out.println("Username: ");
        String newUsername = scanner.nextLine();
        System.out.println("Password: ");
        String newPassword = scanner.nextLine();

        // TODO auth group selection


        users.register(newName, newSurname, newUsername, newPassword,newAge,newEmail, newTC_NO);
    }

    public void delete() throws SQLException {
        System.out.println("Enter the TC NO of the user that you want to delete.");
        System.out.print("TC Number: ");
        String tc_no = scanner.nextLine();
        users.delete(tc_no);
    }

    public void update(){
        // TODO instead of entering username and password create an are in sql called TC kimlik
        // TODO update part must be in a loop that where user updates as needed
       /* System.out.println("Enter the username and password of the employee you want to update.");
        boolean updated = false;
        System.out.println("Username: ");
        String update_username = scanner.next();
        System.out.println("Password: ");
        String update_password = scanner.next();
        ResultSet rs_update = st.executeQuery(employeeLoginQuery);

        while(rs_update.next()){
            _username = rs_update.getString("username");
            _password = rs_update.getString("password");
            if(update_username.equals(_username) & update_password.equals(_password)){
                System.out.println("Which one do you want to update name, surname, username, password, age ,email, isAdmin");
                String updateAnswer = scanner.next();

                updateSQL(updateAnswer, _username, _password);

                updated = true;
                break;
            }
        }
        if(!updated){
            System.out.println("There is no employee with that username / password");
            System.exit(0);
        }
        break;*/
    }






}

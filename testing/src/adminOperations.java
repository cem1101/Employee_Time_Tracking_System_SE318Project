import java.sql.SQLException;
import java.util.Scanner;

public class adminOperations {
        public Scanner scanner = new Scanner(System.in);
        public void main() throws SQLException{
            // TODO format
            boolean isContinue = true;
            while(isContinue) {
                System.out.println("Press 1 to create new employees.. 2 to delete.. 3 to update ... 4 to check worksheet from employees 5 for exit");
                switch (scanner.nextInt()) {
                    case 1:
                        create();
                        System.out.println("Creation is done!");
                        break;
                    case 2:
                        delete();
                        System.out.println("Deletion is done!");
                        break;
                    case 3:
                        update();
                        break;
                    case 4:
                        checkWorksheets();
                        break;
                    case 5:
                        isContinue = false;
                        System.exit(1);
                        break;
                }
            }
        }

    private void checkWorksheets() throws SQLException {
        Users _users = new Users();

    }

    public void create() throws SQLException {
        System.out.println("Please enter the employee information");
        System.out.println("Name: ");
        String newName = scanner.next();
        System.out.println("Surname: ");
        String newSurname = scanner.next();
        System.out.println("Age: ");
        int newAge = scanner.nextInt();
        System.out.println("Email: ");
        String newEmail = scanner.next();
        System.out.println("TC NO: ");
        int newTC_NO = scanner.nextInt();
        System.out.println("Username: ");
        String newUsername = scanner.next();
        System.out.println("Password: ");
        String newPassword = scanner.next();
        System.out.println("Auth group: ");
        int authGroup = scanner.nextInt();
        Users.register(authGroup, newName, newSurname, newUsername, newPassword,newAge,newEmail, newTC_NO);
    }

    public void delete() throws SQLException {
        System.out.println("Enter the TC NO of the user that you want to delete.");
        System.out.print("TC Number: ");
        String tc = scanner.next();
        Users _users = new Users();
        _users.deleteByTC(tc);

    }

    public void update() throws SQLException {
        Users _user = new Users();
        _user.updateProcedure();
    }






}

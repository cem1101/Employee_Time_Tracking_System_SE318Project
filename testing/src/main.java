import java.sql.*;
import java.util.Scanner;

public class main {
    private static String _name;
    private static String _surname;
    private static String _username;
    private static String _password;
    private static int _age;
    private static String _email;
    private static String _lastLogin;
    private static int _isAdmin  = 0;
    private static int _tc_no;




    //  TODO to make it look nicer all the switch case methods can be implementing in a method so it wont look crowded  !!!!!!!!!

    public static void main(String args[]) throws  SQLException{
        String employeeLoginQuery = "SELECT *  FROM employee";
        String managerLoginQuery = "SELECT * FROM manager";
        String adminLoginQuery = "SELECT * FROM administrator";
        Administrator administrator = null;
        Employee employee = null;


        Scanner scanner = new Scanner(System.in);
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        Statement st = connection.createStatement();

        System.out.println("Welcome to login screen!");
        System.out.println("Enter 1 for registration");
        System.out.println("Enter 2 for employee login ");
        System.out.println("Enter 3 for manager login");
        System.out.println("Enter 4 for admin login");

        int answer = scanner.nextInt();
        /* switch statements later will be deleted because of the OCP violation */
        // TODO all system can only stop working when user enter wrong information higher than permitted number or whenever she/he wants.
        switch (answer){
            case 1:

                // TODO Give error if someone enter wrong information
                // TODO pick good variable names
                System.out.println("Please enter the fallowing information respectively");
                System.out.println("Name: ");
                String newName = scanner.next();
                System.out.println("Surname: ");
                String newSurname = scanner.next();
                System.out.println("Age: ");
                int newAge = scanner.nextInt();
                System.out.println("Email: ");
                String newEmail = scanner.next();
                System.out.println("Username: ");
                String  newUsername = scanner.next();
                System.out.println("Password: ");
                String newPassword = scanner.next();
                System.out.println("TC Number: ");
                int newTC_NO = scanner.nextInt();
                 employee = new Employee(newName, newSurname, newUsername, newPassword, newAge, newEmail, newTC_NO);

                break;
            case 2:
                boolean entered = false;
                System.out.println("Employee Username: ");
                String employeeName = scanner.next();
                System.out.println("Employee Password: ");
                String employeePassword = scanner.next();


                  ResultSet employeeRS = st.executeQuery(employeeLoginQuery);

                while(employeeRS.next()){
                    _username = employeeRS.getString("username");
                    _password = employeeRS.getString("password");
                    _name = employeeRS.getString("name");
                    if(employeeName.equals(_username) & employeePassword.equals(_password)){
                        System.out.println("Welcome Employee: " + _name );
                        entered = true;
                        break;
                    }
                }
                if(!entered){
                    System.out.println("Wrong username / password");
                    System.exit(0);
                    // TODO loop until user chooses register (if it is the third time user enters wrong information exit from the system.)
                }
                break;

            case 3:
                System.out.println("Username: ");
                String managerName = scanner.next();
                System.out.println("Password: ");
                String managerPassword = scanner.next();

                ResultSet managerRS = st.executeQuery(managerLoginQuery);
                while(managerRS.next()){
                    _username = managerRS.getString("username");
                    _password = managerRS.getString("password");
                    _name = managerRS.getString("name");
                    if(managerName.equals(_username) & managerPassword.equals(_password)){
                        System.out.println("Welcome Manager: " + _name );
                    }
                }
                break;
            case 4:

                System.out.println("Username: ");
                String adminName = scanner.next();
                System.out.println("Password: ");
                String adminPassword = scanner.next();

                ResultSet adminRS = st.executeQuery(adminLoginQuery);
                while(adminRS.next()){
                    _username = adminRS.getString("username");
                    _password = adminRS.getString("password");
                    _name = adminRS.getString("name");
                    _tc_no = adminRS.getInt(("tc_no"));
                    if(adminName.equals(_username) & adminPassword.equals(_password)){
                        System.out.println("Welcome Admin: " + _name );
                        administrator = new Administrator(_username, _password, _name, _tc_no);
                        _isAdmin = 1;
                    }
                }
                break;

            default:
                System.out.println("Wrong option");
                System.exit(0);
        }

        // TODO CASE 4
        if(_isAdmin == 1) {
        System.out.println("Press 1 to create new employees.. 2 to delete.. 3 to update ... 4 to check worksheet from employees");
        int selection = scanner.nextInt();
            switch (selection){
                case 1:

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
                    String  newUsername = scanner.next();
                    System.out.println("Password: ");
                    String newPassword = scanner.next();

                    administrator.createEmployee(newName, newSurname, newAge, newEmail, newUsername, newPassword, newTC_NO);
                    break;

                case 2:
                    // TODO instead of entering username and password create an are in sql called TC kimlik
                    System.out.println("Enter the TC NO of the employee you want to delete.");
                    System.out.println("TC NO: ");
                    int tc_no = scanner.nextInt();
                    administrator.deleteEmployee(tc_no);
                    // TODO loop until user chooses register (if it is the third time user enters wrong information exit from the system.)

                    break;
                case 3:
                    // TODO instead of entering username and password create an are in sql called TC kimlik
                    // TODO update part must be in a loop that where user updates as needed
                    System.out.println("Enter the username and password of the employee you want to update.");
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
                    break;
            }
        }
    }

    public static void updateSQL(String update, String _username, String _password) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        PreparedStatement preparedStatement;
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        if(update.equals("name")){
            System.out.println("Enter the new name");
            String name = scanner.next();
            String updateName = "UPDATE employee set name = ? WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(updateName);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, _username);
            preparedStatement.setString(3, _password);
            preparedStatement.execute();
            System.out.println("Name updated! New name: " + name );
        }
        if(update.equals("surname")){
            System.out.println("Enter the new surname");
            String surname = scanner.next();
            String updateName = "UPDATE employee set surname = ? WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(updateName);
            preparedStatement.setString(1, surname);
            preparedStatement.setString(2, _username);
            preparedStatement.setString(3, _password);
            preparedStatement.execute();
            System.out.println("Name updated! New surname: " + surname);
        }
        if(update.equals("username")){
            System.out.println("Enter the new username");
            String username = scanner.next();
            String updateName = "UPDATE employee set username = ? WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(updateName);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, _username);
            preparedStatement.setString(3, _password);
            preparedStatement.execute();
            System.out.println("Name updated! New username: " + username);
        }
        if(update.equals("password")){
            System.out.println("Enter the new password");
            String password = scanner.next();
            String updateName = "UPDATE employee set password = ? WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(updateName);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, _username);
            preparedStatement.setString(3, _password);
            preparedStatement.execute();
            System.out.println("Name updated! New password: " + password);
        }
        if(update.equals("age")){
            System.out.println("Enter the new age");
            String password = scanner.next();
            String age = "UPDATE employee set age = ? WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(age);
            preparedStatement.setString(1, age);
            preparedStatement.setString(2, _username);
            preparedStatement.setString(3, _password);
            preparedStatement.execute();
            System.out.println("Name updated! New age: " + age);
        }
        if(update.equals("email")){
            System.out.println("Enter the new email");
            String email = scanner.next();
            String age = "UPDATE employee set email = ? WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(age);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, _username);
            preparedStatement.setString(3, _password);
            preparedStatement.execute();
            System.out.println("Name updated! New email: " + email);
        }
        if(update.equals("tc_no")){
            System.out.println("Enter the new isAdmin 0 or 1");
            int tc_no = scanner.nextInt();
            String age = "UPDATE employee set tc_no = ? WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(age);
            preparedStatement.setInt(1, tc_no);
            preparedStatement.setString(2, _username);
            preparedStatement.setString(3, _password);
            preparedStatement.execute();
            System.out.println("Name updated! New tc no: " + tc_no);
        }
    }

}



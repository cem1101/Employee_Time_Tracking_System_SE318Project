import java.sql.*;
import java.util.Scanner;


public class main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) throws SQLException {

        //users.deleteByTC("444");
        //users.updateProcedure();


        System.out.println("Welcome!");
        System.out.println("Enter 1 for registration");
        System.out.println("Enter 2 for login ");
        System.out.println("Enter 3 for quit");

        int answer = scanner.nextInt();

        boolean mainLoop = true;
        while (mainLoop) {
            switch (answer) {
                case 1:
                    users.registerProcedure();
                    break;
                case 2:
                    if (users.loginProcedure()) {
                        mainLoop = false;
                        break;
                    }
                    break;
                case 3:

                    System.out.println("Bye Bye");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Wrong option, please try again.");
                    System.exit(0);
            }
        }


        // redirection as authgroup
        switch (users._authgroup) {
            // employee
            case 1:
                // call employee operations menu
                break;

            // manager
            case 2:
                // call manager operations menu
                break;
            // admin
            case 3:
                // call admin operations menu
                break;

            default:
                System.out.println("you are unknown, please contact your technical personal.");
                System.exit(0);
        }


    }
}
/*
    public static void updateSQL(String update, String _username, String _password) throws SQLException {
        /*
        * TODO All colms will gives to users, if he or she want to change,
        *       enter new value, otherwise he or she passes blank
        *       and its value applied as default which is in db
        * *//*
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

}*/



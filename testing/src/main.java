import java.sql.*;
import java.util.Scanner;


public class main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) throws SQLException {

        System.out.println("Welcome!");
        System.out.println("Enter 1 for registration");
        System.out.println("Enter 2 for login ");
        System.out.println("Enter 3 for quit");

        int answer = scanner.nextInt();

        boolean mainLoop = true;
        while (mainLoop) {
            switch (answer) {
                case 1:
                    Users.registerProcedure();
                    mainLoop = false;
                    break;
                case 2:
                    if (Users.loginProcedure()) {
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
        switch (Users._authgroup) {
            // employee
            case 1:
                // call employee operations menu
                String tcNo = Users._tcNo;
                EmployeeOperations employeeOperations = new EmployeeOperations();
                employeeOperations.main(tcNo);
                break;

            // manager
            case 2:
                // call manager operations menu

                break;
            // admin
            case 3:
                // call admin operations menu
                adminOperations adminOperations = new adminOperations();
                adminOperations.main();
                break;

            default:
                System.out.println("you are unknown, please contact your technical personal.");
                System.exit(0);
        }
    }
}




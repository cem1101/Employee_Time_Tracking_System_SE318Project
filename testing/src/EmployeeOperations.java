import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeOperations {
    public Scanner scanner = new Scanner(System.in);
    public void main() throws SQLException {
        // TODO format
        boolean isContinue = true;
        while(isContinue) {
            // Ask the employee to what he/she wants to do.
            System.out.println("Press 1 to see all worksheets you are responsible for.. 2 to send worksheet to admin.. 3 to edit worksheets 4 to exit");
            switch (scanner.nextInt()) {
                case 1:
                    checkWorksheets();
                    break;
                case 2:
                    submitWorksheet();
                    break;
                case 3:
                    editWorksheet();
                    break;
                case 4:
                    System.exit(1);
                    break;
            }
        }
    }
    private void checkWorksheets() throws SQLException {
        Users _user = new Users();
        // Call checkEmployeeWS
        _user.checkEmployeeWS();
    }

    private void submitWorksheet() throws SQLException {
        Users _user = new Users();
        // Call submitEmployeeWS
        _user.submitEmployeeWS();
    }

    private void editWorksheet() {
        Users _user = new Users();
        // Call editWorksheet
        _user.editWorksheet();
    }
}

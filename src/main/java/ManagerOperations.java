import java.sql.SQLException;
import java.util.Scanner;

public class ManagerOperations {
    public Scanner scanner = new Scanner(System.in);

    public void main() throws SQLException {
        // TODO format
        boolean isContinue = true;
        while(isContinue) {
            // Ask the manager to what he/she wants to do.
            System.out.println("Press 1 to see check worksheets needed to be approved.. \n2 to edit worksheets.. \n3 to exit");
            switch (scanner.nextInt()) {
                case 1:
                    approveWorksheets();
                    break;
                case 2:
                    editWorkSheets();
                    break;
                case 3:
                    System.exit(1);
                    break;
            }
        }
    }

    private void editWorkSheets() throws SQLException {
        Users _user = new Users();
        _user.editWorksheet();
    }

    private void approveWorksheets() throws SQLException {
        Users _user = new Users();
        // Call approveWS method
        _user.approveWS();
    }
}

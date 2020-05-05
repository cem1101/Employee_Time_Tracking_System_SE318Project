import java.sql.SQLException;
import java.util.Scanner;

public class ManagerOperations {
    private String _tcNo;

    public Scanner scanner = new Scanner(System.in);
    public void main() throws SQLException {

        // TODO format
        boolean isContinue = true;
        while(isContinue) {
            System.out.println("Press 1 to see check worksheets needed to be approved.. 2 to edit worksheets.. 3 to exit");
            switch (scanner.nextInt()) {
                case 1:
                    approveWorksheets();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    System.exit(1);
                    break;
            }
        }
    }

    public void approveWorksheets() throws SQLException {
        Users _user = new Users();
        _user.approveWS();
    }
}

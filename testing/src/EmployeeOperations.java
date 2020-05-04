import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeOperations {
    private String _tcNo;
    public Scanner scanner = new Scanner(System.in);
    public void main(String tcNo) throws SQLException {
        this._tcNo = tcNo;
        // TODO format
        boolean isContinue = true;
        while(isContinue) {
            System.out.println("Press 1 to see all worksheets you are responsible for.. 2 to send worksheet to admin.. 3 to edit worksheets 4 to exit");
            switch (scanner.nextInt()) {
                case 1:
                    checkWorksheets(_tcNo);
                    break;
                case 2:
                    submitWorksheet(_tcNo);
                    break;
                case 3:
                    editWorksheet(_tcNo);
                    break;
                case 4:
                    System.exit(1);
                    break;
            }
        }
    }

    public void checkWorksheets(String _tcNo) throws SQLException {
        Users _user = new Users();
        _user.checkEmployeeWS(_tcNo);
    }

    public void submitWorksheet(String _tcNo) throws SQLException {
        Users _user = new Users();
        _user.submitEmployeeWS(_tcNo);
    }

    public void editWorksheet(String _tcNo) throws SQLException {
        Users _user = new Users();
        _user.editWorksheet(_tcNo);
    }
}

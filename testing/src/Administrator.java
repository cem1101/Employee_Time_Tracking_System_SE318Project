import java.sql.*;
import java.util.Scanner;

public class Administrator {
    private String _username;
    private String _password;
    private String name;
    private int _tc_no;

    private Scanner scanner = new Scanner(System.in);
    private db databaseConnection = new db();
    private Connection connection = databaseConnection.connect();
    private Statement st = connection.createStatement();

    private String creatingEmployeeQuery = "INSERT INTO employee ( name, surname, username, password, age, email, tc_no) " + "VALUES ( ? , ?, ? , ?, ?, ?, ?)";
    private String deletingEmployeeQuery = "DELETE FROM employee WHERE tc_no= ?";
    private String employeeQuery = "SELECT *  FROM employee";

    public Administrator(String _username, String _password, String name, int tc_no) throws SQLException {
        this._username = _username;
        this._password = _password;
        this.name = name;
        this._tc_no = tc_no;
    }


    //TODO update user
    public void updateEmployee(){

    }

    public void deleteEmployee(int tc_no) throws SQLException {
        boolean deleted = false;

        ResultSet rs_delete = st.executeQuery(employeeQuery);
        while(rs_delete.next()){
            int employee_tc_no = rs_delete.getInt("tc_no");
            if(tc_no == employee_tc_no){
                PreparedStatement preparedStatement1 = connection.prepareStatement(deletingEmployeeQuery);
                preparedStatement1.setInt(1, tc_no);
                System.out.println("User Deleted");
                preparedStatement1.execute();
            }
        }if(!deleted){
            System.out.println("There is no user with that tc_no: " + tc_no);
        }
    }

    public void createEmployee(String newName, String newSurname, int newAge, String newEmail, String  newUsername, String newPassword, int tc_no  ) throws SQLException {
        /* Creating new employee */
        PreparedStatement preparedStatement = connection.prepareStatement(creatingEmployeeQuery);
        preparedStatement.setString(1, newName);
        preparedStatement.setString(2, newSurname);
        preparedStatement.setString(3, newUsername);
        preparedStatement.setString(4, newPassword);
        preparedStatement.setInt(5, newAge);
        preparedStatement.setString(6, newEmail);
        preparedStatement.setInt(7, tc_no);
        preparedStatement.execute();
        System.out.println("User created!");



    }
    //TODO approve or disapprove worksheet
    public String approveWorksheet(){

        return null;
    }






}

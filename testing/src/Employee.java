import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Employee {

    private static String _name;
    private static String _surname;
    private static String _username;
    private static String _password;
    private static int _age;
    private static String _email;
    private static String _lastLogin;
    private static int _tc_no;
    private Scanner scanner = new Scanner(System.in);
    private db db = new db();
    private Connection connection = db.connect();
    private String creatingEmployeeQuery = "INSERT INTO employee ( name, surname, username, password, age, email, tc) " + "VALUES ( ? , ?, ? , ?, ?, ?, ?)";


    public Employee(String newName, String newSurname, String newUsername, String newPassword, int newAge, String newEmail, int newTC_NO  ) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(creatingEmployeeQuery);
        preparedStatement.setString(1, newName);
        preparedStatement.setString(2, newSurname);
        preparedStatement.setString(3, newUsername);
        preparedStatement.setString(4, newPassword);
        preparedStatement.setInt(5, newAge);
        preparedStatement.setString(6, newEmail);
        preparedStatement.setInt(7, newTC_NO);
        preparedStatement.execute();

        _name = newName;
        _surname = newSurname;
        _username = newSurname;
        _password = newPassword;
        _age = newAge;
        _email = newEmail;
        _tc_no = newTC_NO;
    }

    //TODO submit worksheet
  //  public Worksheets submitWorksheet(Worksheets ws){
   //     return ws;
 //   }


    public static String get_name() {
        return _name;
    }

    public static void set_name(String _name) {
        Employee._name = _name;
    }

    public static String get_surname() {
        return _surname;
    }

    public static void set_surname(String _surname) {
        Employee._surname = _surname;
    }

    public static String get_username() {
        return _username;
    }

    public static void set_username(String _username) {
        Employee._username = _username;
    }

    public static String get_password() {
        return _password;
    }

    public static void set_password(String _password) {
        Employee._password = _password;
    }

    public static int get_age() {
        return _age;
    }

    public static void set_age(int _age) {
        Employee._age = _age;
    }

    public static String get_email() {
        return _email;
    }

    public static void set_email(String _email) {
        Employee._email = _email;
    }

    public static String get_lastLogin() {
        return _lastLogin;
    }

    public static void set_lastLogin(String _lastLogin) {
        Employee._lastLogin = _lastLogin;
    }

    public static int get_tc_no() {
        return _tc_no;
    }

    public static void set_tc_no(int _tc_no) {
        Employee._tc_no = _tc_no;
    }



}

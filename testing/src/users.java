import java.sql.*;
import java.util.Scanner;

public class users{


    public static int _id;
    public static int _authgroup;
    public static String _name;
    public static String _surname;
    public static String _username;
    public static int _age;
    public static String _email;
    public static String _tcNo;


    public static void main(String args[]){

    }



    public static void delete(String tcNumber) throws SQLException {

    }


    public static void register(String newName, String newSurname, String newUsername, String newPassword, int newAge, String newEmail, int newTC_NO  ) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employee ( name, surname, username, password, age, email, tc) " + "VALUES ( ? , ?, ? , ?, ?, ?, ?)");
        preparedStatement.setString(1, newName);
        preparedStatement.setString(2, newSurname);
        preparedStatement.setString(3, newUsername);
        preparedStatement.setString(4, newPassword);
        preparedStatement.setInt(5, newAge);
        preparedStatement.setString(6, newEmail);
        preparedStatement.setInt(7, newTC_NO);
        preparedStatement.execute();
    }



    public static void registerProcedure()  throws  SQLException{
        // TODO Give error if someone enter wrong information
        // TODO pick good variable names
        Employee employee = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the fallowing information respectively");
        System.out.print("Name: ");
        String newName = scanner.next();
        System.out.print("Surname: ");
        String newSurname = scanner.next();
        System.out.print("Age: ");
        int newAge = scanner.nextInt();
        System.out.print("Email: ");
        String newEmail = scanner.next();
        System.out.print("Username: ");
        String  newUsername = scanner.next();
        System.out.print("Password: ");
        String newPassword = scanner.next();
        System.out.print("TC Number: ");
        int newTC_NO = scanner.nextInt();
        users.register(newName, newSurname, newUsername, newPassword, newAge, newEmail, newTC_NO);
        System.out.println("Registration success. Please login.");
    }

    public static boolean login(String username, String password) throws  SQLException{
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        Statement st = connection.createStatement();
        String usernameTmp,passwordTmp;
        ResultSet managerRS = st.executeQuery("SELECT * FROM users");
        while(managerRS.next()){
            usernameTmp = managerRS.getString("username");
            passwordTmp = managerRS.getString("password");
            if(username.equals(usernameTmp) & password.equals(passwordTmp)){
                _id = Integer.parseInt(managerRS.getString("id"));
                _authgroup = Integer.parseInt(managerRS.getString("authgroup"));
                _name = managerRS.getString("name");
                _surname = managerRS.getString("surname");
                _username = managerRS.getString("username");
                _age = Integer.parseInt(managerRS.getString("age"));
                _email = managerRS.getString("email");
                _tcNo = managerRS.getString("tc");

                return true;
            }
        }
        return false;
    }

    public static String getAuthGroup(){
        switch(_authgroup){
            case 1:return "employee";
            case 2:return "manager";
            case 3:return "admin";
        }
        return "unknown";
    }


    public static boolean loginProcedure()  throws  SQLException{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();
        if(login(username,password)){
            System.out.println("logged successfully "+_name+" "+_surname+". You are a "+getAuthGroup()+".");
            return true;
        }else{
            System.out.println("invalid username or password.");
            return false;
        }
    }
}

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


public class users{


    static class user{
        public static String[] fields = {"authgroup","name","surname","password","username","age","email","tcNo"};

        public static int id;
        public static int authgroup;
        public static String name;
        public static String surname;
        public static String password;
        public static String username;
        public static int age;
        public static String email;
        public static String tcNo;

        public static boolean load(int _data){
            return load("id",""+_data);
        }
        public static boolean loadByTC(String _data){
            return load("tc",_data);
        }


        public static boolean load(String colm, String _data){
            try{

                PreparedStatement preparedStatement = db.connect().prepareStatement("SELECT * FROM users where ?=?");
                preparedStatement.setString(1, colm);
                preparedStatement.setString(2, _data);
                ResultSet result = preparedStatement.executeQuery();
                if(result.next() == false){
                    return false;
                }
                id = result.getInt("id");
                authgroup = result.getInt("authgroup");
                age = result.getInt("age");
                tcNo = result.getString("tc");
                name = result.getString("name");
                surname = result.getString("surname");
                username = result.getString("username");
                password = result.getString("password");
                email = result.getString("email");
                return true;
            }catch(SQLException e){
                System.out.println(e.getMessage());
                return false;
            }
        }

        public static void create(){
            crud("insert into");
        }
        public static void update(){
            crud("update");
        }

        public static void delete(){

        }

        public static void crud(String type){
            String query;
            if(type == "delete"){
                query = "delete users where id=?";
                try {
                    PreparedStatement preparedStatement = db.connect().prepareStatement(query);
                    preparedStatement.setInt(1, id);

                    preparedStatement.execute();
                } catch (SQLException throwables) {
                    System.out.println(throwables.getMessage());
                    throwables.printStackTrace();
                }
            }else {
                query = type+" users set " +
                        "authgroup=?," +
                        "age=?," +
                        "tc=?," +
                        "name=?," +
                        "surname=?," +
                        "username=?," +
                        "password=?," +
                        "email=?" +
                        " where id = ?";
                try {
                    PreparedStatement preparedStatement = db.connect().prepareStatement(query);
                    preparedStatement.setInt(1, authgroup);
                    preparedStatement.setInt(2, age);
                    preparedStatement.setString(3, tcNo);
                    preparedStatement.setString(4, name);
                    preparedStatement.setString(5, surname);
                    preparedStatement.setString(6, username);
                    preparedStatement.setString(7, password);
                    preparedStatement.setString(8, email);
                    preparedStatement.setInt(9, id);

                    preparedStatement.execute();
                } catch (SQLException throwables) {
                    System.out.println(throwables.getMessage());
                    throwables.printStackTrace();
                }
            }




        }



    }




    public static int _id;
    public static int _authgroup;
    public static String _name;
    public static String _surname;
    public static String _username;
    public static int _age;
    public static String _email;
    public static String _tcNo;

    public static db db = new db();

    public static void main(String args[]){

    }




    public static void register(String newName, String newSurname, String newUsername, String newPassword, int newAge, String newEmail, int newTC_NO  ) throws SQLException {

        Connection connection = db.connect();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users ( name, surname, username, password, age, email, tc) " + "VALUES ( ? , ?, ? , ?, ?, ?, ?)");
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
        
        Connection connection = db.connect();
        Statement st = connection.createStatement();
        String usernameTmp,passwordTmp;
        ResultSet managerRS = st.executeQuery("SELECT * FROM users");
        while(managerRS.next()){
            usernameTmp = managerRS.getString("username");
            passwordTmp = managerRS.getString("password");
            if(username.equals(usernameTmp) & password.equals(passwordTmp)){
                _id = managerRS.getInt("id");
                _authgroup = managerRS.getInt("authgroup");
                _name = managerRS.getString("name");
                _surname = managerRS.getString("surname");
                _username = managerRS.getString("username");
                _age = managerRS.getInt("age");
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







    public static boolean updateProcedure()  throws  SQLException{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Select user by TC Number: ");
        String searchTC = scanner.nextLine();
        

        PreparedStatement preparedStatement = db.connect().prepareStatement("SELECT * FROM users where tc=?");
        preparedStatement.setString(1, searchTC);
        ResultSet result = preparedStatement.executeQuery();

        if(result.next() == false){
            System.out.println("there is no user!");
            return false;
        }

        System.out.println("You are updating "+result.getString("name"));
        int userID = Integer.parseInt(result.getString("id"));
            user _user = new user();
            _user.load(userID);
            System.out.println("Enter new values, if do not want to change related field, pass the field blank.");
            for (String row : user.fields)
            {
                System.out.print(row+"\t:");
                String tmp = scanner.nextLine();
                if(tmp.length() > 0){
                    switch(row){
                        case "id":			_user.id = Integer.parseInt(tmp);break;
                        case "authgroup":	_user.authgroup = Integer.parseInt(tmp);break;
                        case "name":		_user.name = tmp;break;
                        case "surname":		_user.surname = tmp;break;
                        case "password":	_user.password = tmp;break;
                        case "username":	_user.username = tmp;break;
                        case "age":			_user.age = Integer.parseInt(tmp);break;
                        case "email":		_user.email = tmp;break;
                        case "tcNo":		_user.tcNo = tmp;break;
                    }
                }
            }
            _user.update();
            System.out.print("revisions have been saved.");
            System.out.println(result);




            System.exit(0);
            return true;

    }

    public static boolean deleteByTC(String tc){
        user _user = new user();
        if(_user.loadByTC(tc) == false){
            return false;
        };
        _user.delete();
         return true;
    }
    public static boolean deleteByID(int id){
        user _user = new user();
        _user.load(id);
        _user.delete();
        return true;
    }




}


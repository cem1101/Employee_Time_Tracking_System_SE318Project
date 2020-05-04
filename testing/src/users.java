import java.sql.*;
import java.util.Scanner;


class Users {

    static class User {
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
            String qq = "SELECT * FROM users where "+ colm + "=" + _data;
            try {
                Connection connection = db.connect();
                Statement st = connection.createStatement();
                ResultSet load = st.executeQuery(qq);

                while(load.next()){
                    id = load.getInt("id");
                    authgroup = load.getInt("authgroup");
                    name = load.getString("name");
                    surname = load.getString("surname");
                    username = load.getString("username");
                    password = load.getString("password");
                    age = load.getInt("age");
                    email = load.getString("email");
                    tcNo = load.getString("tc");
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

        public static void create(){
            crud("insert into");
        }
        public static void update(){
            crud("update");
        }

        public static void delete()  {
            crud("delete");

        }

        public static void crud(String type){
            String query;
            if(type == "delete"){
                query = "delete FROM users where tc=?";
                try {
                    PreparedStatement preparedStatement = db.connect().prepareStatement(query);
                    preparedStatement.setString(1, tcNo);
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

        // gets the user info so when employee submitting a worksheet only need to fill part is Content other information filled automatically
        public void submitEmployeeWorksheet(User user) throws SQLException {
            Scanner scanner = new Scanner(System.in);
            Connection connection = db.connect();
            String query = "INSERT INTO worksheets ( responsible, responsibleID,content, status)"  + "VALUES (?, ? , ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.name);
            System.out.println("name " + user.name);
            preparedStatement.setInt(2, user.id);
            System.out.println("What is the content of worksheet? ");
            String content = scanner.next();
            preparedStatement.setString(3, content);
            preparedStatement.setInt(4, 0);
            preparedStatement.execute();
            System.out.println("Worksheet submitted");
        }

        public void loadEmployeeWorksheet(User user) throws SQLException  {
            String query = "Select * FROM worksheets Where responsibleID = "+  user.id;

            Connection connection = db.connect();
            Statement st = connection.createStatement();
            ResultSet loadWorksheets = st.executeQuery(query);
            while(loadWorksheets.next()){
                int id = loadWorksheets.getInt("ID");
                String responsible = loadWorksheets.getString("responsible");
                int responsibleID = loadWorksheets.getInt("responsibleID");
                String admin = loadWorksheets.getString("admin");
                String manager = loadWorksheets.getString("manager");
                String content = loadWorksheets.getString("content");
                int status = loadWorksheets.getInt("status");
                System.out.printf("ID: %d%nResponsible: %s%nResponsibleID: %d%nAdmin: %s%nManager: %s%nContent: %s%nStatus: %d%n",id,responsible,responsibleID,admin,manager,content,status);
                System.out.printf("--------------------------------------------------------------------------------------------------------------------------------------------------------%n");
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

    public static void register(int newAuthgroup, String newName, String newSurname, String newUsername, String newPassword, int newAge, String newEmail, int newTC_NO  ) throws SQLException {
        Connection connection = db.connect();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users ( authgroup, name, surname, username, password, age, email, tc) " + "VALUES ( ?,? , ?, ? , ?, ?, ?, ?)");
        preparedStatement.setInt(1, newAuthgroup);
        preparedStatement.setString(2, newName);
        preparedStatement.setString(3, newSurname);
        preparedStatement.setString(4, newUsername);
        preparedStatement.setString(5, newPassword);
        preparedStatement.setInt(6, newAge);
        preparedStatement.setString(7, newEmail);
        preparedStatement.setInt(8, newTC_NO);
        preparedStatement.execute();
    }



    public static void registerProcedure()  throws  SQLException{
        // TODO Give error if someone enter wrong information
        // TODO pick good variable names
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the fallowing information respectively");
        System.out.print("Authgroup: ");
        int newAuthgroup = scanner.nextInt();
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
        Users.register(newAuthgroup, newName, newSurname, newUsername, newPassword, newAge, newEmail, newTC_NO);
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
        System.out.print("Enter the TC  of the employee you want to update. ");
        String searchTC = scanner.next();
        

        PreparedStatement preparedStatement = db.connect().prepareStatement("SELECT * FROM users where tc=?");
        preparedStatement.setString(1, searchTC);
        ResultSet result = preparedStatement.executeQuery();

        if(result.next() == false){
            System.out.println("there is no user!");
            return false;
        }

        System.out.println("You are updating "+result.getString("name"));
        String tcNo = result.getString("tc");
            User _user = new User();
            _user.loadByTC(tcNo);
            System.out.println("Enter new values, if do not want to change related field, pass the field blank.");
            for (String row : User.fields)
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
            return true;

    }



   public static void deleteByTC(String tc) throws SQLException {
        User _user = new User();
        _user.loadByTC(tc);
         _user.delete();
    }

    public static void submitEmployeeWS(String tcNo) throws SQLException {
        User _user = new User();
        _user.loadByTC(tcNo);
        _user.submitEmployeeWorksheet(_user);
    }

    public static void checkEmployeeWS(String tcNo) throws SQLException{
        User _user = new User();
        _user.loadByTC(tcNo);

        _user.loadEmployeeWorksheet(_user);

    }

    public void editWorksheet(String tcNo) throws SQLException {
        //TODO Edit worksheet by everyone

    }
 /*  public static boolean deleteByID(int id){
        user _user = new user();
        _user.load(id);
        System.out.println(_user.name);
        _user.delete();
        return true;
    } */


// private String creatingEmployeeQuery = "INSERT INTO employee ( name, surname, username, password, age, email, tc_no) " + "VALUES ( ? , ?, ? , ?, ?, ?, ?)";
//    private String deletingEmployeeQuery = "DELETE FROM employee WHERE tc_no= ?";
//    private String employeeQuery = "SELECT *  FROM employee";

}


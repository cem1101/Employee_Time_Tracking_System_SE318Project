import java.sql.*;
import java.util.Scanner;


class Users {


    static class User {
        // All the field in the users table in the DATABASE
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



      /*  public static boolean load(int _data){
            return load("id",""+_data);
        }*/

        private static boolean loadByTC(String _data) {
            // Call the load method with the type "TC" and wanted "data"
            return load("tc",_data);
        }

        private static boolean load(String colm, String _data){
            // Query that takes the user from DB with the wanted Column name and data
            String qq = "SELECT * FROM users where "+ colm + "=\"" + _data+"\"";

            try {
                // Connect database
                Connection connection = db.connect();
                // Create statement
                Statement st = connection.createStatement();
                // Execute query into resultset
                ResultSet load = st.executeQuery(qq);

                // If there is a user with wanted information call the user and load all the information.
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
                // Catch and print an error message if there is any exception
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

        private static void create(){
            // Call crud method with type "insert into"
            crud("insert into");
        }
        private static void update(){
            // Call crud method with type "update"
            crud("update");
        }

        private static void delete()  {
            // Call crud method with type "delete"
            crud("delete");

        }

        private static void crud(String type){
            String query;
            // Check if the type is delete or not
            if(type.equals("delete")){
                // Write to query that deletes user with selected "TC" number
                query = "delete FROM users where tc=?";
                try {
                    // Create prepared statement with query that has missing values
                    PreparedStatement preparedStatement = db.connect().prepareStatement(query);
                    // Set the missing value
                    preparedStatement.setString(1, tcNo);
                    // Execute query
                    preparedStatement.execute();
                    //  Catch for errors
                } catch (SQLException throwables) {
                    System.out.println(throwables.getMessage());
                    throwables.printStackTrace();
                }
                // If type is not delete
            }else {
                // Create to query for update or insert to new user
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
                    // Create the prepared statement with the query
                    PreparedStatement preparedStatement = db.connect().prepareStatement(query);
                    // Set all the missing values in the query
                    preparedStatement.setInt(1, authgroup);
                    preparedStatement.setInt(2, age);
                    preparedStatement.setString(3, tcNo);
                    preparedStatement.setString(4, name);
                    preparedStatement.setString(5, surname);
                    preparedStatement.setString(6, username);
                    preparedStatement.setString(7, password);
                    preparedStatement.setString(8, email);
                    preparedStatement.setInt(9, id);
                    // Execute the query
                    preparedStatement.execute();
                    //  Catch for errors
                } catch (SQLException throwables) {
                    System.out.println(throwables.getMessage());
                    throwables.printStackTrace();
                }
            }
        }

        // gets the user info so when employee submitting a worksheet only need to fill part is Content other information filled automatically
        private void submitEmployeeWorksheet() throws SQLException {
            System.out.println("Employee name: "+   _name);
            Scanner scanner = new Scanner(System.in);
            // Connect to the database
            Connection connection = db.connect();
            // Write query to add new worksheet to the database
            String query = "INSERT INTO worksheets ( responsible, responsibleID,content, status)"  + "VALUES (?, ? , ?, ?)";
            // Create prepared statement with the query
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            // Set missing values in the query (these are the name and _id of the user that submits the worksheets)
            preparedStatement.setString(1, _name);
            preparedStatement.setInt(2, _id);
            // Take the content of the new worksheet
            System.out.println("What is the content of worksheet? ");
            String content = scanner.next();
            // Set the content
            preparedStatement.setString(3, content);
            preparedStatement.setInt(4, 0);
            // Execute query
            preparedStatement.execute();
            System.out.println("Worksheet submitted");
        }

        private void loadEmployeeWorksheet( ) throws SQLException  {
            // Query that calls worksheets only with the associated user ID
            String query = "Select * FROM worksheets Where responsibleID = "+  _id;
            // Connect database
            Connection connection = db.connect();
            // Create statement for the query
            Statement st = connection.createStatement();
            // Execute query into result set
            ResultSet loadWorksheets = st.executeQuery(query);
            // Until there is no more worksheet print all the information
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


        private void approveWorksheets() throws SQLException {
            Scanner scanner = new Scanner(System.in);
            // Write query for the taking all the worksheets that are not approved or wanted to revised.
            String query = "Select * FROM worksheets Where status = 0 or status = 2";
            //TODO Make a method just for loading worksheet info so that loadEmployeeWorksheet and approveWorksheets
            // TODO can use it.
            //Connect database
            Connection connection = db.connect();
            // Create the statement without missing values
            Statement st = connection.createStatement();
            // Execute the query
            ResultSet loadWorksheets = st.executeQuery(query);
            System.out.println("These are the available worksheets right now:");
            // Take and print all the worksheet information until there is no more.
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
            int wsID;
            // Until the wanted worksheet founded make a loop
            while(true) {
                // Take the worksheet ID that admin or manager wants to change
                System.out.println("Write the worksheet ID you want to approved or revised");
                wsID = scanner.nextInt();
                // Query that calls worksheet with selected ID value
                String query2 = "Select * FROM worksheets Where ID = " + wsID;
                // Execute query
                ResultSet approveWorksheets = st.executeQuery(query2);
                // IF there is no worksheet with wanted ID give an error message
                if (!approveWorksheets.next()) {
                    System.out.println("there is no user!");
                }else
                    break;
            }
            // Take the option if worksheet is approved or needs to be revised.
            System.out.println("If you want to approve it press 1.. if you want it to be revised press 2");
            int choice = scanner.nextInt();
            // TODO make an update worksheet method so that admin and manager can use both
            // Write the query that calls worksheet with wanted ID then
            // Change the status with given value and after that change the manager name that
            // who changed the worksheet information
            String query3 = "update worksheets set manager = ?, status = ? where ID = " + wsID;
            // Create prepared statement because query have missing values
            PreparedStatement preparedStatement = connection.prepareStatement(query3);
            // Enter the missing values
            preparedStatement.setString(1, this.name);
            preparedStatement.setInt(2, choice);
            // Execute query
            preparedStatement.execute();
            System.out.println("Your choice have been done! ");
        }
    }



    // User values in the DB
    public static int _id;
    public static int _authgroup;
    public static String _name;
    public static String _surname;
    public static String _username;
    public static int _age;
    public static String _email;
    public static String _tcNo;

    public static db db = new db();

    public static boolean register(int newAuthgroup, String newName, String newSurname, String newUsername, String newPassword, int newAge, String newEmail, String newTC_NO  ) throws SQLException {
        if(newAge>200){
            return false;
        }

        if(newAge<18){
            return false;
        }

        if(newTC_NO.length() != 11){
            return false;
        }

        if(newAuthgroup != 1 && newAuthgroup!=2 && newAuthgroup!=3){
            return false;
        }


        if(newEmail.indexOf("@") == -1){
            return false;
        }
        if(newEmail.indexOf(".") == -1){
            return false;
        }

        int checkCounter = 0;
        for (int i = 0; i < newEmail.length(); i++) {
            if (newEmail.charAt(i) == '@') {
                checkCounter++;
            }
        }
        if(checkCounter>1){
            return false;
        }



        //Connect database
        Connection connection = db.connect();
        // Create prepared statement for sql query
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users ( authgroup, name, surname, username, password, age, email, tc) " + "VALUES ( ?,? , ?, ? , ?, ?, ?, ?)");
        // Set all the missing values in the query
        preparedStatement.setInt(1, newAuthgroup);
        preparedStatement.setString(2, newName);
        preparedStatement.setString(3, newSurname);
        preparedStatement.setString(4, newUsername);
        preparedStatement.setString(5, newPassword);
        preparedStatement.setInt(6, newAge);
        preparedStatement.setString(7, newEmail);
        preparedStatement.setString(8, newTC_NO);
        // Execute the query
        preparedStatement.execute();
        return true;
    }







    public static void registerProcedure()  throws  SQLException{
        // TODO Give error if someone enter wrong information
        // TODO pick good variable names

        //Take all the information from the user
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Please enter the fallowing information respectively");
        //System.out.print("Authgroup: ");
        //int newAuthgroup = scanner.nextInt();
        boolean pass=false;
        String newName="",newSurname="",tmp;
        int newAuthgroup=1,newAge = 0;
/*
        // asks users her or his name while he/she enter in correct format
        do {
            pass=true;
            System.out.print("Name: ");
            tmp = scanner.next();
            if(tmp.length() < 3 || tmp.length() > 10){
                pass=false;
                System.out.println("Your name should be longer than 2 characters and shorter than 10 character!");
            }else{
                newName = tmp;
                pass=true;
            }
        }while(!pass);


        // asks users her or his surname while he/she enter in correct format
        do {
            pass=true;
            System.out.print("Surname: ");
            tmp = scanner.next();
            if(tmp.length() < 3 || tmp.length() > 10){
                pass=false;
                System.out.println("Your Surname should be longer than 2 characters and shorter than 10 character!");
            }else{
                newSurname = tmp;
                pass=true;
            }
        }while(!pass);

        */


        // asks users her or his age while he/she enter in correct format
        do {
            pass=true;
            System.out.print("Age: ");
            tmp = scanner.next();
            if(!validationFunctions.isNumeric(tmp)){
                pass=false;
                System.out.println("Your Age should be numeric, longer than 2 characters and shorter than 10 character!");
            }else{
                newAge = Integer.parseInt(tmp);
                pass=true;
            }
        }while(!pass);




        System.out.print("Email: ");
        String newEmail = scanner.next();
        System.out.print("Username: ");
        String  newUsername = scanner.next();
        System.out.print("Password: ");
        String newPassword = scanner.next();
        System.out.print("TC Number: ");
        String newTC_NO = scanner.next();

        //Call user register method to save information to database
        Users.register(newAuthgroup, newName, newSurname, newUsername, newPassword, newAge, newEmail, newTC_NO);
        System.out.println("Registration success. Please login.");
    }

    public static boolean login(String username, String password) throws  SQLException{
        // Connect database
        Connection connection = db.connect();
        // Create statement that have no missing value
        Statement st = connection.createStatement();
        String usernameTmp,passwordTmp;
        // Execute to query to call all the user information in users table
        ResultSet managerRS = st.executeQuery("SELECT * FROM users");
        // Take the information until it is end of the table
        while(managerRS.next()){
            // Only take the username and password with the incoming information
            usernameTmp = managerRS.getString("username");
            passwordTmp = managerRS.getString("password");
            // if the entered username and password equals to the database information
            // save all the information to use after in the operations
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

    // Check the user "authgroup" in the db and according that information
    // assign the correct user type
    public static String getAuthGroup(){
        switch(_authgroup){
            // three types of the user that can be
            case 1:return "employee";
            case 2:return "manager";
            case 3:return "admin";
        }
        return "unknown";
    }


    public static boolean loginProcedure()  throws  SQLException{
        Scanner scanner = new Scanner(System.in);
        // Take user name and password
        System.out.print("Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();
        // Check if the user is in the database
        // if yes print welcome message
        if(login(username,password)){
            System.out.println("logged successfully "+_name+" "+_surname+". You are a "+getAuthGroup()+".");
            return true;
        // if user is not in db print an error message
        }else{
            System.out.println("invalid username or password.");
            return false;
        }
    }

    public static boolean updateProcedure()  throws  SQLException{
        Scanner scanner = new Scanner(System.in);
        // Take the "TC" number of the user wanted to update
        System.out.print("Enter the TC  of the employee you want to update. ");
        String searchTC = scanner.next();
        
        // Create prepared statement that calls users with the selected "TC" number
        PreparedStatement preparedStatement = db.connect().prepareStatement("SELECT * FROM users where tc=?");
        // Set the missing values in the query
        preparedStatement.setString(1, searchTC);
        // Execute the query
        ResultSet result = preparedStatement.executeQuery();

        // If there is no user print error message
        if(!result.next()){
            System.out.println("there is no user!");
            return false;
        }

        System.out.println("You are updating "+result.getString("name"));
        String tcNo = result.getString("tc");
            User _user = new User();
            // load user with the selected "TC" number
            _user.loadByTC(tcNo);
            System.out.println("Enter new values, if do not want to change related field, pass the field blank.");
            for (String row : User.fields)
            {
                // Change all the values if there is an any update on them.
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
            // Call update method.
            _user.update();
            System.out.print("revisions have been saved.");
            System.out.println(result);
            return true;

    }



   public static void deleteByTC(String tc) throws SQLException {
       // Create temp user
       User _user = new User();
       // Load the user with selected TC number
        _user.loadByTC(tc);
        // Delete the user
         _user.delete();
    }

    public static void submitEmployeeWS() throws SQLException {
        // Create temp user
        User _user = new User();
        // Call the submitEmployeeWorksheet method
        _user.submitEmployeeWorksheet();
    }

    public static void checkEmployeeWS( ) throws SQLException{
        // Create temp user
        User _user = new User();
        // Call the loadEmployeeWorksheets method
        _user.loadEmployeeWorksheet();
    }

    public void editWorksheet() {
        //TODO Edit worksheet by everyone

    }

    public void approveWS() throws SQLException {
        // Create temp user
        User _user = new User();
        // Call the approveWorksheets method
        _user.approveWorksheets();
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


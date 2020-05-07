import org.junit.Assert;
import org.junit.Test;

import java.sql.*;

public class usersClassTests {
    db db = new db();
        private dummyDataFunctionsForTests tf = new dummyDataFunctionsForTests();
    Users users = new Users();

    /***
     * delete tc tests
      */

        @Test //Expected: Fail, if try to delete a user that doesn't exist
        public void deleteByTC_Test_Positive() throws SQLException {
            Users users = new Users();
            tf.createDummyRowToUsers();
            int oldAmount = Integer.parseInt(tf.getLastRowInsertedOnUsers().get("id").toString());
            users.deleteByTC("12345678901");
            int newAmount = Integer.parseInt(tf.getLastRowInsertedOnUsers().get("id").toString());
            Assert.assertTrue(oldAmount!=newAmount);
        }


       /* @Test //Expected: Fail, if try to delete a user that doesn't exist
        public void deleteByTC_Test_Negative() throws SQLException {
            Users users = new Users();
            tf.createDummyRowToUsers();
            int oldAmount = Integer.parseInt(tf.getLastRowInsertedOnUsers().get("id").toString());
            users.deleteByTC("abcdefg");
            int newAmount = Integer.parseInt(tf.getLastRowInsertedOnUsers().get("id").toString());

            tf.deleteLastCreatedRow();
            Assert.assertTrue(oldAmount==newAmount);
        }*/




    /***
     * user login tests
     */


    @Test // Expected: Success, if user can login to the system
    public void loginTestPositive() throws SQLException {
        boolean trueLogin = users.login("e","e");
        // Test: trueLogin is true
        Assert.assertEquals(true, trueLogin);
    }

    @Test // Expected: Success, if employee try to login as employee
    public void login_As_Employee_and_CheckForAuthgroupPositive() throws SQLException {
        users.login("e","e");
        int authgroup = users._authgroup;
        // Test: authgroup is 1
        Assert.assertEquals(1, authgroup);

    }

    @Test // Expected: Success, if manager try to login as manager
    public void login_As_Manager_and_CheckForAuthgroupPositive() throws SQLException {
        users.login("m","m");
        int authgroup = users._authgroup;
        // Test: authgroup is 2
        Assert.assertEquals(2,authgroup);
    }

    @Test // Expected: Success, if admin try to login as admin
    public void login_As_Admin_and_CheckForAuthgroupPositive() throws SQLException {
        users.login("123","123");
        int authgroup = users._authgroup;
        // Test: authgroup is 3
        Assert.assertEquals(3,authgroup);
    }

    /** These are the negative tests that should fail (Fail means, the program works as expected) */

    @Test // Expected: Fail, if a manager user try to login as employee or admin
    public void login_As_Manager_and_CheckForAuthgroupNegative1() throws SQLException {
        users.login("m","m");
        int authgroup = users._authgroup;
        // False values to check if they are coming or not
        Assert.assertNotSame(1,authgroup);
    }

    @Test // Expected: Fail, if a manager user try to login as employee or admin
    public void login_As_Manager_and_CheckForAuthgroupNegative2() throws SQLException {
        users.login("m","m");
        int authgroup = users._authgroup;
        // False values to check if they are coming or not
        Assert.assertNotSame(3,authgroup);
    }

    @Test // Expected: Fail, if a admin user try to login as employee or manager
    public void login_As_Admin_and_CheckForAuthgroupNegative1() throws SQLException {
        users.login("123","123");
        int authgroup = users._authgroup;
        // False values to check if they are coming or not
        Assert.assertNotSame(2,authgroup);

    }

    @Test // Expected: Fail, if a admin user try to login as employee or manager
    public void login_As_Admin_and_CheckForAuthgroupNegative2() throws SQLException {
        users.login("123","123");
        int authgroup = users._authgroup;
        // False values to check if they are coming or not
        Assert.assertNotSame(1,authgroup);
    }

    @Test // Expected: Fail, if a employee user try to login as manager or admin
    public void login_As_Employee_and_CheckForAuthgroupNegative1() throws SQLException {
        users.login("e","e");
        int authgroup = users._authgroup;
        // False values to check if they are coming or not
        Assert.assertNotSame(3,authgroup);
    }

    @Test // Expected: Fail, if a employee user try to login as manager or admin
    public void login_As_Employee_and_CheckForAuthgroupNegative2() throws SQLException {
        users.login("e","e");
        int authgroup = users._authgroup;
        // False values to check if they are coming or not
        Assert.assertNotSame(2,authgroup);
    }


    @Test // Expected: Fail: User uses wrong user name and password
    public void loginTestNegative() throws SQLException {
        boolean falseLogin = users.login("qwe","qwe");
        // Test: falseLogin is true
        Assert.assertEquals(false, falseLogin);
    }






    /***
     * user mail tests
     */


    @Test
    public void registerMethodTestNegativeForMailWithControlStatement1() throws SQLException {
        // System.out.println(getLastRowInsertedOnUsers().get("id"));
        Assert.assertFalse(tf.tryMailInput("canbagdiken.com"));
    }
    @Test
    public void registerMethodTestNegativeForMailWithControlStatement2() throws SQLException {
        Assert.assertFalse(tf.tryMailInput("can@@bagdiken.com"));
    }
    @Test
    public void registerMethodTestNegativeForMailWithControlStatement3() throws SQLException {
        Assert.assertFalse(tf.tryMailInput("can@bagdikencom"));
    }





    /***
     * user register tests
     */




    @Test // Expected: Success, if the user can register to the system
    public void registerMethodTestNegativeForTC() throws SQLException {
        Users user = new Users();
        user.register(3,
                "denemename",
                "denemesurname",
                "denemeusername",
                "denemeparola",
                999999,
                "deneme@mail.com",
                "12345678901" );

        Connection connection = db.connect();
        Statement st = connection.createStatement();
        String query = "SELECT * FROM users order by id desc limit 1";
        ResultSet results = st.executeQuery(query);
        results.next();
        Assert.assertNotSame(999999999,results.getInt("age"));
    }


    @Test // Expected: Success, if the user can register to the system
    public void registerMethodTestNegativeForHighAgeWithDB() throws SQLException {
        Users user = new Users();
        user.register(3,
                "denemename",
                "denemesurname",
                "denemeusername",
                "denemeparola",
                999999,
                "deneme@mail.com",
                "12345678901" );

        Connection connection = db.connect();
        Statement st = connection.createStatement();
        String query = "SELECT * FROM users order by id desc limit 1";
        ResultSet results = st.executeQuery(query);
        results.next();
        Assert.assertNotSame(999999999,results.getInt("age"));
    }

    @Test // Expected: Success, if the user can register to the system
    public void registerMethodTestNegativeForLowAgeWithControlCondition() throws SQLException {
        Users user = new Users();
        boolean result = user.register(3,
                "denemename",
                "denemesurname",
                "denemeusername",
                "denemeparola",
                8,
                "deneme@mail.com",
                "12345678901" );


        Assert.assertFalse(result);
    }

    @Test // Expected: Success, if the user can register to the system
    public void registerMethodTestNegativeForHighAgeWithControlCondition() throws SQLException {
        Users user = new Users();
        boolean result = user.register(3,
                "denemename",
                "denemesurname",
                "denemeusername",
                "denemeparola",
                201,
                "deneme@mail.com",
                "12345678901" );
        Assert.assertFalse(result);
    }





    @Test // Expected: Success, if the user can register to the system
    public void registerMethodTestPositive() throws SQLException {
        Users user = new Users();
        user.register(3,
                "denemename",
                "denemesurname",
                "denemeusername",
                "denemeparola",
                99,
                "deneme@mail.com",
                "12345678901" );

        Connection connection = db.connect();
        Statement st = connection.createStatement();
        String query = "SELECT * FROM users order by id desc limit 1";
        ResultSet results = st.executeQuery(query);
        results.next();


        Assert.assertEquals("denemename",results.getString("name"));
        Assert.assertEquals("denemesurname",results.getString("surname"));
        Assert.assertEquals("denemeusername",results.getString("username"));
        Assert.assertEquals("denemeparola",results.getString("password"));
        Assert.assertEquals(99,results.getInt("age"));
        Assert.assertEquals("deneme@mail.com",results.getString("email"));
        Assert.assertEquals("12345678901",results.getString("tc"));
    }





    @Test // Expected: Fail, if tried to register a user that already exists
    public void registerTestNegative() {

        //Connection to database
        Connection conn = null;
        Statement st = null;
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        String negativeTest = "Adnan";
        String name = null;

        try{
            //Connection to databse continious
            conn = db.connect();
            st = conn.createStatement();
            preparedStatement = conn.prepareStatement("INSERT INTO users ( authgroup, name, surname, username, password, age, email, tc) " + "VALUES ( ?,? , ?, ? , ?, ?, ?, ?)");
            // Set all the missing values in the query
            preparedStatement.setInt(1, 5);
            preparedStatement.setString(2, negativeTest);
            preparedStatement.setString(3, negativeTest);
            preparedStatement.setString(4, negativeTest);
            preparedStatement.setString(5, negativeTest);
            preparedStatement.setInt(6, 9999);
            preparedStatement.setString(7, negativeTest);
            preparedStatement.setInt(8, 000);
            //Execute query
            preparedStatement.execute();

            String query = "SELECT * FROM users WHERE authgroup = 3";
            results = st.executeQuery(query);

            while(results.next()){
                name = results.getString("Adnan");
            }

        } catch (Exception e){
            // Test: if negativeTest and name is equal
            Assert.assertFalse(negativeTest.equals(name));
        }

    }





    /***
     * user register tests
     */


}

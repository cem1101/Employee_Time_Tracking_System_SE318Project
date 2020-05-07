import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class usersTest {

    Users users;

    private db db = new db();

    @Before
    public void before(){
        users = new Users();
    }

    @Test // Expected: Success, if the worksheet is submitted
    public void login_as_employee_submitEmployeeWorksheetTest_Positive() throws SQLException {
        boolean submitted = false;
        users.login("e","e");
        users.submitEmployeeWS();
        // TODO

    }

    @Test // Expected: Success, if user can be deleted by TC number
    public void deleteByTC_Test_Positive() throws SQLException {
        users.deleteByTC("0");
        boolean deleted = false;
        String query = "SELECT * FROM users WHERE tc = " + users._tcNo;
        Connection connection = db.connect();
        Statement st = connection.createStatement();
        ResultSet managerRS = st.executeQuery(query);
        // Change value of deleted if not managerRS.next()
        if(!managerRS.next()){
            deleted = true;
        }
        // Test if the value of deleted is true or not
        Assert.assertEquals(true, deleted);

        if(deleted) {
            // ADD back deleted user
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users ( authgroup, name, surname, username, password, age, email, tc) " + "VALUES ( ?,? , ?, ? , ?, ?, ?, ?)");
            // Set all the missing values in the query
            preparedStatement.setInt(1, 3);
            preparedStatement.setString(2, "0");
            preparedStatement.setString(3, "0");
            preparedStatement.setString(4, "0");
            preparedStatement.setString(5, "0");
            preparedStatement.setInt(6, 0);
            preparedStatement.setString(7, "0");
            preparedStatement.setInt(8, 0);
            // Execute the query
            preparedStatement.execute();
        }
    }

    @Test // Expected: Success, if the user can register to the system
    public void registerTestPositive() throws SQLException {
        //  Connection to database
        Connection connection = db.connect();
        Statement st = connection.createStatement();
        // Create prepared statement for sql query
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users ( authgroup, name, surname, username, password, age, email, tc) " + "VALUES ( ?,? , ?, ? , ?, ?, ?, ?)");
        // Set all the missing values in the query
        String test = "test";
        preparedStatement.setInt(1, 5);
        preparedStatement.setString(2,test );
        preparedStatement.setString(3, test);
        preparedStatement.setString(4, test);
        preparedStatement.setString(5, test);
        preparedStatement.setInt(6, 9999);
        preparedStatement.setString(7, test);
        preparedStatement.setInt(8, 000);
        // Execute the query
        preparedStatement.execute();

        String query = "SELECT * FROM users WHERE authgroup = 5";
        ResultSet results = st.executeQuery(query);
        String name = null;

        while(results.next()){
            name = results.getString("name");
        }
        // Test if test and name are equal
        Assert.assertTrue(test.equals(name));
        // Delete the user which given in the test if given
        if(test.equals(name)){
            String delete = "DELETE FROM users where authgroup = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(delete);
            preparedStmt.setInt(1,5);
            // Execute query
            preparedStmt.execute();
        }
    }

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
    public void login_As_Manager_and_CheckForAuthgroupNegative() throws SQLException {
        users.login("m","m");
        int authgroup = users._authgroup;
        // False values to check if they are coming or not
        Assert.assertEquals(1,authgroup);
        Assert.assertEquals(3,authgroup);
    }

    @Test // Expected: Fail, if a admin user try to login as employee or manager
    public void login_As_Admin_and_CheckForAuthgroupNegative() throws SQLException {
        users.login("123","123");
        int authgroup = users._authgroup;
        // False values to check if they are coming or not
        Assert.assertEquals(2,authgroup);
        Assert.assertEquals(1,authgroup);

    }

    @Test // Expected: Fail, if a employee user try to login as manager or admin
    public void login_As_Employee_and_CheckForAuthgroupNegative() throws SQLException {
        users.login("e","e");
        int authgroup = users._authgroup;
        // False values to check if they are coming or not
        Assert.assertEquals(2,authgroup);
        Assert.assertEquals(3,authgroup);
    }

    @Test // Expected: Fail: User uses wrong user name and password
    public void loginTestNegative() throws SQLException {
        boolean falseLogin = users.login("qwe","qwe");
        // Test: falseLogin is true
        Assert.assertEquals(true, falseLogin);
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
            Assert.assertTrue(negativeTest.equals(name));
        }

    }

    @Test
    public void login_as_employee_submitEmployeeWorksheetTest_Negative() throws SQLException {
        boolean submitted = false;
        users.login("e","e");
        users.submitEmployeeWS();
        // TODO submit ws negative
    }

    @Test //Expected: Fail, if try to delete a user that doesn't exist
    public void deleteByTC_Test_Negative() throws SQLException {
        // Defined a non-exist user to delete
        users.deleteByTC("900");
        boolean deleted = true;
        // Delete query
        String query = "DELETE FROM users WHERE tc = " + users._tcNo;
        // Connection to database
        Connection connection = null;
        Statement st = null;
        ResultSet managerRS = null;

        // try: The query is executable? If not catch
        try {
            connection = db.connect();
            st = connection.createStatement();
            managerRS = st.executeQuery(query);
        }catch (SQLException sE) {
                deleted = false;
        }
        // Test: If deleted is true
        Assert.assertEquals(true, deleted);
    }
}

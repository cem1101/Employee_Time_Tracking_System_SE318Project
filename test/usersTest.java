import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.Scanner;

public class usersTest {
    Users users;
    private db db = new db();
    @Before
    public void before(){
        users = new Users();
    }



    @Test
    public void login_as_employee_submitEmployeeWorksheetTest_Positive() throws SQLException {
        boolean submitted = false;
        users.login("e","e");
        users.submitEmployeeWS();
        // TODO

    }

    @Test
    public void deleteByTC_Test_Positive() throws SQLException {
        users.deleteByTC("0");
        boolean deleted = false;
        String query = "SELECT * FROM users where tc = " + users._tcNo;
        Connection connection = db.connect();
        Statement st = connection.createStatement();
        ResultSet managerRS = st.executeQuery(query);
        if(!managerRS.next()){
            deleted = true;
        }
        Assert.assertEquals(true,deleted);

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

    @Test
    public void registerTestPositive() throws SQLException {
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

        String query = "SELECT * FROM users where authgroup = 5";
        ResultSet results = st.executeQuery(query);
        String name = null;
        while(results.next()){
            name = results.getString("name");
        }
        Assert.assertTrue(test.equals(name));

        if(test.equals(name)){
            String delete = "DELETE FROM users where authgroup = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(delete);
            preparedStmt.setInt(1,5);
            preparedStmt.execute();
        }
    }


    @Test
    public void loginTestPositive() throws SQLException {
        boolean trueLogin = users.login("e","e");
        Assert.assertEquals(true,trueLogin);
    }

    @Test
    public void login_As_Employee_and_CheckForAuthgroupPositive() throws SQLException {
        users.login("e","e");
        int authgroup = users._authgroup;
        Assert.assertEquals(1,authgroup);

    }

    @Test
    public void login_As_Manager_and_CheckForAuthgroupPositive() throws SQLException {
        users.login("m","m");
        int authgroup = users._authgroup;
        Assert.assertEquals(2,authgroup);
    }

    @Test
    public void login_As_Admin_and_CheckForAuthgroupPositive() throws SQLException {
        users.login("123","123");
        int authgroup = users._authgroup;
        Assert.assertEquals(3,authgroup);
    }

    /** These are the false tests that should fail (Fail means good) */

    @Test
    public void login_As_Manager_and_CheckForAuthgroupNegative() throws SQLException {
        users.login("m","m");
        int authgroup = users._authgroup;
        // False values to check if they are coming or not
        Assert.assertEquals(1,authgroup);
        Assert.assertEquals(3,authgroup);
    }

    @Test
    public void login_As_Admin_and_CheckForAuthgroupNegative() throws SQLException {
        users.login("123","123");
        int authgroup = users._authgroup;
        // False values to check if they are coming or not
        Assert.assertEquals(2,authgroup);
        Assert.assertEquals(1,authgroup);

    }

    @Test
    public void login_As_Employee_and_CheckForAuthgroupNegative() throws SQLException {
        users.login("e","e");
        int authgroup = users._authgroup;
        // False values to check if they are coming or not
        Assert.assertEquals(2,authgroup);
        Assert.assertEquals(3,authgroup);
    }

    @Test
    public void loginTestFalse() throws SQLException {
        boolean falseLogin = users.login("qwe","qwe");
        Assert.assertEquals(true,falseLogin);
    }

    @Test
    public void registerTestNegative() throws SQLException {
        // TODO register negative
    }

    @Test
    public void login_as_employee_submitEmployeeWorksheetTest_Negative() throws SQLException {
        boolean submitted = false;
        users.login("e","e");
        users.submitEmployeeWS();
        // TODO submit ws negative
    }

    @Test
    public void deleteByTC_Test_Negative() throws SQLException {
        // TODO delete by TC negative
    }
}

import java.sql.*;
import java.util.HashMap;

public class dummyDataFunctionsForTests {


    Users users;

    private db db = new db();





    public void  createDummyRowToUsers() throws SQLException {
        users.register(3,
                "denemename",
                "denemesurname",
                "denemeusername",
                "denemeparola",
                30,
                "deneme@mail.com",
                "12345678901" );
    }





    public HashMap getLastRowInsertedOnUsers() throws SQLException {
        Connection connection = db.connect();
        Statement st = connection.createStatement();
        String query = "SELECT * FROM users order by id desc limit 1";
        ResultSet results = st.executeQuery(query);
        results.next();
        HashMap data = new HashMap();
        data.put("id",results.getInt("id"));
        data.put("authgroup",results.getInt("authgroup"));
        data.put("name",results.getString("name"));
        data.put("surname",results.getString("surname"));
        data.put("username",results.getString("username"));
        data.put("password",results.getString("password"));
        data.put("age",results.getInt("age"));
        data.put("email",results.getString("email"));
        data.put("tc",results.getString("tc"));
        return data;

    }



    public void deleteLastCreatedRow() throws SQLException {
        Connection connection = db.connect();
        String delete = "DELETE FROM users order by id desc limit 1";
        PreparedStatement preparedStmt = connection.prepareStatement(delete);
        preparedStmt.execute();
    }





    public boolean tryMailInput(String email) throws SQLException {
        Users user = new Users();
        return user.register(3,
                "denemename",
                "denemesurname",
                "denemeusername",
                "denemeparola",
                29,
                email,
                "12345678901" );
    }



}

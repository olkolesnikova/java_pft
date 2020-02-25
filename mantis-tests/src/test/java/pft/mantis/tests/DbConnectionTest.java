package pft.mantis.tests;

import org.testng.annotations.Test;
import pft.mantis.model.UserData;
import pft.mantis.model.Users;

import java.sql.*;

public class DbConnectionTest {

    @Test
    public void testDbConnection() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?serverTimezone=UTC&user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select id,username from mantis_user_mantis");
            Users users = new Users();
            while (rs.next()) {
                users.add(new UserData());
            }
            rs.close();
            st.close();
            conn.close();
            System.out.println(users);


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}

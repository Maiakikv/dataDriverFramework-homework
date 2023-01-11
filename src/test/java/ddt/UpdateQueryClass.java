package ddt;

import org.testng.Assert;

import java.sql.*;

public class UpdateQueryClass {
    public static void update() {

        String sqlUpdate = "update [students].[dbo].[students]\n" +
                "  set firstName=?\n" +
                "  where id=1004";

        try (Connection conn = ConnectionClass.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {
            conn.setAutoCommit(false);
            String firstName = "Anjela";


            pstmt.setString(1, firstName);


            int rowAffected = pstmt.executeUpdate();
            System.out.println(String.format("Row affected %d", rowAffected));

            conn.commit();


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }


    public static void main(String[] args) {
        update();

        String sql = "SELECT * FROM [students].[dbo].[students] where id = 1004";
        try (Connection conn = ConnectionClass.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String name = rs.getString(2);
                Assert.assertEquals(name, "Anjela");


            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}



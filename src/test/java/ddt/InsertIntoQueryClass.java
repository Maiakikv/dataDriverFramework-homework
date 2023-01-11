package ddt;

import org.testng.Assert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InsertIntoQueryClass {
    public static int insertStudent(int id,String firstName,String lastName,
                                      String phone) {
        ResultSet rs = null;
        int studentId = 0;

        String sql = " insert into [students].[dbo].[students]\n" +
                "(id,firstName,lastName,phone)\n" +
                "  values(?,?,?,?)";

        try (Connection conn = ConnectionClass.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {


            conn.setAutoCommit(false);

            pstmt.setInt(1, id);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, phone);



            int rowAffected = pstmt.executeUpdate();


            String sql_res= "select * from students where id=1004";
            rs=pstmt.executeQuery(sql_res);

            if(rowAffected == 1)
            {
                // get id
                rs = pstmt.getGeneratedKeys();
                if(rs.next())
                    studentId = rs.getInt(1);


            }

            conn.commit();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } finally {
            try {

                if(rs != null)  rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return studentId;
    }

    public static void main(String[] args) {
        // insert a new student
        int id = insertStudent(1004, "Maia", "Kikvadze",
                "599090909");

        System.out.println(String.format("A new row with id %d has been inserted.",id));

        String sqll= "SELECT * FROM [students].[dbo].[students] where id = 1004";
        List<String> studentInfo = new ArrayList<>();

            try (Connection conn = ConnectionClass.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sqll)) {

                while (rs.next()) {
                studentInfo.add(rs.getString(2));
                studentInfo.add(rs.getString(3));
                studentInfo.add(rs.getString(4));

                // აფდეითის მერე აღარ ჩანს "მაია" და ჩანს "ანჟელა", წინას როგორ შევადარო, ვერ ვხვდები

                Assert.assertEquals(studentInfo.get(0), "Maia");
                Assert.assertEquals(studentInfo.get(1), "Kikvadze");
                Assert.assertEquals(studentInfo.get(2), "599090909");

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}



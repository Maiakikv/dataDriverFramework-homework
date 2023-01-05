package main.DDT;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class SelectQuery {


    @DataProvider(name = "DB")
    public Object[][] dataProvider() throws SQLException {
        String dbdata[][] = getValuesFromSQL();
        return dbdata;
    }

    public String[][] getValuesFromSQL() throws SQLException {

        String sql = "select * from [students].[dbo].[students]";
        Connection conn = MySQLJDBCUtil.getConnection();
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery(sql);
        rs.last();
        int rows = rs.getRow();
        ResultSetMetaData meeta = rs.getMetaData();
        int cols = meeta.getColumnCount();
        String data[][] = new String[rows][cols];

        int i = 0;
        rs.beforeFirst();
        while (rs.next()) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = rs.getString(j + 1);
                System.out.println(data[i][j]);

            }
            i++;

        }
        return data;
    }

//    @Test(dataProvider = "DB")
//    public void fillForm(String id, String firstName, String lastName, String mobileNumber) {
//        open("https://demoqa.com/automation-practice-form");
//        $("#firstName").sendKeys(firstName);
//        $("#lastName").sendKeys(lastName);
//        $("[placeholder='Mobile Number']").sendKeys(mobileNumber);

    }









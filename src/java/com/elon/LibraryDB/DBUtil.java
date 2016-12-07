/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elon.LibraryDB;

import java.sql.*;

/**
 *
 * @author nyoung5
 */
public class DBUtil {
  public static void closeStatement(Statement s) {
        try {
            if (s != null) {
                s.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void closePreparedStatement(Statement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static String getHtmlTable(ResultSet results)
            throws SQLException
    {
        StringBuilder htmlTable = new StringBuilder();
        ResultSetMetaData metaData = results.getMetaData();
        int columnCount = metaData.getColumnCount();
        htmlTable.append("<table>\n");
        
        // add header row
        htmlTable.append("<tr>");
        for (int i = 1; i <= columnCount; i++) {
            htmlTable.append("<th>");
            htmlTable.append(metaData.getColumnLabel(i));
            htmlTable.append("</th>");
        }
        htmlTable.append("<th></th>");
        htmlTable.append("</tr>");
        
        // add all other rows
        while (results.next())
        {
            htmlTable.append("<tr>");
            for (int i = 1; i <= columnCount; i++) {
                htmlTable.append("<td>");
                htmlTable.append(results.getString(i));
                htmlTable.append("</td>");
            }
            String checkin = results.getString("Book Title");
            htmlTable.append("<td>")
                    .append("<form action=").append("'library'").append(">")
                    .append("<input type=").append("'hidden'")
                    .append(" name=").append("'").append("action")
                    .append("'").append(" value=")
                    .append("'").append(checkin).append("'").append(">")
                    .append("<input type=").append("'submit'")
                    .append(" value=").append("'Check In'").append(">")
                    .append("</form>").append("</td>").append("</tr>");
        }
        
        htmlTable.append("</table>");
        DBUtil.closeResultSet(results);
        return htmlTable.toString();
    }    
    
}

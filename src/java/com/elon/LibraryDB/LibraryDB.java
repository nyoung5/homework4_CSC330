/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elon.LibraryDB;


import java.util.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;



/**
 * 
 * @author nyoung5
 */
public class LibraryDB {
  
  
  //TODO: Do the dates
  public static void checkout(User user, String bookname) {
        
        if(!emailExists(user.getEmail())) {
          insert(user);
        }
  
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO checkout"
                + "VALUES (?, ?, ?, ?";
        try {
            ps = connection.prepareStatement(query);
            
            
            //Source for dates: http://stackoverflow.com/questions/3350893/
            //how-to-insert-current-date-and-time-in-a-database-using-sql
            // and http://stackoverflow.com/questions/1005523/how-to-add-
            //one-day-to-a-date
            
            //create java date based on current Time.
            java.util.Date jcheckoutDate = new Date();
            
            //Calendar for adding 2 weeks
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(jcheckoutDate);
            calendar.add(Calendar.DATE, 14);
            
            java.util.Date jdueDate = calendar.getTime();
            
//    TEST        System.out.println("checkout date: " + jcheckoutDate.getTime());
//            System.out.println("due date: " + jdueDate.getTime());
          
            
            //create date based on miliseconds passed in from date
            java.sql.Date checkoutDate = new java.sql.Date(jcheckoutDate.getTime());
            java.sql.Date dueDate = new java.sql.Date(jdueDate.getTime());
           
//    TEST        System.out.println("sql checkout date: " + checkoutDate);
//            System.out.println("sql due date: " + dueDate);
            
            ps.setDate(1, checkoutDate);
            ps.setDate(2, dueDate);
            ps.setString(3, user.getEmail());
            ps.setString(4, bookname);
            

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("problem with checking out book");
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
  }
  
  public static boolean emailExists(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT email FROM user "
                + "WHERE email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
  
  public static int insert(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO user (first_name,last_name,email)"
                + "VALUES (?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int update(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE user SET "
                + "FirstName = ?, "
                + "LastName = ? "
                + "WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int delete(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM user "
                + "WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
 public static ArrayList<User> selectUsers() {
        // add code that returns an ArrayList<User> object of all users in the User table
        ArrayList<User> userList = new ArrayList<User>();
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        //Nathan Young
        String query = "SELECT * FROM user ";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            User user = null;
            while (rs.next()) {
                user = new User();
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                
                //Nathan Young
                userList.add(user);
                
            }
            return userList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }    
}

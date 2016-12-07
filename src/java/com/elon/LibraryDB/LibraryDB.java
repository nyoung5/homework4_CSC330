package com.elon.LibraryDB;

import java.util.Date;
import java.sql.*;
import java.util.Calendar;

/**
 * 
 * @author Nathan Young
 * @author Harrison Durant
 * 
 * Library database talks with the SQL database 
 * Manages books, users and checkouts
 */
public class LibraryDB {
  
/**
 * Updates the table to check out a book
 * @param user person checking out the book
 * @param bookTitle title of the book to be checked out
 * @return if checkout successful, emptystring, if unsuccessful, error message
 */
  public static String checkout(User user, String bookTitle) {
        
        if(!emailExists(user.getEmail())) {
          insertUser(user);
        }
        
        if(!bookExists(bookTitle)) {
          insertBook(bookTitle);
        }
        else if(isCheckedOut(bookTitle)) {
          return "Sorry, but this book has been checked out already";
        }
  
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO checkout (checkout_date,"
                + "due_date,user_email,book_name)"
                + "VALUES (?, ?, ?, ?)";
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
            
            //create date based on miliseconds passed in from date
            java.sql.Date checkoutDate = new java.sql.Date(jcheckoutDate.getTime());
            java.sql.Date dueDate = new java.sql.Date(jdueDate.getTime());
           
            ps.setDate(1, checkoutDate);
            ps.setDate(2, dueDate);
            ps.setString(3, user.getEmail());
            ps.setString(4, bookTitle);

            ps.executeUpdate();
            return "";
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("problem with checking out book");
            return "There was a problem with checking out book";
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
  }

  /**
   * Checks the status of the 
   * 
   * @param email e-mail to check if in database
   * @return boolean saying whether or not the email exists
   */
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
  
  /**
   * Checks if the book exists in database
   * 
   * @param bookTitle name of book to check if in database
   * @return boolean stating if the book exists or not
   */
  public static boolean bookExists(String bookTitle) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM book "
                + "WHERE book_name = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, bookTitle);
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
  
  /**
   * Checks to see if a book is checked out
   * @param bookTitle book to check in database
   * @return boolean representing whether or not the book is checked out 
   */
  public static boolean isCheckedOut(String bookTitle) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query
                = "SELECT * FROM checkout "
                + "WHERE book_name = ?";
      
        try {
            ps = connection.prepareStatement(query);
            
            ps.setString(1,bookTitle);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
  /**
   * Checks in a book by deleting it from the database
   * @param bookTitle the book to be checked in 
   */
    public static void checkIn(String bookTitle) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM checkout "
                + "WHERE book_name = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, bookTitle);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
  
  public static int insertBook(String bookTitle) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO book (book_name)"
                + "VALUES (?)";
        try {
            ps = connection.prepareStatement(query);
            
            ps.setString(1, bookTitle);

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
  
  /**
   * Inserts a user into the database
   * @param user user object to be inserted into database
   */
  public static void insertUser(User user) {
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

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
  }

  /**
   * deletes a user from the database
   * @param user user to be deleted
   */
    public static void delete(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM user "
                + "WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
/**
 * Returns all the checked out books and people associated with them
 * @return String with all the users in a table
 */
 public static String getCheckouts() {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        
        //Nathan Young
        String query = "SELECT "
                + "CONCAT(user.first_name,' ',user.last_name) AS 'Patron Name', "
                + "user_email AS 'Email Address', "
                + "book_name AS 'Book Title', "
                + "DATE(due_date) AS 'Due Date', "
                + "CASE WHEN due_date < DATE(NOW()) THEN 'overdue' "
                + "ELSE '' END AS 'Overdue'"
                + "FROM checkout "
                + "JOIN user ON checkout.user_email = user.email"
                ;
        try {
            ps = connection.prepareStatement(query);
            resultSet = ps.executeQuery();
            return DBUtil.getHtmlTable(resultSet);
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }    
}

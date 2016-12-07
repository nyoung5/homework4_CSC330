/*
  Copyright © 2016 Harrison Durant & Nathan Young
*/
package com.elon.libraryServlet;

import com.elon.LibraryDB.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author harrisondurant
 * @author nathanyoung
 */
public class LibraryServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
    String url = "/index.jsp";
    String message = "";
    
    HttpSession session = request.getSession();
    
    GregorianCalendar currentDate = new GregorianCalendar();
    int currentYear = currentDate.get(Calendar.YEAR);
    request.setAttribute("currentYear", currentYear);

    String action = request.getParameter("action");
    if (action != null) {
    
      if (action.equals("checkout")) {
          // get parameters from the request
        url = "/checkout.jsp";
        
        //String bookname = "";
        //TODO get bookname request.getParameter(bookname)
        
        //User user = new User("Jeff", "Stein", "jstein@yahoo.com");
        //LibraryDB.checkout(user, "Crafting Olympia");
         
        //LibraryDB.insert(user);
      }
      else if(action.equals("manage")) {
        String results = LibraryDB.getCheckouts();
        request.setAttribute("checkoutTable", results);
        url = "/manage.jsp";
      }
      else if(action.equals("process_checkout")) {
        String first = request.getParameter("firstname");
        String last = request.getParameter("lastname");
        String email = request.getParameter("email");
        String bookTitle = request.getParameter("title");
        User user = new User(first,last,email);
        String libraryResponse = LibraryDB.checkout(user,bookTitle);
        
        if(libraryResponse.length() != 0) {
          request.setAttribute("message",libraryResponse);
          url = "/checkout.jsp";
        } 
        else {
          url = "/thanks.jsp";
        }
      }
      else { //default case -- action is to check in a book
        String bookTitle = action;
        LibraryDB.checkIn(bookTitle);
        url = "/library?action=manage";
      }
    }
    
    
    //request.setAttribute("message", message);
    //request.setAttribute("investment", investment);
    
    getServletContext()
            .getRequestDispatcher(url)
            .forward(request, response);
  }
    
  @Override
  protected void doGet(HttpServletRequest request,
          HttpServletResponse response)
          throws ServletException, IOException {
    doPost(request, response);
  }    
}

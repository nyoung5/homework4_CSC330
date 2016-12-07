/*
  Copyright © 2016 Harrison Durant & Nathan Young
*/
package com.elon.libraryServlet;

import com.elon.LibraryDB.*;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author harrisondurant
 * @author nathanyoung
 * Controller for managing form actions 
 * and getting responses from database classes
 */
public class LibraryServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
    String url = "/index.jsp";
    
    //used for displaying year next to copyright
    GregorianCalendar currentDate = new GregorianCalendar();
    int currentYear = currentDate.get(Calendar.YEAR);
    request.setAttribute("currentYear", currentYear);

    String action = request.getParameter("action");
    if (action != null) {
    
      if (action.equals("checkout")) { //just redirect to checkout page
        url = "/checkout.jsp";
      }
      else if(action.equals("manage")) { // create table and go to manage page
        String results = LibraryDB.getCheckouts();
        request.setAttribute("checkoutTable", results);
        url = "/manage.jsp";
      }
      else if(action.equals("process_checkout")) { //checks out book if not out
        String first = request.getParameter("firstname");
        String last = request.getParameter("lastname");
        String email = request.getParameter("email");
        String bookTitle = request.getParameter("title");
        User user = new User(first,last,email);
        String libraryResponse = LibraryDB.checkout(user,bookTitle);
        
        if(libraryResponse.length() != 0) { //If there's a problem print message
          request.setAttribute("message",libraryResponse);
          url = "/checkout.jsp";
        } 
        else { //otherwise bring user to thanks/success page
          url = "/thanks.jsp";
        }
      }
      else { //default case -- action is to check in a book
        String bookTitle = action;
        LibraryDB.checkIn(bookTitle);
        url = "/library?action=manage";
      }
    }
    
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

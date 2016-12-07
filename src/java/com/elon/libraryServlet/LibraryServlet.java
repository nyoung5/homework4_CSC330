/*
  Copyright © 2016 Harrison Durant & Nathan Young
*/
package com.elon.libraryServlet;

import com.elon.LibraryDB.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
    
    HttpSession session = request.getSession();
    
    GregorianCalendar currentDate = new GregorianCalendar();
    int currentYear = currentDate.get(Calendar.YEAR);
    request.setAttribute("currentYear", currentYear);

    String action = request.getParameter("action");
    if (action != null) {
    
      if (action.equals("checkout")) {
          // get parameters from the request
        url = "/checkout.jsp";
        
        String bookname = "";
        //TODO get bookname request.getParameter(bookname)
        
        User user = new User("Jeff", "Stein", "jstein@yahoo.com");
        //LibraryDB.checkout(user, "Crafting Olympia");
         
        LibraryDB.insert(user);
        
        
      }
      else if(action.equals("manage")) {
        System.out.println("@@@@@@MANAGE!");
        ArrayList<User> users = LibraryDB.selectUsers();
        
        for(User user:users){
        System.out.println(user.getEmail());
        }
        
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

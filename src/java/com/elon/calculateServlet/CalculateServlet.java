/*
  Copyright © 2016 Harrison Durant & Nathan Young
*/
package com.elon.calculateServlet;

import com.elon.business.Investment;
import java.io.IOException;
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
public class CalculateServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
    String url = "/index.jsp";
    String message = "";
    Investment investment = new Investment();
    
    HttpSession session = request.getSession();
    
    GregorianCalendar currentDate = new GregorianCalendar();
    int currentYear = currentDate.get(Calendar.YEAR);
    request.setAttribute("currentYear", currentYear);

    String action = request.getParameter("action");
    if (action != null) {
    
      if (action.equals("calc")) {
          // get parameters from the request
         
        String investmentAmount = request.getParameter("investmentAmount");
        String yearlyInterestRate = request.getParameter("yearlyInterestRate");
        String numberOfYears = request.getParameter("numberOfYears");

        try {
          double amount = Double.parseDouble(investmentAmount);
          double interest = Double.parseDouble(yearlyInterestRate);
          int years = Integer.parseInt(numberOfYears);

          if(amount >= 0 && interest >= 0 && years >= 0) {
            investment = new Investment(amount,interest,years);
            url = "/futureValue.jsp";
          }
          else {
            message = "Please enter positive numbers.";
          }
        }
        catch(NumberFormatException e) {
          message = "Please enter only positive numbers.";
        }
        
        session.setAttribute("investment",investment);
        
      }
    }
    
    
    request.setAttribute("message", message);
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

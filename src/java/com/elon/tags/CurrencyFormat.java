/*
 * Copyright © 2016 Harrison Durant & Nathan Young
 */
package com.elon.tags;

import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * This tag formats currency with a '$' symbol
 * and comma delimiters
 * @author harrisondurant
 * @author nathanyoung
 */
public class CurrencyFormat extends TagSupport {
  private double dollars;
  
  public void setDollars(String dollars){
    try {
      this.dollars = Double.parseDouble(dollars);
    }
    catch(NumberFormatException e) {
      System.out.println("Exception occurred: " + e.getMessage());
      this.dollars = 0.0;
    }
  }
  

  /**
   * Called by the container to invoke this tag. The implementation of this
   * method is provided by the tag library developer, and handles all tag
   * processing, body iteration, etc.
   */
  @Override
  public int doStartTag() throws JspException {
    try {
      JspWriter out = pageContext.getOut();
      out.print(String.format("$%,.2f", dollars));
    } catch (IOException ioe) {
      System.out.println(ioe);
    }
    return SKIP_BODY;
  }
  
}

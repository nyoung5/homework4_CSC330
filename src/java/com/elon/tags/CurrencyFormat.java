/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elon.tags;

import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author nyoung5
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

/*
  Copyright (c) 2016 Harrison Durant, Stephanie Scro -->
 */
package com.elon.business;

import java.io.Serializable;

/**
 * @author harrisondurant
 * @author nathanyoung
 */
public class Investment implements Serializable {
  
  private double amount;
  private double interestRate;
  private int years;
  private double futureValue;
  
  
  public Investment() {
    this.amount = 0.0;
    this.interestRate = 0.0;
    this.years = 0;
    this.futureValue = 0.0;
  }
  
  public Investment(double amount, double interest, int years) {
    this.amount = amount;
    this.interestRate = interest;
    this.years = years;
    this.futureValue = calculateInvestmentValue(amount,interest,years);
  }
  
  public String getAmount() {
      return "" + this.amount;
  }
  
  public void setAmount(String newAmount) {
    try {
      this.amount = Double.parseDouble(newAmount);
    }
    catch(NumberFormatException e) {
      System.out.println("Exception occurred: " + e.getMessage());
    }
  }
  
  public String getInterestRate() {
    return "" + this.interestRate;
  }
  
  public void setInterestRate(String newInterest) {
    try {
      this.interestRate = Double.parseDouble(newInterest);
    }
    catch(NumberFormatException e) {
      System.out.println("Exception occurred: " + e.getMessage());
    }
  }
  
  public String getYears() {
    return "" + this.years;
  }
  
  public void setYears(String newYears) {
    try {
      this.years = Integer.parseInt(newYears);
    }
    catch(NumberFormatException e) {
      System.out.println("Exception occurred: " + e.getMessage());
    }
  }
  
  public String getFutureValue() {
    return "" + this.futureValue;
  }
  
  public void setFutureValue(String newValue) {
    try {
      this.futureValue = Double.parseDouble(newValue);
    }
    catch(NumberFormatException e) {
      System.out.println("Exception occurred: " + e.getMessage());
    }
  }
  
  public static double calculateInvestmentValue(double investment, double interest, int years) {
    double rate = 1.0 + (interest / 100.0);
    double value = investment * Math.pow(rate,years);
    return value;
  }
  
  public String getFormattedFutureValue(){
      return String.format("$%,.2f", this.futureValue);
  }
  public String getFormattedAmount(){
      return String.format("$%,.2f", this.amount);
  }
  
}

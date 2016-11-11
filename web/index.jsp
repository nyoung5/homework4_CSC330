<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/includes/header.html" %>
  <h1>Future Value Calculator</h1>
  
  <p><i>${message}</i></p>
  
  <form action="calculate" method="post">
  <input type="hidden" name="action" value="calc">
    <table class="calculator">
      <tr>
        <td><p>Investment Amount</p></td>
        <td><input type="text" name="investmentAmount" value="${sessionScope.investment.amount}" required></td>
      </tr>
      <tr>
        <td><p>Yearly Interest Rate</p></td>
        <td><input type="text" name="yearlyInterestRate" value="${sessionScope.investment.interestRate}" required></td>
      </tr>
      <tr>
        <td><p>Number of Years</p></td>
        <td><input type="text" name="numberOfYears" value="${sessionScope.investment.years}" placeholder="Integer number of years" required></td>
      </tr>
      <tr>
        <td></td>
        <td><input type="submit" value="Calculate"></td>
      </tr>
    </table>
  </form>
</div>

<%@ include file="/includes/footer.jsp" %>
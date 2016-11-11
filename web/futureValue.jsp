<%@ include file="/includes/header.html" %>
<%@taglib prefix="elon" uri="WEB-INF/currency.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <h1>Future Value Calculator</h1>
  <table>
    <tr>
      <td><p>Investment Amount:</p></td>
      <td><p><elon:currencyFormat dollars="${sessionScope.investment.amount}"/></p></td>
    </tr>
    <tr>
      <td><p>Yearly Interest Rate:</p></td>
      <td><p>${sessionScope.investment.interestRate}</p></td>
    </tr>
    <tr>
      <td><p>Number of Years:</p></td>
      <td><p>${sessionScope.investment.years}</p></td>
    </tr>
    <tr>
      <td><p><b>Year</b></p></td>
      <td><p><b>Value</b></p></td>
    </tr>
    <c:forTokens var="retVal" varStatus="status" delims="|" items="${sessionScope.investment.investmentReturns}">
      <tr>
        <td><p>${status.count}</p></td>
        <td><p><elon:currencyFormat dollars="${retVal}" /></p></td>
      </tr>
    </c:forTokens>
  </table>
	<a href="<c:url value='/calculate'/>">
		Return to Calculator
	</a>

</div>

<%@ include file ="/includes/footer.jsp" %>
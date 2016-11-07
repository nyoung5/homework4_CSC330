<%@ include file="/includes/header.html" %>
<jsp:useBean id="investment" scope="session" class="com.elon.business.Investment"/>
  
  <h1>Future Value Calculator</h1>
  <table>
    <tr>
      <td><p>Investment Amount:</p></td>
      <td><p><jsp:getProperty name="investment" property="formattedAmount"/></p></td>
    </tr>
    <tr>
      <td><p>Yearly Interest Rate:</p></td>
      <td><p><jsp:getProperty name="investment" property="interestRate"/></p></td>
    </tr>
    <tr>
      <td><p>Number of Years:</p></td>
      <td><p><jsp:getProperty name="investment" property="years"/></p></td>
    </tr>
    <tr>
      <td><p>Future Value:</p></td>
      <td><p><jsp:getProperty name="investment" property="formattedFutureValue"/></p></td>
    </tr>
    <tr>
      <form action="calculate">
        <td></td>
        <td>
          <input type="submit" value="Return to Calculator">
        </td>
      </form>
    </tr>
  </table>

</div>

<%@ include file ="/includes/footer.jsp" %>
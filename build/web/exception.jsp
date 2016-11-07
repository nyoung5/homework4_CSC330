<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>

<%@ include file="/includes/header.html" %>

<!-- Copyright (c) 2016 Harrison Durant, Nathan Young -->

  <h1>Java Error</h1>
  <p>Sorry, Java has thrown an exception.</p>
  <p>To continue, click the Back button.</p>

  <h2>Details</h2>
  <p>Type: ${pageContext.exception["class"]}</p>
  <p>Message: ${pageContext.exception.message}</p>

</div>

<%@ include file="/includes/footer.jsp" %>
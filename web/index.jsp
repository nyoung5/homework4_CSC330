<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/includes/header.html" %>
  <h1>Belk Library</h1>
  
  <a href="<c:url value='/library?action=checkout'/>">
    Check out a book
  </a><br>
  <a href="<c:url value='/library?action=manage'/>">
    Manage checked out books
  </a>
</div>

<%@ include file="/includes/footer.jsp" %>
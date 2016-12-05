<%@ include file="/includes/header.html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Belk Library</h1>
<h1>checkout a book</h1>

<form action="library">
  <input type="hidden" name="action" value="booksearch">
</form>

<a href="<c:url value='/library'/>">
    Return to Homepage
</a>

</div>

<%@ include file ="/includes/footer.jsp" %>
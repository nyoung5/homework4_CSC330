<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="footer">
<c:choose>
  <c:when test="${currentYear != null}">
  <p>&copy; ${currentYear}, Harrison Durant &amp; Nathan Young</p>
  </c:when>    
  <c:otherwise>
  <p>&copy; 2016, Harrison Durant &amp; Nathan Young</p>
  </c:otherwise>
</c:choose>
</div>
</body>
</html>

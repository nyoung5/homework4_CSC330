<%@ include file="/includes/header.html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p>${message}</p>
<h1>checkout a book</h1>

<form action="library" method="post">
  <input type="hidden" name="action" value="process_checkout">
  <table class="library_table">
    <tr>
      <td>First Name</td>
      <td><input type="text" name="firstname" required></td>
    </tr>
    <tr>
      <td>Last Name</td>
      <td><input type="text" name="lastname" required></td>
    </tr>
    <tr>
      <td>Email Address</td>
      <td><input type="email" name="email" required></td>
    </tr>
    <tr>
      <td>Book Title</td>
      <td><input type="text" name="title" required></td>
    </tr>
    <tr>
      <td></td>
      <td><input type="submit" value="Checkout"></td>
    </tr>
  </table>
</form>

</div>

<%@ include file ="/includes/footer.jsp" %>
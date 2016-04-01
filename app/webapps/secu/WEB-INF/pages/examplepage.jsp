<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>

<h1>Examplepage</h1>

<h2><c:out value="${message}"/></h2>

<form action="example.secu" method="get">
    <input type="text" name="einname" id="eineid">
</form>

<form action="example.secu" method="post">
    <input type="text" name="einname" id="eineid2">
</form>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <link type="text/css"
          href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
    <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
    <title>Meal</title>
</head>

<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Edit meal</h2>
<script>
    $(function () {
        $('input[name=dob]').datepicker();
    });
</script>

<form method="POST" action='meals' name="frmAddUser">
    DateTime : <input
        type="datetime-local" name="dob"
        value="${meal.dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))}"/>
    <br/>
    <br>
    Description : <input
        type="text" name="firstName"
        value="<c:out value="${meal.description}" />"/>
    <br/>
    <br>
    Calories : <input
        type="text" name="lastName"
        value="<c:out value="${meal.calories}" />"/> <br/>
    <br>
    <input type="submit" value="Save"/> <button onclick="window.history.back()" type="button">Cancel</button>
</form>
</body>
</html>
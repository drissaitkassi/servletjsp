
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
<head>
    <title>Add Student </title>
    <link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>
<body>

<%--    <h1> test add student jsp <% String test ="my variable";  %></h1>
    <%= test %>--%>

<h1> Add Student View </h1>
<hr>

<h2> ${requestScope.email}</h2>

<div id="container">
        <form name="addForm" method="GET" action="AddStudentServlet">
            <table>
                    <tr>
                        <td>
                            <label> firstName :</label>
                        </td>
                        <td>
                            <input type="text" name="firstname">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label> lastName :</label>
                        </td>
                        <td>
                            <input type="text" name="lastname">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label> email :</label>
                        </td>
                        <td>
                            <input type="text" name="email">
                        </td>
                    </tr>
            </table>
            <button type="submit" id="save"> Save </button>
        </form>
    </div>
</body>
</html>

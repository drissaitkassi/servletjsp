<%@ page import="com.example.studentappjspservlet.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Web Student Book</title>
    <link type="text/css"rel="stylesheet" href="css/style.css">
</head>
<% List<Student> theStudents = (List<Student>)request.getAttribute("STUDENTS"); %>
<body>

<div id="wrapper">
    <div id="header">
        <h2>List of SG Students</h2>
    </div>
</div>
<div id="container">
    <div id="content">
        <table>
            <tr>
                <th>First Name </th>
                <th>Last Name</th>
                <th>Email </th>
            </tr>
            <% for(Student tempStudent:theStudents) { %>
            <tr><td><%= tempStudent.getFirstName() %></td>
            <td><%= tempStudent.getLastName() %></td>
            <td><%= tempStudent.getEmail() %></td>
            <%} %>
        </table>
    </div>
</div>
<br/>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>
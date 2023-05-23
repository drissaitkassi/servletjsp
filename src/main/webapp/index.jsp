<%@ page import="com.example.studentappjspservlet.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Web Student Book</title>
</head>
<% List<Student> theStudents = (List<Student>)request.getAttribute("STUDENT_LIST"); %>
<body>
<!--  ${STUDENT_LIST}-->
<%= theStudents %>

<br/>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>
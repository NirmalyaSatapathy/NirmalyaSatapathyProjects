<%@page import="java.text.BreakIterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String[] selected = request.getParameterValues("selected");
if (selected != null) {
    for (String record : selected) {
        String[] parts = record.split("-");
        String user = parts[0];
        int bookId = Integer.parseInt(parts[1]);
        out.println("Fine set for User: " + user + ", Book: " + bookId + "<br>");
    }
    %>
    <a href="AdminMenu.jsp">home page</a>
    <% 
} else {
    out.println("No selections made.");
}
%>
<br><a href="AdminMenu.jsp">home page</a>

</body>
</html>
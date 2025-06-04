<%@page import="com.java.lib.model.TransReturn"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="libraryDao" class="com.java.lib.dao.LibraryDaoImp" />
<%
    List<TransReturn> list = libraryDao.getDefaulters();
%>
<form action="GenerateFine.jsp" method="post">
    <table border="1">
        <tr>
            <th>Select</th><th>User</th><th>Book ID</th><th>From Date</th><th>To Date</th><th>Fine</th>
        </tr>
        <% for (TransReturn tr : list) { %>
        <tr>
            <td><input type="checkbox" name="selected" value="<%=tr.getUserName()%>-<%=tr.getBookId()%>" /></td>
            <td><%=tr.getUserName()%></td>
            <td><%=tr.getBookId()%></td>
            <td><%=tr.getFromDate()%></td>
            <td><%=tr.getToDate()%></td>
            <td>₹<%=tr.getFineAmount()%></td>
        </tr>
        <% } %>
    </table>
    <br>
    <input type="submit" value="Generate Fine"/>
</form>
</body>
</html>
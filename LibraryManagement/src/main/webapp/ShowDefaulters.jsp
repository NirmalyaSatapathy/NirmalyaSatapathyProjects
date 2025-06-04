<%@page import="com.java.lib.model.Defaulter"%>
<%@page import="java.util.List"%>
<%@page import="com.java.lib.dao.LibraryDaoImp"%>
<%@page import="com.java.lib.dao.LibraryDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="Menu.jsp"/>
    <h2 align="center">Defaulters (Books not returned within 21 days)</h2>

    <%
       LibraryDao dao = new LibraryDaoImp();
        List<Defaulter> defaulters = dao.getDefaulters();
    %>
	
    <table border="3">
        <tr>
            <th>User Name</th>
            <th>Book ID</th>
            <th>Book Name</th>
            <th>From Date</th>
            <th>To Date</th>
            <th>Days Overdue</th>
            <th>Fine (₹)</th>
            <th>Status</th>
        </tr>
        <%
            for (Defaulter d : defaulters) {
        %>
        <tr>
            <td><%= d.getUserName() %></td>
            <td><%= d.getBookId() %></td>
            <td><%= d.getBookName() %></td>
            <td><%= d.getFromDate() %></td>
            <td><%= d.getToDate() != null ? d.getToDate() : "Not Returned" %></td>
            <td><%= d.getDaysOverdue() != -1 ? d.getDaysOverdue() : "N/A" %></td>
            <td><%= d.getFine() %></td>
            <td><%= d.getStatus() %></td>
        </tr>
        <%
            }
        %>
       
    </table>
</body>
</html>
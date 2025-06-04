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
	String userName = (String)session.getAttribute("user");
	List<TransReturn> bookHistory = libraryDao.showHistoryById(userName);
%>
<jsp:include page="Menu.jsp"/>
<form action="History.jsp">
<table border="3">
	<tr>
		<th>User Name</th>
		<th>Book Id</th>
		<th>From Date</th>
		<th>To Date</th>
		<th>Fine</th>
	</tr>
<%
	for(TransReturn r : bookHistory) {
%>
	<tr>
		<td><%=r.getUserName()%> </td>
		<td><%=r.getBookId()%> </td>
		<td><%=r.getFromDate()%> </td>
		<td> <%=r.getToDate()%> </td>
		<td><%=r.getFineAmount() %>></td>
	</tr>
<%
	}
%>
</table>

</body>
</html>
<%@page import="java.sql.Date"%>
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
	<jsp:useBean id="libraryDao" class="com.java.lib.dao.LibraryDaoImp"/>
	<%
		String[] bookList=request.getParameterValues("bookid");
		String user=(String)session.getAttribute("user");
		Date date=Date.valueOf(request.getParameter("date"));
		for(String s:bookList)
		{
			int bid=Integer.parseInt(s);
			out.println(libraryDao.issueBook(user, bid, date));
			out.println("<br/>");
		}
	%>
</body>
</html>
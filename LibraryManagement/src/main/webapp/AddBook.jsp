<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method ="post"action="AddBook.jsp">
	Enter Book Name:<br>
	<input type="text" name="name"/><br>
	Enter Author Name:<br>
	<input type="text" name="author"/><br>
	Enter Book edition:<br>
	<input type="text" name="edition"/><br>
	Enter Book Dept:<br>
	<input type="text" name="dept"/><br>
	Enter Availables:<br>
	<input type="number" name="noOfBooks"/><br>
	<input type="submit" value="AddBook" id="button-1"/>
</form>
	<%
	if(request.getParameter("name")!=null)
	{
%>
	<jsp:useBean id="newBook" class="com.java.lib.model.Books"/>
	<jsp:setProperty property="*" name="newBook"/>
	<jsp:useBean id="libraryDao" class="com.java.lib.dao.LibraryDaoImp" />
		<% 
		String op = libraryDao.addBook(newBook);
		if (op!=null)
		{
			
	%>
	<jsp:forward page="AdminMenu.jsp" />
	<%
		} else {
			out.println("some error occuerd...");
		}
	}
	
	
	%>
</body>
</html>
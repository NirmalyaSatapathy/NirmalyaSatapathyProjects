<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="AddAdmin.jsp">
		Admin Name : 
		<input type="text" name="adminName" /> <br/><br/>
		Password : 
		<input type="password" name="passWord" /> <br/><br/>
		Re-Type Password : 
		<input type="password" name="retypePassCode" /> <br/><br/> 
		<input type="submit" value="Create Account" />
	</form>
	<%
		if (request.getParameter("adminName")!=null && 
			request.getParameter("passWord")!=null
				) {
			String pwd = request.getParameter("passWord");
			String reType = request.getParameter("retypePassCode");
			if (pwd.equals(reType)) {
	%>
	<jsp:useBean id="libAdmins" class="com.java.lib.model.LibAdmins" />
	<jsp:useBean id="libDao" class="com.java.lib.dao.LibraryDaoImp" />
	<jsp:setProperty property="*" name="libAdmins"/>
	<%=libDao.createAdmin(libAdmins) %>
	<%
			}
		}
	%>
</body>
</html>
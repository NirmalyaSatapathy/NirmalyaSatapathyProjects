<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%> 
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%> 

<f:view>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employ Login</title>
</head>
<body>
    <h:form>
        <h:panelGrid columns="2" cellpadding="5">
            <h:outputLabel for="username" value="Employ Username:" />
            <h:inputText id="username" value="#{login.name}" required="true" />
            <h:outputLabel for="password" value="Password:" />
            <h:inputSecret id="password" value="#{login.password}" required="true" />

            <h:outputText value="" />
            <h:commandButton value="Login" action="#{loginController.userLoginController(login)}" />

        </h:panelGrid>

        <h:messages globalOnly="true" style="color:red" />
    </h:form>
</body>
</html>
</f:view>

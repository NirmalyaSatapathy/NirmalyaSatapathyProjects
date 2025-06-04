<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%> 
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%> 

<f:view>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Employ</title>
</head>
<body>
<h:form>
    <h:panelGrid columns="2" cellpadding="5">

        <h:outputLabel for="userName" value="Employ Name:" />
        <h:inputText id="userName" value="#{employ.userName}" required="true" />

        <h:outputLabel for="password" value="Password:" />
        <h:inputSecret id="password" value="#{employ.password}" required="true" />

        <h:outputLabel for="dept" value="Department:" />
        <h:inputText id="dept" value="#{employ.dept}" required="true" />

        <h:outputLabel for="email" value="Email:" />
        <h:inputText id="email" value="#{employ.email}" required="true" />

        <h:outputLabel for="type" value="Type (admin/employ):" />
        <h:inputText id="type" value="#{employ.type}" required="true" />

        <h:outputText value="" />
        <h:commandButton value="Add Employ" action="#{employController.addEmployController(employ)}" />

    </h:panelGrid>

    <h:messages globalOnly="true" />
</h:form>
</body>
</html>
</f:view>

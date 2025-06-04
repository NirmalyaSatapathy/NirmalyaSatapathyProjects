<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
<html>
<head>
    <meta charset="UTF-8">
    <title>Return Asset</title>
</head>
<body>
<h:form>
    <h:panelGrid columns="2" cellpadding="5">

        <h:outputLabel for="username" value="Username:" />
        <h:inputText id="username" value="#{returnAsset.name}" required="true" />

        <h:outputLabel for="email" value="Email:" />
        <h:inputText id="email" value="#{returnAsset.email}" required="true" />

        <h:outputLabel for="assetName" value="Asset Name:" />
        <h:inputText id="assetName" value="#{returnAsset.asset}" required="true" />

        <h:outputLabel for="returnDate" value="Return Date:" />
        <h:inputText id="returnDate" value="#{returnAsset.returnDate}" required="true">
            <f:convertDateTime pattern="yyyy-MM-dd" />
        </h:inputText>

        <h:outputText value="" />
        <h:commandButton value="Return Asset" action="#{assetManagerController.returnAssetController(returnAsset)}" />

    </h:panelGrid>

    <h:messages globalOnly="true" />
</h:form>
</body>
</html>
</f:view>

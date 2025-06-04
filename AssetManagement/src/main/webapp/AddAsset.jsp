<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%> 
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%> 

<f:view>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Asset</title>
</head>
<body>
<h:form>
    <h:panelGrid columns="2" cellpadding="5">

        <h:outputLabel for="assetName" value="Asset Name:" />
        <h:inputText id="assetName" value="#{asset.assetName}" required="true" />
<h:outputLabel for="assetType" value="Asset Type:" />
        <h:inputText id="assetType" value="#{asset.assetType}" required="true" />
        <h:outputLabel for="description" value="Description:" />
        <h:inputTextarea id="description" value="#{asset.description}" rows="3" cols="20" required="true" />

        <h:outputLabel for="totalQuantity" value="Total Quantity:" />
        <h:inputText id="totalQuantity" value="#{asset.totalQuantity}" required="true" />

        <h:outputLabel for="availQuantity" value="Available Quantity:" />
        <h:inputText id="availQuantity" value="#{asset.availQuantity}" required="true" />

        <h:outputLabel for="createdBy" value="Created By:" />
        <h:inputText id="createdBy" value="#{sessionScope.adminName}" readonly="true" />

        <h:outputText value="" />
        <h:commandButton value="Add Asset" action="#{assetController.addAssetController(asset)}" />

    </h:panelGrid>

    <h:messages globalOnly="true" />
</h:form>
</body>
</html>
</f:view>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<f:view>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Available Assets</title>
    <style>
        .data-table {
            border-collapse: collapse;
            width: 100%;
        }
        .data-table th, .data-table td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        .data-table th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>

<h2>Available Assets</h2>
<jsp:include page="EmployDashBoard.jsp"/>
<h:dataTable value="#{assetController.showAssetsController()}" var="asset"
             styleClass="data-table"
             rendered="#{not empty assetController.showAssetsController()}">

    <h:column>
        <f:facet name="header"><h:outputText value="Asset Name" /></f:facet>
        <h:outputText value="#{asset.assetName}" />
    </h:column>

    <h:column>
        <f:facet name="header"><h:outputText value="Asset Type" /></f:facet>
        <h:outputText value="#{asset.assetType}" />
    </h:column>

    <h:column>
        <f:facet name="header"><h:outputText value="Description" /></f:facet>
        <h:outputText value="#{asset.description}" />
    </h:column>
<h:column>
        <f:facet name="header"><h:outputText value="Total Quantity" /></f:facet>
        <h:outputText value="#{asset.totalQuantity}" />
    </h:column>
    <h:column>
        <f:facet name="header"><h:outputText value="Available Quantity" /></f:facet>
        <h:outputText value="#{asset.availQuantity}" />
    </h:column>

    <h:column>
        <f:facet name="header"><h:outputText value="Created By" /></f:facet>
        <h:outputText value="#{asset.createdBy}" />
    </h:column>

</h:dataTable>

</body>
</html>
</f:view>

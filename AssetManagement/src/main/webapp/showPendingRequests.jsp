<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<f:view>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Pending Asset Requests</title>
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
<jsp:include page="AdminDashboard.jsp"/>
<h2>Pending Asset Requests</h2>

<h:dataTable value="#{assetManagerController.showPendingRequestsController()}" var="r"
             styleClass="data-table"
             rendered="#{not empty assetManagerController.showPendingRequestsController()}">

    <h:column>
        <f:facet name="header"><h:outputText value="Asset Name" /></f:facet>
        <h:outputText value="#{r.assetName}" />
    </h:column>

    <h:column>
        <f:facet name="header"><h:outputText value="Employee Name" /></f:facet>
        <h:outputText value="#{r.employName}" />
    </h:column>

    <h:column>
        <f:facet name="header"><h:outputText value="Assigned Date" /></f:facet>
        <h:outputText value="#{r.assignedDate}">
            <f:convertDateTime pattern="yyyy-MM-dd HH:mm:ss" />
        </h:outputText>
    </h:column>

    <h:column>
        <f:facet name="header"><h:outputText value="Return Date" /></f:facet>
        <h:outputText value="#{r.returnDate}">
            <f:convertDateTime pattern="yyyy-MM-dd HH:mm:ss" />
        </h:outputText>
    </h:column>

    <h:column>
        <f:facet name="header"><h:outputText value="Status" /></f:facet>
        <h:outputText value="#{r.status}" />
    </h:column>

</h:dataTable>

</body>
</html>
</f:view>

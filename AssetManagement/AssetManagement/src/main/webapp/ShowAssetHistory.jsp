<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>

<f:view>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Asset History</title>
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

<h2>View Asset History</h2>
<jsp:include page="EmployDashBoard.jsp"/>
<h:form>

    <h:panelGrid columns="2" cellpadding="5">

        <h:outputLabel for="username" value="User Name:" />
        <h:inputText id="username" value="#{returnAsset.name}" required="true" />

        <h:outputLabel for="email" value="Email:" />
        <h:inputText id="email" value="#{returnAsset.email}" required="true" />

        <h:outputText />
        <h:commandButton value="Show History" action="#{assetManagerController.showAssetHistoryController(returnAsset)}" />

    </h:panelGrid>

</h:form>

<h:dataTable value="#{assetManagerController.assetHistoryList}" var="history" styleClass="data-table"
             rendered="#{not empty assetManagerController.assetHistoryList}">

    <h:column>
        <f:facet name="header"><h:outputText value="Asset Name" /></f:facet>
        <h:outputText value="#{history.assetName}" />
    </h:column>

    <h:column>
        <f:facet name="header"><h:outputText value="Assigned Date" /></f:facet>
        <h:outputText value="#{history.assignedDate}">
            <f:convertDateTime pattern="yyyy-MM-dd HH:mm:ss" />
        </h:outputText>
    </h:column>

    <h:column>
        <f:facet name="header"><h:outputText value="Return Date" /></f:facet>
        <h:outputText value="#{history.returnDate}">
            <f:convertDateTime pattern="yyyy-MM-dd HH:mm:ss" />
        </h:outputText>
    </h:column>

    <h:column>
        <f:facet name="header"><h:outputText value="Status" /></f:facet>
        <h:outputText value="#{history.status}" />
    </h:column>

</h:dataTable>

</body>
</html>
</f:view>

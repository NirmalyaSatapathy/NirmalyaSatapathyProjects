<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<f:view>
<html>
<head>
    <title>Assets By Type</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<jsp:include page="EmployDashBoard.jsp"/>
<h:form>
	
    <h3>Select Asset Type:</h3>

    <h:selectOneMenu value="#{assetController.selectedType}" onchange="submit()">
        <f:selectItem itemLabel="-- Select Type --" itemValue="" />
        <f:selectItems value="#{assetController.getDistinctTypesController()}" var="type" itemValue="#{type}" itemLabel="#{type}" />
    </h:selectOneMenu>

    <br/><br/>

    <h:dataTable value="#{assetController.searchAssetByTypeController(assetController.selectedType)}" var="asset"
                 rendered="#{not empty assetController.selectedType}">

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

</h:form>

</body>
</html>
</f:view>

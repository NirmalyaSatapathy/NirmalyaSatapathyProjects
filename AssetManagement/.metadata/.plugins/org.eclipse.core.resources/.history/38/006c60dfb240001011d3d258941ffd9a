<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<f:view>
<h:form prependId="false">
    <h:panelGrid columns="3" cellpadding="5" style="width:400px;">
        
        <h:outputLabel for="assetName" value="Asset Name:" style="text-align:right;" />
        <h:inputText id="assetName" value="#{assetManager.assetName}" required="true" requiredMessage="Asset Name is required" style="width:230px;" />
        <h:message for="assetName" style="color:red" />
        
        <h:outputLabel for="employName" value="Employee Name:" style="text-align:right;" />
        <h:inputText id="employName" value="#{assetManager.employName}" required="true" requiredMessage="Employee Name is required" style="width:230px;" />
        <h:message for="employName" style="color:red" />
        
        <h:outputLabel for="assignedDate" value="Assigned Date:" style="text-align:right;" />
        <h:inputText id="assignedDate" value="#{assetManager.assignedDate}" required="true" requiredMessage="Assigned Date is required" style="width:230px;">
            <f:convertDateTime pattern="yyyy-MM-dd" />
        </h:inputText>
        <h:message for="assignedDate" style="color:red" />
        
        <!-- Empty cell for alignment -->
        <h:outputText value="" />
        <h:commandButton value="Issue Asset" action="#{assetManagerController.issueAssetController(assetManager)}" style="margin-left: 150px;" />
        <h:outputText value="" />
    </h:panelGrid>
    
    <!-- Optionally a global messages area -->
    <h:messages globalOnly="true" style="color:red; margin-top:10px;" />
</h:form>
</f:view>

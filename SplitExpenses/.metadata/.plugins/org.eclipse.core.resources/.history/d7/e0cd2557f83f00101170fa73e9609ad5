<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Group Settlement</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen">
	<div class="container mx-auto p-6">
		<h2 class="text-3xl font-bold mb-4">Group Settlement</h2>

		<!-- Group ID input form -->
		<h:form>
			<div class="mb-4">
				<h:outputLabel for="groupId" value="Enter Group ID:" />
				<h:inputText id="groupId" value="#{userDaoImpl.groupId}"
					required="true" styleClass="w-full border p-2 rounded" />
			</div>
			<h:commandButton value="Calculate Settlement"
				action="#{userDaoImpl.settlement(userDaoImpl.groupId)}"
				styleClass="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700" />
		</h:form>

		<!-- Summary -->
		<div class="mt-6">
			<h3 class="text-xl font-semibold mb-2">Summary</h3>
			<div class="bg-white p-4 rounded shadow">
				<p>
					<strong>Advance Collected:</strong>
					<h:outputText value="#{userDaoImpl.advanceCollected}" />
				</p>
				<p>
					<strong>Total Expenditure:</strong>
					<h:outputText value="#{userDaoImpl.totalExpenditure}" />
				</p>
			</div>
		</div>

		<!-- Users Who Should Receive Money -->
		<div class="mt-8">
			<h3 class="text-xl font-semibold mb-2 text-green-700">Users Who Should Receive Money</h3>
			<h:dataTable value="#{userDaoImpl.getPayUser}" var="pay"
				styleClass="w-full table-auto border border-collapse border-gray-300"
				headerClass="bg-green-600 text-white"
				rowClasses="bg-white text-gray-700 border-b">
				<h:column>
					<f:facet name="header">User ID</f:facet>
					<h:outputText value="#{pay.key}" />
				</h:column>
				<h:column>
					<f:facet name="header">Amount to Receive</f:facet>
					<h:outputText value="#{pay.value}" />
				</h:column>
			</h:dataTable>
		</div>

		<!-- Users Who Should Pay Money -->
		<div class="mt-8">
			<h3 class="text-xl font-semibold mb-2 text-red-700">Users Who Should Pay Money</h3>
			<h:dataTable value="#{userDaoImpl.giveUser}" var="give"
				styleClass="w-full table-auto border border-collapse border-gray-300"
				headerClass="bg-red-600 text-white"
				rowClasses="bg-white text-gray-700 border-b">
				<h:column>
					<f:facet name="header">User ID</f:facet>
					<h:outputText value="#{give.key}" />
				</h:column>
				<h:column>
					<f:facet name="header">Amount to Pay</f:facet>
					<h:outputText value="#{give.value}" />
				</h:column>
			</h:dataTable>
		</div>
	</div>
</body>
</html>
</f:view>

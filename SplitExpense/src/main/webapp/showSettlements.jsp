<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Final Disbursement</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        .data-table th, .data-table td {
            border: 1px solid #9CA3AF;
            padding: 0.5rem;
        }
        .data-table th {
            background-color: #16A34A;
            color: white;
            text-align: left;
        }
        .data-table {
            border-collapse: collapse;
            width: 100%;
        }
    </style>
</head>
<body class="bg-black min-h-screen font-sans text-gray-900 flex items-center justify-center p-6">
    <div class="max-w-4xl w-full bg-white rounded-md shadow-md p-6">

        <!-- Header -->
        <header class="text-center mb-6">
            <h2 class="text-3xl font-bold text-green-700">Trip Settlement Summary</h2>
            <p class="text-gray-600">Check who pays and who receives</p>
        </header>
	<jsp:include page="DashBoard.jsp"/>
        <!-- Group ID Input -->
        <h:form prependId="false">
            <div class="flex space-x-3 mb-6">
                <h:inputText id="groupId" value="#{userDaoImpl.groupId}" required="true"
                    styleClass="flex-grow p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-400"
                    onfocus="if(this.value=='Enter Group ID...')this.value='';"
                    onblur="if(this.value=='')this.value='Enter Group ID...';" />
                <h:commandButton value="Calculate" action="#{userDaoImpl.settlement(userDaoImpl.groupId)}"
                    styleClass="bg-green-300 text-black px-4 rounded cursor-pointer hover:bg-green-400" />
            </div>
        </h:form>

        <!-- Refund Table -->
        <section class="mb-6">
            <h2 class="text-green-700 font-semibold mb-2 text-center text-xl">Refund (To Receive)</h2>
            <h:dataTable value="#{userDaoImpl.getPayUser}" var="pay" styleClass="data-table"
                         rendered="#{not empty userDaoImpl.getPayUser}">
                <h:column>
                    <f:facet name="header"><h:outputText value="User ID" /></f:facet>
                    <h:outputText value="#{pay.key}" />
                </h:column>
                <h:column>
                    <f:facet name="header"><h:outputText value="Amount to Receive" /></f:facet>
                    <h:outputText value="#{pay.value}" />
                </h:column>
            </h:dataTable>
        </section>

        <!-- To Pay Table -->
        <section>
            <h2 class="text-red-700 font-semibold mb-2 text-center text-xl">To Pay</h2>
            <h:dataTable value="#{userDaoImpl.giveUser}" var="give" styleClass="data-table"
                         rendered="#{not empty userDaoImpl.giveUser}">
                <h:column>
                    <f:facet name="header"><h:outputText value="User ID" /></f:facet>
                    <h:outputText value="#{give.key}" />
                </h:column>
                <h:column>
                    <f:facet name="header"><h:outputText value="Amount to Pay" /></f:facet>
                    <h:outputText value="#{give.value}" />
                </h:column>
            </h:dataTable>
        </section>

    </div>
</body>
</html>
</f:view>

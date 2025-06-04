<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<f:view>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Group Members</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-black min-h-screen font-sans text-gray-900 flex flex-col items-center justify-start p-6 space-y-6">

    <!-- Dashboard Include -->
    <div class="w-full max-w-4xl">
        <jsp:include page="DashBoard.jsp" />
    </div>

    <!-- Form Card -->
    <div class="max-w-4xl w-full bg-white rounded-md shadow-md p-6">
        <!-- Page Header -->
        <header class="text-center mb-6">
            <h2 class="text-3xl font-bold text-green-700">Add Group Member</h2>
            <p class="text-gray-600">Enter user and group details</p>
        </header>

        <h:form prependId="false" styleClass="space-y-4">
            <!-- Group ID -->
            <div>
                <h:outputLabel for="groupId" value="Group ID" styleClass="block mb-1 text-gray-700 font-semibold" />
                <h:inputText id="groupId" value="#{groupMembers.groupId}"
                             styleClass="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-400" />
            </div>

            <!-- User ID -->
            <div>
                <h:outputLabel for="userId" value="User ID" styleClass="block mb-1 text-gray-700 font-semibold" />
                <h:inputText id="userId" value="#{groupMembers.userId}"
                             styleClass="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-400" />
            </div>

            <!-- Amount Collected -->
            <div>
                <h:outputLabel for="amountCollected" value="Amount Collected" styleClass="block mb-1 text-gray-700 font-semibold" />
                <h:inputText id="amountCollected" value="#{groupMembers.amountCollected}"
                             styleClass="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-400" />
            </div>

            <!-- Submit Button -->
            <div class="text-center mt-4">
                <h:commandButton value="Add Members" action="#{userDao.addGroupMembers(groupMembers)}"
                                 styleClass="bg-green-500 text-white px-6 py-2 rounded hover:bg-green-600 transition" />
            </div>
        </h:form>
    </div>
</body>
</html>
</f:view>

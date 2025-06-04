<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<f:view>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Travel Group</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-black min-h-screen font-sans text-gray-900 flex flex-col items-center justify-start p-6 space-y-6">

    <!-- Dashboard -->
    <div class="w-full max-w-4xl">
        <jsp:include page="DashBoard.jsp" />
    </div>

    <!-- Travel Group Form -->
    <div class="max-w-4xl w-full bg-white rounded-md shadow-md p-6">
        <header class="text-center mb-6">
            <h2 class="text-3xl font-bold text-green-700">Create New Travel Group</h2>
            <p class="text-gray-600">Enter group details below</p>
        </header>

        <h:form prependId="false" styleClass="space-y-4">
            <!-- Created By -->
            <div>
                <h:outputLabel for="createdBy" value="Created By" styleClass="block mb-1 text-gray-700 font-semibold" />
                <h:inputText id="createdBy" value="#{travelGroup.createdBy}"
                             styleClass="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-400" />
            </div>

            <!-- Group Name -->
            <div>
                <h:outputLabel for="groupName" value="Group Name" styleClass="block mb-1 text-gray-700 font-semibold" />
                <h:inputText id="groupName" value="#{travelGroup.groupName}"
                             styleClass="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-400" />
            </div>

            <!-- Start Date -->
            <div>
                <h:outputLabel for="startDate" value="Start Date" styleClass="block mb-1 text-gray-700 font-semibold" />
                <h:inputText id="startDate" value="#{travelGroup.startDate}"
                             styleClass="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-400">
                    <f:convertDateTime pattern="yyyy-MM-dd" />
                </h:inputText>
            </div>

            <!-- End Date -->
            <div>
                <h:outputLabel for="endDate" value="End Date" styleClass="block mb-1 text-gray-700 font-semibold" />
                <h:inputText id="endDate" value="#{travelGroup.endDate}"
                             styleClass="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-400">
                    <f:convertDateTime pattern="yyyy-MM-dd" />
                </h:inputText>
            </div>

            <!-- Initial Amount -->
            <div>
                <h:outputLabel for="initialAmount" value="Initial Amount" styleClass="block mb-1 text-gray-700 font-semibold" />
                <h:inputText id="initialAmount" value="#{travelGroup.initialAmount}"
                             styleClass="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-400" />
            </div>

            <!-- Submit Button -->
            <div class="text-center mt-4">
                <h:commandButton value="Add Group" action="#{userDao.addGroup(travelGroup)}"
                                 styleClass="bg-green-500 text-white px-6 py-2 rounded hover:bg-green-600 transition" />
            </div>
        </h:form>
    </div>

</body>
</html>
</f:view>

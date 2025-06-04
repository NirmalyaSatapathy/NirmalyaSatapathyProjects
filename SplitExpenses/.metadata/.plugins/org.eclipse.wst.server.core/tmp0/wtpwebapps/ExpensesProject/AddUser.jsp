<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<f:view>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add User</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-black min-h-screen font-sans text-gray-900 flex flex-col items-center justify-start p-6 space-y-6">

    <!-- Dashboard -->
    <div class="w-full max-w-4xl">
        <jsp:include page="DashBoard.jsp" />
    </div>

    <!-- Add User Form -->
    <div class="max-w-4xl w-full bg-white rounded-md shadow-md p-6">
        <header class="text-center mb-6">
            <h2 class="text-3xl font-bold text-green-700">Add New User</h2>
            <p class="text-gray-600">Fill in the user details below</p>
        </header>

        <h:form prependId="false" styleClass="space-y-4">
            <!-- User Name -->
            <div>
                <h:outputLabel for="userName" value="User Name" styleClass="block mb-1 text-gray-700 font-semibold"/>
                <h:inputText id="userName" value="#{users.userName}"
                             styleClass="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-400"/>
            </div>

            <!-- Email -->
            <div>
                <h:outputLabel for="email" value="User Email" styleClass="block mb-1 text-gray-700 font-semibold"/>
                <h:inputText id="email" value="#{users.email}"
                             styleClass="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-400"/>
                <h:message for="email" style="color:red" />
            </div>

            <!-- Submit Button -->
            <div class="text-center mt-4">
                <h:commandButton value="Add User" action="#{userDao.addUserDao(users)}"
                                 styleClass="bg-green-500 text-white px-6 py-2 rounded hover:bg-green-600 transition"/>
            </div>
        </h:form>
    </div>

</body>
</html>
</f:view>

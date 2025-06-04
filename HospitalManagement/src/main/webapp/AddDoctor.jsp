<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<f:view>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Doctor</title>
    <style>
        /* Basic Reset */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f7fc;
            color: #333;
            padding: 30px;
        }

        /* Centering the content */
        .center-content {
            text-align: center;
            margin-bottom: 40px;
        }

        h2 {
            color: #3f51b5;
            font-size: 30px;
            margin-bottom: 20px;
        }

        /* Form Container */
        .form-container {
            background-color: #fff;
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .form-container h2 {
            font-size: 28px;
            color: #333;
            margin-bottom: 20px;
        }

        /* Form Layout */
        .form-container label {
            font-size: 16px;
            font-weight: bold;
            color: #3f51b5;
            margin-top: 10px;
            display: block;
        }

        .form-container input[type="text"],
        .form-container select {
            width: 100%;
            padding: 12px;
            margin-top: 5px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
            background-color: #f1f8e9;
            transition: all 0.3s ease-in-out;
        }

        .form-container input[type="text"]:focus,
        .form-container select:focus {
            border-color: #3f51b5;
            outline: none;
            background-color: #fff9c4;
            box-shadow: 0 0 5px rgba(63, 81, 181, 0.6);
        }

        .form-container .submit-button {
            background-color: #3f51b5;
            color: white;
            border: none;
            padding: 12px 30px;
            border-radius: 5px;
            font-size: 18px;
            cursor: pointer;
            transition: background-color 0.3s, transform 0.3s;
        }

        .form-container .submit-button:hover {
            background-color: #5c6bc0;
            transform: scale(1.05);
        }

        /* Responsive Design */
        @media (max-width: 600px) {
            .form-container {
                padding: 15px;
            }

            .form-container h2 {
                font-size: 24px;
            }

            .form-container input[type="text"],
            .form-container select {
                font-size: 14px;
            }

            .form-container .submit-button {
                width: 100%;
            }
        }
    </style>
</head>
<body>

    <div class="center-content">
        <jsp:include page="Home.jsp" />
        <h2>Add New Doctor</h2>
    </div>   
    <h:form prependId="false">
        <div class="form-container">
            <h:panelGrid columns="2" cellpadding="5">
                <h:outputLabel for="doctorName" value="Doctor Name:" /><br>
                <h:inputText id="doctorName" value="#{doctor.doctorName}" required="true" /><br>
                <h:message for="doctorName" style="color:red" /><br>
                <h:outputLabel for="specialization" value="Specialization:" /><br>
                <h:selectOneMenu id="specialization" value="#{doctor.spetialization}" required="true">
                    <f:selectItems value="#{controller.specializations}" />
                </h:selectOneMenu><br>
                <h:message for="specialization" style="color:red" /><br>
                <h:outputLabel for="location" value="Location:" /><br>
                <h:inputText id="location" value="#{doctor.location}" required="true" /><br>
                <h:message for="location" style="color:red" /><br>
                <h:outputLabel for="mobileNo" value="Mobile No:" /><br>
                <h:inputText id="mobileNo" value="#{doctor.mobileNo}" required="true" /><br>
                <h:message for="mobileNo" style="color:red" /><br>
                <h:outputLabel for="status" value="Status:" /><br>
                <h:selectOneMenu id="status" value="#{doctor.isStatus}" required="true">
                    <f:selectItem itemValue="ACTIVE" itemLabel="ACTIVE" />
                    <f:selectItem itemValue="INACTIVE" itemLabel="INACTIVE" />
                </h:selectOneMenu><br>
                <h:message for="status" style="color:red" /><br>
                <h:outputLabel /><br>
                <h:commandButton value="Add Doctor" action="#{controller.addDoctor(doctor)}" styleClass="submit-button" />
            </h:panelGrid>
        </div>
    </h:form>

</body>
</html>
</f:view>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Patient</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f7fc;
            color: #333;
            padding: 30px;
        }

        .center-content {
            text-align: center;
            margin-bottom: 40px;
        }

        h2 {
            color: #3f51b5;
            font-size: 30px;
            margin-bottom: 20px;
        }

        .form-container {
            background-color: #fff;
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        label {
            font-size: 16px;
            font-weight: bold;
            color: #3f51b5;
        }

        input[type="text"], input[type="date"] {
            width: 100%;
            padding: 12px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
            background-color: #f1f8e9;
        }

        input[type="text"]:focus, input[type="date"]:focus {
            border-color: #3f51b5;
            background-color: #fff9c4;
            box-shadow: 0 0 5px rgba(63, 81, 181, 0.6);
        }

        .submit-button {
            background-color: #3f51b5;
            color: white;
            border: none;
            padding: 12px 30px;
            border-radius: 5px;
            font-size: 18px;
            cursor: pointer;
        }

        .submit-button:hover {
            background-color: #5c6bc0;
            transform: scale(1.05);
        }

        .error-message {
            color: red;
            font-size: 14px;
            margin-top: -15px;
            margin-bottom: 10px;
        }

        @media (max-width: 600px) {
            .form-container {
                padding: 15px;
            }
        }
    </style>
</head>
<body>

<f:view>
    <div class="center-content">
        <jsp:include page="PatientHome.jsp" />
        <h2>Add New Patient</h2>
    </div>

    <h:form prependId="false">
        <div class="form-container">
            <h:panelGrid columns="2" cellpadding="5" columnClasses="labelCol,fieldCol">
                <h:outputLabel for="patientName" value="Patient Name:" />
                <h:panelGroup>
                    <h:inputText id="patientName" value="#{patient.patientName}" required="true" />
                    <h:message for="patientName" styleClass="error-message" />
                </h:panelGroup>
                <h:outputLabel for="dateOfBirth" value="Date of Birth:" />
                <h:panelGroup>
                    <h:inputText id="dateOfBirth" value="#{patient.dateOfBirth}">
                        <f:convertDateTime pattern="yyyy-MM-dd" />
                    </h:inputText>
                    <h:message for="dateOfBirth" styleClass="error-message" />
                </h:panelGroup>
                <h:outputLabel for="doctorId" value="Doctor ID:" />
                <h:panelGroup>
                    <h:inputText id="doctorId" value="#{patient.doctor.doctorId}" />
                    <h:message for="doctorId" styleClass="error-message" />
                </h:panelGroup>
                <h:outputLabel />
                <h:commandButton value="Add Patient" action="#{controller.addPatient(patient)}" styleClass="submit-button" />
            </h:panelGrid> 
        </div>
    </h:form>
</f:view>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
<html>
<head>
    <meta charset="UTF-8">
    <title>Patients by Doctor ID</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f7fc;
            color: #333;
            padding: 20px;
        }

        center {
            margin-bottom: 30px;
        }

        h2 {
            color: #3f51b5;
            font-size: 28px;
            margin: 20px 0;
        }

        .form-container {
            background-color: #fff;
            max-width: 500px;
            margin: 0 auto 30px;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .form-container label {
            font-size: 16px;
            font-weight: bold;
            color: #3f51b5;
        }

        .form-container input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-top: 6px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f1f8e9;
            font-size: 16px;
        }

        .form-container input[type="text"]:focus {
            border-color: #3f51b5;
            background-color: #fff9c4;
            outline: none;
        }

        .form-container button,
        .form-container .ui-commandbutton {
            width: 100%;
            padding: 12px;
            font-size: 16px;
            background-color: #3f51b5;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.2s ease;
        }

        .form-container button:hover,
        .form-container .ui-commandbutton:hover {
            background-color: #5c6bc0;
            transform: scale(1.03);
        }

        .data-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 30px;
        }

        .data-table th,
        .data-table td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }

        .data-table th {
            background-color: #3f51b5;
            color: white;
        }

        .data-table tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .data-table tr:hover {
            background-color: #e0e0e0;
        }

        .message {
            color: red;
            font-weight: bold;
            margin-top: 20px;
            text-align: center;
        }

        @media (max-width: 600px) {
            h2 {
                font-size: 24px;
            }

            .form-container {
                padding: 15px;
            }

            .form-container input[type="text"],
            .form-container button {
                font-size: 14px;
            }
        }
    </style>
</head>
<body>
    <center>
        <jsp:include page="PatientHome.jsp" />
        <h2><h:outputText value="Patient Details by Doctor ID" /></h2>
    </center>

    <!-- Form to enter Doctor ID -->
    <h:form styleClass="form-container">
        <h:panelGrid columns="2" cellpadding="5">
            <h:outputLabel value="Enter Doctor ID:" for="doctorId" />
            <h:inputText id="doctorId" value="#{doctor.doctorId}" required="true" />
            
            <h:outputText value="" />
            <h:commandButton value="Search" action="#{controller.searchByDoctorId1}" />
        </h:panelGrid>
    </h:form>

    <!-- Show patients if doctor ID is found -->
    <h:panelGroup rendered="#{not empty controller.ShowPatientByDoctorId(doctor.doctorId)}">
        <h:dataTable value="#{controller.ShowPatientByDoctorId(doctor.doctorId)}" var="d" border="1" styleClass="data-table">
            <h:column>
                <f:facet name="header"><h:outputText value="Patient ID" /></f:facet>
                <h:outputText value="#{d.patientId}" />
            </h:column>

            <h:column>
                <f:facet name="header"><h:outputText value="Name" /></f:facet>
                <h:outputText value="#{d.patientName}" />
            </h:column>

            <h:column>
                <f:facet name="header"><h:outputText value="Doctor ID" /></f:facet>
                <h:outputText value="#{d.doctor.doctorId}" />
            </h:column>

            <h:column>
                <f:facet name="header"><h:outputText value="Date of Birth" /></f:facet>
                <h:outputText value="#{d.dateOfBirth}">
                    <f:convertDateTime pattern="yyyy-MM-dd" />
                </h:outputText>
            </h:column>
        </h:dataTable>
    </h:panelGroup>

    <!-- Show error message if no patients are found -->
    <h:panelGroup rendered="#{not empty doctor.doctorId and empty controller.ShowPatientByDoctorId(doctor.doctorId)}">
        <div class="message">
            Enter valid doctor ID.
        </div>
    </h:panelGroup>

</body>
</html>
</f:view>

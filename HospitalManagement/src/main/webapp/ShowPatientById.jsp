<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
<html>
<head>
    <meta charset="UTF-8">
    <title>Patient Details by ID</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f0f4f8;
            margin: 0;
            padding: 20px;
        }

        center {
            margin-bottom: 30px;
        }

        h2 {
            color: #1976d2;
            font-size: 26px;
        }

        .form-container {
            max-width: 500px;
            margin: 0 auto;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        .form-container label {
            font-weight: bold;
            color: #333;
        }

        .form-container input[type="text"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            background-color: #e8f5e9;
        }

        .form-container input[type="text"]:focus {
            background-color: #fffde7;
            border-color: #1976d2;
            outline: none;
        }

        .form-container button,
        .form-container .ui-commandbutton {
            width: 100%;
            padding: 12px;
            background-color: #1976d2;
            color: white;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.2s;
        }

        .form-container button:hover,
        .form-container .ui-commandbutton:hover {
            background-color: #0d47a1;
        }

        .data-table {
            width: 100%;
            margin-top: 30px;
            border-collapse: collapse;
        }

        .data-table th,
        .data-table td {
            padding: 12px;
            border: 1px solid #ddd;
        }

        .data-table th {
            background-color: #1976d2;
            color: #fff;
        }

        .data-table tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .data-table tr:hover {
            background-color: #f1f1f1;
        }

        .message {
            color: red;
            font-weight: bold;
            text-align: center;
            margin-top: 20px;
        }

        @media (max-width: 600px) {
            .form-container {
                padding: 15px;
            }

            h2 {
                font-size: 22px;
            }
        }
    </style>
</head>
<body>
    <center>
        <jsp:include page="PatientHome.jsp" />
        <h2><h:outputText value="Patient Details by ID" /></h2>
    </center>

    <!-- Search form for Patient ID -->
    <h:form styleClass="form-container">
        <h:outputLabel value="Enter Patient ID:" for="pid" />
        <h:inputText id="pid" value="#{patient.patientId}" required="true" />
        <h:commandButton value="Search" action="#{controller.searchByDoctorId1}" />
    </h:form>

    <!-- Display table if patient is found -->
    <h:panelGroup rendered="#{not empty controller.showPatientById(patient.patientId)}">
        <h:dataTable value="#{controller.showPatientById(patient.patientId)}" var="d" border="1" styleClass="data-table">
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Patient ID" />
                </f:facet>
                <h:outputText value="#{d.patientId}" />
            </h:column>

            <h:column>
                <f:facet name="header">
                    <h:outputText value="Name" />
                </f:facet>
                <h:outputText value="#{d.patientName}" />
            </h:column>

            <h:column>
                <f:facet name="header">
                    <h:outputText value="Doctor ID" />
                </f:facet>
                <h:outputText value="#{d.doctor.doctorId}" />
            </h:column>

            <h:column>
                <f:facet name="header">
                    <h:outputText value="Date of Birth" />
                </f:facet>
                <h:outputText value="#{d.dateOfBirth}" />
            </h:column>
        </h:dataTable>
    </h:panelGroup>
     <h:panelGroup rendered="#{not empty patient.patientId and empty controller.showPatientById(patient.patientId)}">
        <div class="message">
            Enter valid doctor ID.
        </div>
    </h:panelGroup>
</body>
</html>
</f:view>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<f:view>
<html>
<head>
    <meta charset="UTF-8">
    <title>Patient Details</title>
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
        }
    </style>
</head>
<body>
    <center>
        <jsp:include page="PatientHome.jsp" />
        <h2><h:outputText value="Patient Details" /></h2>
    </center>

    <!-- Table to display patient details -->
        <h:dataTable value="#{controller.showPatients()}" var="d" border="1" styleClass="data-table">
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
</body>
</html>
</f:view>

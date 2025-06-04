<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Doctor Details</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Arial', sans-serif;
            background-color: #f9f9f9;
            color: #333;
            padding: 20px;
        }

        .center-content {
            text-align: center;
            margin-bottom: 30px;
        }

        h2 {
            color: #3f51b5;
            font-size: 30px;
            margin-bottom: 20px;
        }

        .form-container {
            background-color: #fff;
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            margin-bottom: 30px;
        }

        .form-container h2 {
            font-size: 28px;
            color: #333;
            margin-bottom: 20px;
        }

        .form-container label {
            font-size: 16px;
            font-weight: bold;
            color: #3f51b5;
            margin-top: 10px;
            display: block;
        }

        .form-container input,
        .form-container button {
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

        .form-container input:focus,
        .form-container button:focus {
            border-color: #3f51b5;
            outline: none;
            background-color: #fff9c4;
            box-shadow: 0 0 5px rgba(63, 81, 181, 0.6);
        }

        .form-container button {
            background-color: #3f51b5;
            color: white;
            border: none;
            font-size: 18px;
            cursor: pointer;
            transition: background-color 0.3s, transform 0.3s;
        }

        .form-container button:hover {
            background-color: #5c6bc0;
            transform: scale(1.05);
        }

        .data-table {
            width: 100%;
            margin-top: 30px;
            border-collapse: collapse;
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

        .data-table td {
            background-color: #fff;
            color: #333;
        }

        .data-table tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .data-table tr:hover {
            background-color: #ddd;
        }

        @media (max-width: 600px) {
            .form-container {
                padding: 15px;
            }

            .form-container h2 {
                font-size: 24px;
            }

            .form-container input,
            .form-container button {
                font-size: 14px;
            }
        }

        .no-doctor-message {
            color: red;
            font-size: 16px;
            font-weight: bold;
            margin-top: 20px;
            text-align: center;
        }
    </style>
</head>
<body>

    <center>
        <jsp:include page="Home.jsp" />
        <h2>Doctor Details by ID</h2>
    </center>

    <div class="form-container">
        <h:form>
            <h:outputLabel value="Enter Doctor ID:" for="doctorId" />
            <h:inputText id="doctorId" value="#{doctor.doctorId}" />
            <h:commandButton value="Search" action="#{controller.searchByDoctorId1}" />
        </h:form>
    </div>

    <!-- Show table if data exists -->
    <h:panelGroup rendered="#{not empty controller.searchByDoctorId(doctor.doctorId)}">
        <h:dataTable value="#{controller.searchByDoctorId(doctor.doctorId)}" var="d" border="1" styleClass="data-table">
            <h:column>
                <f:facet name="header"><h:outputText value="Doctor ID" /></f:facet>
                <h:outputText value="#{d.doctorId}" />
            </h:column>
            <h:column>
                <f:facet name="header"><h:outputText value="Name" /></f:facet>
                <h:outputText value="#{d.doctorName}" />
            </h:column>
            <h:column>
                <f:facet name="header"><h:outputText value="Specialization" /></f:facet>
                <h:outputText value="#{d.spetialization}" />
            </h:column>
            <h:column>
                <f:facet name="header"><h:outputText value="Location" /></f:facet>
                <h:outputText value="#{d.location}" />
            </h:column>
            <h:column>
                <f:facet name="header"><h:outputText value="Mobile No" /></f:facet>
                <h:outputText value="#{d.mobileNo}" />
            </h:column>
            <h:column>
                <f:facet name="header"><h:outputText value="Status" /></f:facet>
                <h:outputText value="#{d.isStatus}" />
            </h:column>
        </h:dataTable>
    </h:panelGroup>

    <!-- Show message if no doctor is found -->
    <h:panelGroup rendered="#{not empty doctor.doctorId and empty controller.searchByDoctorId(doctor.doctorId)}">
        <div class="no-doctor-message">
            Enter valid doctor ID.
        </div>
    </h:panelGroup>

</body>
</html>
</f:view>

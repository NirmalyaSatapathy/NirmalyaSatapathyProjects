<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Doctor Search</title>
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
            padding: 20px;
        }

        /* Centering the content */
        .center-content {
            text-align: center;
            margin-bottom: 30px;
        }

        h2 {
            color: #3f51b5;
            font-size: 30px;
            margin-bottom: 20px;
        }

        /* Form Container */
        .form-container {
            background-color: #fff;
            max-width: 600px;
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

        /* Form Styling */
        .form-container label {
            font-size: 16px;
            font-weight: bold;
            color: #3f51b5;
            margin-top: 10px;
            display: block;
        }

        .form-container select,
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

        .form-container select:focus,
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

        /* DataTable Styling */
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

        /* Responsive Design */
        @media (max-width: 600px) {
            .form-container {
                padding: 15px;
            }

            .form-container h2 {
                font-size: 24px;
            }

            .form-container select,
            .form-container button {
                font-size: 14px;
            }

            .form-container button {
                width: 100%;
            }
        }
    </style>
</head>
<body>
    <!-- Begin the JSF view tag here, inside the body -->
    <f:view>
        <!-- Include the Home.jsp file -->
        <jsp:include page="Home.jsp" />

        <div class="center-content">
            <h2>Doctor Search</h2>
        </div>

        <!-- Form for selecting Specialization -->
        <h:form>
            <div class="form-container">
                <h:outputLabel value="Select Specialization:" for="specialization" />
                <h:selectOneMenu id="specialization" value="#{doctor.spetialization}">
                    <f:selectItem itemLabel="--Select--" itemValue="" />
                    <f:selectItems value="#{controller.specializations}" var="spec" itemValue="#{spec}" itemLabel="#{spec}" />
                </h:selectOneMenu>

                <!-- Search Button to trigger search -->
                <h:commandButton value="Search" action="#{controller.searchByDoctorId1}" styleClass="submit-button" />
            </div>
        </h:form>

        <!-- Display Results if doctorList is not empt valuey -->
        <h:panelGroup rendered="#{not empty doctor.spetialization}">
            <h:outputText value="Search Results:" style="font-weight:bold;" /><br/><br/>

            <!-- Table to display the list of doctors -->
            <h:dataTable value="#{controller.showDoctorByCategory(doctor.spetialization)}" var="doc" border="1" styleClass="data-table">
                <h:column>
                    <f:facet name="header"><h:outputText value="Doctor ID"/></f:facet>
                    <h:outputText value="#{doc.doctorId}" />
                </h:column>
                <h:column>
                    <f:facet name="header"><h:outputText value="Name"/></f:facet>
                    <h:outputText value="#{doc.doctorName}" />
                </h:column>
                <h:column>
                    <f:facet name="header"><h:outputText value="Spetialization"/></f:facet>
                    <h:outputText value="#{doc.spetialization}" />
                </h:column>
                <h:column>
                    <f:facet name="header"><h:outputText value="Location"/></f:facet>
                    <h:outputText value="#{doc.location}" />
                </h:column>
                <h:column>
                    <f:facet name="header"><h:outputText value="Mobile no"/></f:facet>
                    <h:outputText value="#{doc.mobileNo}" />
                </h:column>
            </h:dataTable>
        </h:panelGroup>

    </f:view> 
</body>
</html>

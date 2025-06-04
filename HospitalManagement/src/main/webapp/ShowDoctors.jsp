<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
<html>
<head>
    <meta charset="UTF-8">
    <title>Doctor List</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f9f9f9;
            color: #333;
            padding: 20px;
        }

        h2 {
            color: #3f51b5;
            font-size: 30px;
            margin: 20px 0;
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

        center {
            margin-bottom: 30px;
        }

        @media (max-width: 600px) {
            h2 {
                font-size: 24px;
            }

            .data-table th,
            .data-table td {
                font-size: 14px;
                padding: 8px;
            }
        }
    </style>
</head>
<body>
    <center>
        <jsp:include page="Home.jsp" />
        <h2><h:outputText value="List of Doctors" /></h2>
    </center>

    <h:dataTable value="#{doctorDao.showDoctor()}" var="d" border="1" styleClass="data-table">

        <h:column>
            <f:facet name="header">
                <h:outputText value="Doctor ID" />
            </f:facet>
            <h:outputText value="#{d.doctorId}" />
        </h:column>

        <h:column>
            <f:facet name="header">
                <h:outputText value="Name" />
            </f:facet>
            <h:outputText value="#{d.doctorName}" />
        </h:column>

        <h:column>
            <f:facet name="header">
                <h:outputText value="Specialization" />
            </f:facet>
            <h:outputText value="#{d.spetialization}" />
        </h:column>

        <h:column>
            <f:facet name="header">
                <h:outputText value="Location" />
            </f:facet>
            <h:outputText value="#{d.location}" />
        </h:column>

        <h:column>
            <f:facet name="header">
                <h:outputText value="Mobile No" />
            </f:facet>
            <h:outputText value="#{d.mobileNo}" />
        </h:column>

        <h:column>
            <f:facet name="header">
                <h:outputText value="Status" />
            </f:facet>
            <h:outputText value="#{d.isStatus}" />
        </h:column>

    </h:dataTable>
</body>
</html>
</f:view>

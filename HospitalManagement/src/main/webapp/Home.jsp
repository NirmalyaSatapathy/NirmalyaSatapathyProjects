<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Navigation Bar</title>
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
        }

        /* Navigation Bar Styling */
        .navbar {
            background-color: #004d99;
            padding: 10px 0;
            text-align: center;
            font-size: 18px;
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 20px; /* Space between links */
        }

        .navbar a {
            color: white;
            text-decoration: none;
            padding: 12px 20px;
            font-size: 18px;
            border-radius: 5px;
            transition: background-color 0.3s, transform 0.3s;
        }

        .navbar a:hover {
            background-color: #ff9800;
            color: white;
            transform: scale(1.05);  /* Adds subtle zoom effect on hover */
        }

        .navbar a.active {
            background-color: #ff9800;
            color: white;
            border-radius: 5px;
        }

        /* Responsive Design */
        @media (max-width: 600px) {
            .navbar {
                flex-direction: column; /* Stack the items vertically on small screens */
                gap: 10px; /* Reduced gap between links for better spacing on small screens */
            }

            .navbar a {
                font-size: 16px;
                padding: 10px 20px;
                width: 100%; /* Ensure links take up full width */
                text-align: center; /* Center text inside links */
            }
        }
    </style>
</head>
<body>

    <!-- Navigation Bar -->
    <div class="navbar">
        <a href="ShowDoctors.jsf">Show doctors</a>
        <a href="AddDoctor.jsf">Add doctor</a>
        <a href="ShowDoctorCategory.jsf">Show doctor by category</a>
        <a href="showDoctorId.jsf">Show doctor by Id</a>
        <a href="PatientHome.jsf">Patient page</a>
        <a href="MedicalHistoryHome.jsf">Medical History page</a>
    </div>

</body>
</html>

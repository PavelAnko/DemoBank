<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://kit.fontawesome.com/15c48be795.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
    <script src="${pageContext.request.contextPath}/js/styles_register.js"></script>
    <title>Registration</title>
</head>
<body class="d-flex align-items-center justify-content-center">
<div class="card registration-form-card col-5 bg-transparent border-0">
    <div class="card=body">
        <h1 class="text-white t-1 rg">
            <i class="fa fa-edit"></i>Register
        </h1>
        <form action="/new_user_save" class="reg-form" onsubmit="return validateForm()">
            <div class="row">
                <div class="form-group col">
                    <input type="text" name="first_name" id="first_name" class="form-control form-control-lg t-2" placeholder="Enter First Name"/>
                    <div id="first_name_error" class="error-message">First name is required!</div>
                </div>
                <div class="form-group col">
                    <input type="text" name="last_name" id="last_name" class="form-control form-control-lg t-2" placeholder="Enter Last Name"/>
                    <div id="last_name_error" class="error-message">Last name is required!</div>
                </div>
            </div>
            <div class="form-group col-13">
                <input type="email" name="email" id="email" class="form-control form-control-lg t-2" placeholder="Enter Email"/>
                <div id="email_error" class="error-message">Please enter a valid email address!</div>
            </div>
            <div class="row">
                <div class="form-group col">
                    <input type="password" name="password" id="password" class="form-control form-control-lg t-2" placeholder="Password"/>
                    <div id="password_error" class="error-message">Password is required!</div>
                </div>
                <div class="form-group col">
                    <input type="password" name="confirm_password" id="confirm_password" class="form-control form-control-lg t-2" placeholder="Confirm Password"/>
                    <div id="confirm_password_error" class="error-message">Passwords do not match!</div>
                </div>
            </div>
            <div class="form-group col-13">
                <button type="submit" class="btn btn-success mr-2 t-1">Sign Up</button>
            </div>
            <div id="form_error" class="error-message">Please fix the errors above!</div>
        </form>
        <p class="class-text t-1">
            Already have an account? <a href="/login" class="t-1 text-warning">Log In</a>
        </p>
        <p class="class-text text-warning">
            <i class="fa fa-arrow-alt-circle-left"></i><a href="/" class="t-1 text-warning ml-2">Back</a>
        </p>
    </div>
</div>
</body>
</html>

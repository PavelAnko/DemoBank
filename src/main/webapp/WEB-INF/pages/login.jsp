<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://kit.fontawesome.com/15c48be795.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
    <script src="${pageContext.request.contextPath}/js/styles_login.js"></script>
    <title>LogIn</title>
</head>
<body class="d-flex align-items-center justify-content-center">
<div class="card login-form-card col-4 bg-transparent border-0">
    <div class="card-body">
        <h1 class="t-1">
            <i class="fa fa-user-circle"></i> Log In
        </h1>
        <form action="login_with_an_existing_user" class="login-form">
            <div class="form-group col-13">
                <input type="email" name="email" class="form-control form-control-lg t-2" placeholder="Enter Email"/>
                <div id="email_error" class="error-message" style="display: none;">Email cannot be empty!</div>
            </div>
            <div class="form-group col-13">
                <input type="password" name="password" class="form-control form-control-lg t-2" placeholder="Password"/>
                <div id="password_error" class="error-message" style="display: none;">Password cannot be empty!</div>
            </div>
            <div class="form-group col-13">
                <a href="" class="btn btn-primary t-1" onclick="return validateLoginForm()">Log In</a>
            </div>
            <div id="login_form_error" class="error-message" style="display: none;">Please fill in all fields!</div>
        </form>
        <p class="class-text t-1">
            Don't have an account? <a href="/register" class="t-1 text-warning">Sing Up</a>
        </p>
        <p class="class-text text-warning">
            <i class="fa fa-arrow-alt-circle-left"></i><a href="/" class="t-1 text-warning ml-2">Back</a>
        </p>
    </div>
</div>
</body>
</html>
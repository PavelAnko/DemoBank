function validateForm() {
    var firstName = document.getElementById("first_name").value;
    var lastName = document.getElementById("last_name").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirm_password").value;
    var formError = false;

    var errorMessages = document.getElementsByClassName("error-message");
    for (var i = 0; i < errorMessages.length; i++) {
        errorMessages[i].style.display = "none";
    }

    if (firstName.length < 1) {
        document.getElementById("first_name_error").style.display = "block";
        formError = true;
    } else if (firstName.length <= 1) {
        document.getElementById("first_name_error").innerHTML = "First name cannot be 1 characters long!";
        document.getElementById("first_name_error").style.display = "block";
        formError = true;
    }

    if (lastName.length < 1) {
        document.getElementById("last_name_error").style.display = "block";
        formError = true;
    } else if (lastName.length <= 3) {
        document.getElementById("last_name_error").innerHTML = "Last name cannot be 3 characters long!";
        document.getElementById("last_name_error").style.display = "block";
        formError = true;
    }

    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (email === "" || !emailRegex.test(email)) {
        document.getElementById("email_error").style.display = "block";
        isValid = false;
    } else {
        document.getElementById("email_error").style.display = "none";
    }

    if (password.length < 1) {
        document.getElementById("password_error").style.display = "block";
        formError = true;
    } else if (password.length <= 3) {
        document.getElementById("password_error").innerHTML = "Password cannot be 3 characters long!";
        document.getElementById("password_error").style.display = "block";
        formError = true;
    }

    if (confirmPassword.length < 1) {
        document.getElementById("confirm_password_error").style.display = "block";
        formError = true;
    } else if (confirmPassword !== password) {
        document.getElementById("confirm_password_error").innerHTML = "Passwords do not match!";
        document.getElementById("confirm_password_error").style.display = "block";
        formError = true;
    }

    if (formError) {
        document.getElementById("form_error").style.display = "block";
        return false;
    }

    return true;

}
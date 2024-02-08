function validateLoginForm() {
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    var formError = false;
    var errorMessages = document.getElementsByClassName("error-message");
    for (var i = 0; i < errorMessages.length; i++) {
        errorMessages[i].style.display = "none";
    }
    if (email.length < 1) {
        document.getElementById("email_error").style.display = "block";
        formError = true;
    }
    if (password.length < 1) {
        document.getElementById("password_error").style.display = "block";
        formError = true;
    }

    if (formError) {
        document.getElementById("form_error").style.display = "block";
        return false;
    }

    return true;
}

function validateLoginForm() {
    var email = document.querySelector(".login-form input[name='email']").value;
    var password = document.querySelector(".login-form input[name='password']").value;

    var emailError = document.getElementById("email_error");
    var passwordError = document.getElementById("password_error");

    if (email.trim() === "") {
        emailError.style.display = "block";
        passwordError.style.display = "none";
        return false;
    }
    if (password.trim() === "") {
        passwordError.style.display = "block";
        emailError.style.display = "none";
        return false;
    }

    document.getElementById("login_form_error").style.display = "none";
    return true;
}
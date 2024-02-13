function validatePaymentForm() {
    var selectCard = document.getElementById("select_refill_card").value;
    var selectAmount = document.getElementById("select_amount").value;
    var formError = false;
    var errorMessages = document.getElementsByClassName("error-message");

    for (var i = 0; i < errorMessages.length; i++) {
        errorMessages[i].style.display = "none";
    }

    if (selectCard === "") {
        document.getElementById("select_refill_card_error").style.display = "block";
        formError = true;
    }

    if (selectAmount === "") {
        document.getElementById("select_refill_amount_error").style.display = "block";
        formError = true;
    }

    if (formError) {
        return false;
    }

    return true;
}

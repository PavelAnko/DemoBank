document.addEventListener("DOMContentLoaded", function() {
    const transactType = document.querySelector("#transact-type");
    const paymentCard = document.querySelector(".refill-card");
    const transactCard = document.querySelector(".transfer-card");

    const refillFormInitialHTML = paymentCard.innerHTML;
    const transferFormInitialHTML = transactCard.innerHTML;

    transactType.addEventListener("change", () => {
        switch (transactType.value){
            case "refill":
                paymentCard.style.display = "block";
                if (paymentCard.nextElementSibling) {
                    paymentCard.nextElementSibling.style.display = "none";
                }
                if (transactCard) {
                    transactCard.style.display = "none";
                }
                paymentCard.innerHTML = refillFormInitialHTML;
                break;

            case "transfer":
                if (transactCard) {
                    transactCard.style.display = "block";
                }
                if (transactCard.previousElementSibling) {
                    transactCard.previousElementSibling.style.display = "none";
                }
                if (paymentCard) {
                    paymentCard.style.display = "none";
                }
                transactCard.innerHTML = transferFormInitialHTML;
                break;
        }
    });
});

function capitalizeElementText(elementId) {
    var element = document.getElementById(elementId);
    if (element) {
        var text = element.textContent.trim();
        var capitalizedText = text.toUpperCase();
        element.textContent = capitalizedText;
    }
}

document.addEventListener("DOMContentLoaded", function() {
    capitalizeElementText("usernameUSD");
    capitalizeElementText("usernameUAH");
});

window.addEventListener('DOMContentLoaded', (event) => {
    const errorContainer = document.getElementById('errorContainer');
    const errorMessage = document.getElementById('errorMessage');

    const error = sessionStorage.getItem('error');
    if (error) {
        errorContainer.style.display = 'flex';
        errorMessage.innerText = error;
        sessionStorage.removeItem('error');
    }
});
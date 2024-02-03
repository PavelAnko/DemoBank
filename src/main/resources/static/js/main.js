document.addEventListener("DOMContentLoaded", function() {
    const transactType = document.querySelector("#transact-type");
    const paymentCard = document.querySelector(".payment-card");
    const transactCard = document.querySelector(".transfer-card");

    transactType.addEventListener("change", () => {
        switch (transactType.value){
            case "payment":
                paymentCard.style.display="block";
                if (paymentCard.nextElementSibling) {
                    paymentCard.nextElementSibling.style.display="none";
                }
                break;
            case "transfer":
                if (transactCard.previousElementSibling) {
                    transactCard.previousElementSibling.style.display="none";
                }
                transactCard.style.display="block";
                if (transactCard.nextElementSibling) {
                    transactCard.nextElementSibling.style.display="none";
                }
                break;
        }
    });
});

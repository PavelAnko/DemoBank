function validateTransferForm() {
    var selectCard = document.getElementById("select_transfer_card").value;
    var recipientCard = document.getElementsByName("recipient_card")[0].value;
    var transferAmount = document.getElementsByName("transfer_amount")[0].value;
    var formError = false;

    var selectCardError = document.getElementById("select_transfer_card_error");
    var recipientCardError = document.getElementById("select_transfer_recipient_error");
    var transferAmountError = document.getElementById("select_transfer_amount_error");

    if (selectCard === "") {
        selectCardError.style.display = "block";
        formError = true;
    } else {
        selectCardError.style.display = "none";
    }

    if (recipientCard === "") {
        recipientCardError.style.display = "block";
        formError = true;
    } else {
        recipientCardError.style.display = "none";
    }

    if (transferAmount === "") {
        transferAmountError.style.display = "block";
        formError = true;
    } else {
        transferAmountError.style.display = "none";
    }

    if (formError) {
        return false;
    }

    return true;
}

function getExchangeRate(currencyCode) {
    return new Promise((resolve, reject) => {
        fetch('https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json')
            .then(response => response.json())
            .then(data => {
                const currency = data.find(currency => currency.cc === currencyCode);
                if (currency && currency.rate) {
                    resolve(currency.rate);
                } else {
                    reject(new Error(`Unable to retrieve exchange rate for ${currencyCode} from NBU API`));
                }
            })
            .catch(reject);
    });
}

function getUsdToUahExchangeRate() {
    return getExchangeRate('USD');
}

function getUahToUsdExchangeRate() {
    return getUsdToUahExchangeRate().then(rate => 1 / rate);
}

function updateExchangeRates() {
    getExchangeRate('USD')
        .then(rate => {
            document.getElementById('usdToUahRate').innerText = rate.toFixed(2);
            return getUahToUsdExchangeRate();
        })
        .then(rate => {
            document.getElementById('uahToUsdRate').innerText = rate.toFixed(3);
        })
        .catch(error => {
            console.error('Failed to fetch exchange rates:', error);
            document.getElementById('usdToUahRate').innerText = 'N/A';
            document.getElementById('uahToUsdRate').innerText = 'N/A';
        });
}

updateExchangeRates();
setInterval(updateExchangeRates, 60000);



// window.onload = function() {
//     var urlParams = new URLSearchParams(window.location.search);
//     var error = urlParams.get('error');
//
//     if (error === 'recipient_card_not_exist') {
//         var recipientCardError = document.getElementById("recipient_card_not_exist_error");
//         recipientCardError.style.display = "block";
//         recipientCardError.innerText = "Recipient's card does not exist.";
//     } else if (error === 'invalid_transfer_amount') {
//         var invalidTransferAmountError = document.getElementById("invalid_transfer_amount_error");
//         invalidTransferAmountError.style.display = "block";
//         invalidTransferAmountError.innerText = "Invalid transfer amount.";
//     } else if (error === 'invalid_select_card') {
//         var selectCardError = document.getElementById("select_transfer_card_error");
//         selectCardError.style.display = "block";
//         selectCardError.innerText = "Please select a valid card.";
//     } else {
//         // Если нет ошибки, форма остается открытой
//         var paymentForm = document.getElementById("payment_form");
//         paymentForm.style.display = "block";
//     }
// }

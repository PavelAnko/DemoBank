<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.2/assets/css/docs.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://kit.fontawesome.com/15c48be795.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
    <script src="${pageContext.request.contextPath}/js/refill_card.js"></script>
    <script src="${pageContext.request.contextPath}/js/transfer_card.js"></script>
    <title>Dashboard</title>
</head>
<body>
<header class="main-page-header mb-3">
    <div class="container d-flex align-items-center">
        <div class="company-name t-1">Your Online Bank</div>
        <nav class="navigation">
            <li><a href="/app/dashboard">Dashboard</a></li>
            <li><a href="/app/dashboard/replenishment_history">Replenishment History</a></li>
            <li><a href="/app/dashboard/transact_history">Transaction History</a></li>
        </nav>
        <div class="display-name ms-auto t-1 me-4" >
            <i class="fa-regular fa-user me-1"></i>Welcome: <span>${users.first_name}  ${users.last_name}</span>
        </div>
        <a href="/logout" class="btn btn-sm t-1">
            <i class="fa-solid fa-arrow-right-from-bracket me-1" aria-hidden="true"></i>Sing Out
        </a>
    </div>
</header>

<div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">
    <div class="offcanvas-header">
        <h5 class="offcanvas-title t-2" id="offcanvasExampleLabel">Transact</h5>
        <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body">
        <sele name="card-text t-1" class="t-1">
            Chose an option below to perform a transaction.
        </sele>
        <select name="transact-type" class="form-select my-3" id="transact-type" aria-label="Default select example">
            <option value="" selected disabled>-- Select Transaction Type --</option>
            <option value="refill">Refill</option>
            <option value="transfer">Transfer</option>
        </select>

        <div class="card refill-card">
            <div class="card-body">
                <form action="/refill_balance" method="post" onsubmit="return validatePaymentForm()">
                    <lable for="select_your_card">Select Your Card</lable>
                    <select name="select_card" class="form-control mb-2" id="select_refill_card">
                        <option value="" selected disabled>-- Select Bank Card --</option>
                        <option value="usd_card">USD Card: ${usdCardNumber}</option>
                        <option value="uah_card">UAH Card: ${uahCardNumber}</option>
                    </select>
                    <div id="select_refill_card_error" class="error-message" style="display: none;">Please select a card.</div>
                    <lable for="select_amount">Select The Amount To Top Up</lable>
                    <select name="select_amount" class="form-control mb-2" id="select_amount">
                        <option value="" selected disabled>-- Select An Amount --</option>
                        <option value="10">10</option>
                        <option value="20">20</option>
                        <option value="50">50</option>
                        <option value="100">100</option>
                        <option value="200">200</option>
                        <option value="500">500</option>
                    </select>
                    <div id="select_refill_amount_error" class="error-message mb-2" style="display: none;">Please select an amount.</div>
                    <div class="form-group mb-2">
                        <button type="submit" class="transact-btn transact-edit btn t-1 shadow col-3">Pay</button>
                    </div>
                </form>
            </div>
        </div>

        <div class="card transfer-card">
            <div class="card-body">
                <form action="/money_transfer" method="post" onsubmit="return validateTransferForm()">
                    <div class="form-group">
                        <lable for="select_card">Select Your Card</lable>
                        <select name="select_card" class="form-control" id="select_transfer_card">
                            <option value="" selected disabled>-- Select Bank Card --</option>
                            <option value="usd_card">USD Card: ${usdCardNumber}</option>
                            <option value="uah_card">UAH Card: ${uahCardNumber}</option>
                        </select>
                        <div id="select_transfer_card_error" class="error-message" style="display: none;">Please select a card.</div>
                    </div>
                    <div class="form-group">
                        <lable for="recipient_card">Enter The Recipient's Card</lable>
                        <input type="text" name="recipient_card" class="form-control" placeholder="000000">
                        <div id="select_transfer_recipient_error" class="error-message" style="display: none;">Please enter the recipient card.</div>
                        <div th:if="${recipient_card_not_exist_error}" class="error-message">
                            <span th:text="${recipient_card_not_exist_error}"></span>
                        </div>
                    </div>
                    <div class="form-group mb-2">
                        <lable for="transfer_amount">Enter Transfer Amount</lable>
                        <input type="text" name="transfer_amount" class="form-control" placeholder="00.00">
                        <div id="select_transfer_amount_error" class="error-message" style="display: none;">Please enter the transfer amount.</div>
                    </div>
                    <div class="form-group mb-2">
                        <button type="submit" class="transact-btn transact-edit btn t-1 shadow col-3">Pay</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
    <div class="offcanvas-header">
        <h5 class="offcanvas-title" id="offcanvasRightLabel">Offcanvas right</h5>
        <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body">
        Accounts From Container
    </div>
</div>

<div class="container d-flex">
    <div class="sm-img"></div>
    <button class="transact-edit btn t-1 ms-auto shadow mt-2" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" aria-controls="offcanvasExample">
        <i class="fa-solid fa-wallet me-1 " style="color: #ffffff;"></i>Transact
    </button>
</div>

<div class="container d-flex t-2 mt-3">
    <h2 class="me-auto">USD Balance</h2>
    <h2 class="ms-auto">
        <c:if test="${requestScope.totalUsdBalance !=null}">
            <c:out value="${totalUsdBalance}"/>
        </c:if>
    </h2>
    <i class="fa-solid fa-dollar-sign ms-2 mt-1" style="color: #ffffff; font-size: 28px; height: 21px; width: 30px;"></i>
</div>
<div class="container d-flex t-2">
    <h2 class="me-auto">UAH Balance</h2>
    <h2 class="ms-auto">
        <c:if test="${requestScope.totalUahBalance !=null}">
            <c:out value="${totalUahBalance}"/>
        </c:if>
    </h2>
    <i class="fa-solid fa-hryvnia-sign ms-2 mt-1" style="color: #ffffff; font-size: 28px; height: 21px; width: 30px;"></i>
</div>

<div class="container-card-error" id="errorContainer">
    <div class="card-error" id="errorMessage"></div>
</div>

<div class="container">
    <div class="accordion">
        <div class="accordion-item">
            <h2 class="accordion-header" id="panelsStayOpen-headingOne">
                <button class="accordion-button collapsed t-3" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="false" aria-controls="panelsStayOpen-collapseOne">
                    <strong class="me-1">USD</strong> card
                </button>
            </h2>
            <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse" aria-labelledby="panelsStayOpen-headingOne">
                <div class="accordion-body">
                    <strong>Bank institution</strong>: Your Online Bank<br>
                    <strong>Payee</strong>: <span id="usernameUSD">${users.first_name} ${users.last_name}</span> <br>
                    <strong>Card number</strong>: ${usdCardNumber}<br>
                    <strong>Account currency</strong>: USD
                </div>
            </div>
        </div>

        <div class="accordion-item">
            <h2 class="accordion-header" id="panelsStayOpen-headingTwo">
                <button class="accordion-button collapsed t-3" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseTwo" aria-expanded="false" aria-controls="panelsStayOpen-collapseTwo">
                    <strong class="me-1">UAH</strong> card
                </button>
            </h2>
            <div id="panelsStayOpen-collapseTwo" class="accordion-collapse collapse" aria-labelledby="panelsStayOpen-headingTwo">
                <div class="accordion-body">
                    <strong>Bank institution</strong>: Your Online Bank<br>
                    <strong>Payee</strong>: <span id="usernameUAH">${users.first_name} ${users.last_name}</span> <br>
                    <strong>Card number</strong>: ${uahCardNumber}<br>
                    <strong>Account currency</strong>: UAH
                </div>
            </div>
        </div>

        <div class="accordion-item">
            <h2 class="accordion-header" id="panelsStayOpen-headingThree">
                <button class="accordion-button collapsed t-3" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseThree" aria-expanded="false" aria-controls="panelsStayOpen-collapseThree">
                    <div class="card-title me-1">USD</div> to <div class="card-title ms-1 me-1">UAH</div> exchange rate
                </button>
            </h2>
            <div id="panelsStayOpen-collapseThree" class="accordion-collapse collapse" aria-labelledby="panelsStayOpen-headingThree">
                <div class="accordion-body">
                    <strong>USD to UAH Exchange Rate: </strong><span id="usdToUahRate"></span><br>
                    <strong>UAH to USD Exchange Rate: </strong><span id="uahToUsdRate"></span>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

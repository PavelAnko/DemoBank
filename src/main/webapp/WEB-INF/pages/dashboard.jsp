<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Dashboard</title>
</head>
<body>
<header class="main-page-header mb-3">
    <div class="container d-flex align-items-center">
        <div class="company-name t-1">Your Online Bank</div>
        <nav class="navigation">
            <li><a href="">Dashboard</a></li>
            <li><a href="">Payment History</a></li>
            <li><a href="">Transaction History</a></li>
        </nav>
        <div class="display-name ms-auto t-1 me-4" >
            <i class="fa-regular fa-user me-1"></i>Welcome: <span>First Name - Second Name </span>
        </div>
        <a href="" class="btn btn-sm t-1">
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
            <option value="payment">Payment</option>
            <option value="transfer">Transfer</option>
        </select>

        <div class="card payment-card">
            <div class="card-body">
                <div class="form-group mb-2">
                    <label for="">Account Holder / Recipient</label>
                    <input type="text" name="recipient" name="recipient" class="form-control" placeholder="Enter Account Holder / Recipient Name">
                </div>
                <div class="form-group mb-2">
                    <label for="">Recipient Account Number</label>
                    <input type="text" name="account-number" name="account-number" class="form-control" placeholder="Enter Recipient Account Number">
                </div>
                <div class="form-group">
                    <lable for="">Select Account</lable>
                    <select name="account_id" class="form-control" id="">
                        <option value="">-- Select Account --</option>
                    </select>
                </div>

                <div class="form-group mb-2">
                    <label for="">Reference</label>
                    <input type="text" name="reference" class="form-control" placeholder="Enter Reference">
                </div>
                <div class="form-group mb-2">
                    <label for="">Enter Payment Amount</label>
                    <input type="text" name="payment-amount" class="form-control" placeholder="Enter Payment Amount">
                </div>
                <div class="form-group mb-2">
                    <button class="transact-btn transact-edit btn t-1 shadow col-3">Pay</button>
                </div>
            </div>
        </div>

        <div class="card transfer-card">
            <div class="card-body">
                <div class="form-group">
                    <lable for="">Select Account</lable>
                    <select name="account_id" class="form-control">
                        <option value="">-- Select Account --</option>
                    </select>
                </div>
                <div class="form-group">
                    <lable for="">Select Account</lable>
                    <select name="account_id" class="form-control">
                        <option value="">-- Select Account --</option>
                    </select>
                </div>
                <div class="form-group mb-2">
                    <label for="">Enter Transfer Amount</label>
                    <input type="text" name="transfer-amount" class="form-control" placeholder="Enter Transfer Payment Amount">
                </div>
                <div class="form-group mb-2">
                    <button class="transact-btn transact-edit btn t-1 shadow col-3">Pay</button>
                </div>
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
    <!--    <button class="transact-edit btn t-1 shadow" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight" aria-controls="offcanvasRight">-->
    <!--      <i class="fa-solid fa-credit-card me-1" style="color: #ffffff;"></i>Add New Account-->
    <!--    </button>-->
    <div class="sm-img"></div>
    <button class="transact-edit btn t-1 ms-auto shadow mt-2" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" aria-controls="offcanvasExample">
        <i class="fa-solid fa-wallet me-1 " style="color: #ffffff;"></i>Transact
    </button>
</div>

<div class="container d-flex t-2 py-3">
    <h2 class="me-auto">Total Balance</h2>
    <h2 class="ms-auto">0.00</h2>
</div>

<div class="container">
    <div class="accordion">
        <div class="accordion-item">
            <h2 class="accordion-header transact-bar-edit" id="panelsStayOpen-headingOne">
                <button class="accordion-button collapsed transact-bar-edit" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="false" aria-controls="panelsStayOpen-collapseOne">
                    Accordion Item #1
                </button>
            </h2>
            <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse" aria-labelledby="panelsStayOpen-headingOne">
                <div class="accordion-body">
                    <strong>This is the first item's accordion body.</strong> It is shown by default, until the collapse plugin adds the appropriate classes that we use to style each element. These classes control the overall appearance, as well as the showing and hiding via CSS transitions. You can modify any of this with custom CSS or overriding our default variables. It's also worth noting that just about any HTML can go within the <code>.accordion-body</code>, though the transition does limit overflow.
                </div>
            </div>
        </div>

        <div class="accordion-item">
            <h2 class="accordion-header" id="panelsStayOpen-headingTwo">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseTwo" aria-expanded="false" aria-controls="panelsStayOpen-collapseTwo">
                    Accordion Item #2
                </button>
            </h2>
            <div id="panelsStayOpen-collapseTwo" class="accordion-collapse collapse" aria-labelledby="panelsStayOpen-headingTwo">
                <div class="accordion-body">
                    <strong>This is the second item's accordion body.</strong> It is hidden by default, until the collapse plugin adds the appropriate classes that we use to style each element. These classes control the overall appearance, as well as the showing and hiding via CSS transitions. You can modify any of this with custom CSS or overriding our default variables. It's also worth noting that just about any HTML can go within the <code>.accordion-body</code>, though the transition does limit overflow.
                </div>
            </div>
        </div>

        <div class="accordion-item">
            <h2 class="accordion-header" id="panelsStayOpen-headingThree">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseThree" aria-expanded="false" aria-controls="panelsStayOpen-collapseThree">
                    Accordion Item #3
                </button>
            </h2>
            <div id="panelsStayOpen-collapseThree" class="accordion-collapse collapse" aria-labelledby="panelsStayOpen-headingThree">
                <div class="accordion-body">
                    <strong>This is the third item's accordion body.</strong> It is hidden by default, until the collapse plugin adds the appropriate classes that we use to style each element. These classes control the overall appearance, as well as the showing and hiding via CSS transitions. You can modify any of this with custom CSS or overriding our default variables. It's also worth noting that just about any HTML can go within the <code>.accordion-body</code>, though the transition does limit overflow.
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
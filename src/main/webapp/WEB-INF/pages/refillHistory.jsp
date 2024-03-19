<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.2/assets/css/docs.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://kit.fontawesome.com/15c48be795.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.js"></script>
    <title>Replenishment History</title>
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
<body>
<div class="container my-4">
    <div class="card transaction-history shadow">
        <div class="card-header">
            <i class="fas fa-credit-card me-2" aria-hidden="true"></i> Replenishment History
        </div>
        <div class="card-body">
            <c:if test="${noReplenishmentsMessage != null}">
                <p class="text-center">${noReplenishmentsMessage}</p>  </c:if>
            <c:if test="${refill != null}">
                <table class="table text-center table-striped">
                    <tr>
                        <%--<th>Replenishment ID</th>--%>
                        <th>Replenishment Type</th>
                        <th>Amount</th>
                        <th>Source</th>
                        <th>Listed on</th>
                        <th>Payer</th>
                        <th>Replenished at</th>
                    </tr>
                    <c:forEach items="${refill}" var="refill">
                        <tr>
                            <%--<td>${refill.replenishment_id}</td>--%>
                            <td>${refill.replenishment_type}</td>
                            <td>${refill.amount}</td>
                            <td>${refill.source}</td>
                            <td>${refill.listed_on}</td>
                            <td>${refill.first_name} ${refill.last_name}</td>
                            <td>${refill.created_at}</td>
                        </tr>
                    </c:forEach>
                </table>
                <div class="card-body">
                    <c:if test="${totalPages ne null}">
                        <nav aria-label="Page navigation example" style="height: 50px">
                            <ul class="pagination">
                                <c:forEach var="i" begin="0" end="${totalPages-1}">
                                    <li class="page-item ${currentPage == i ? 'active' : ''}">
                                        <a class="page-link" href="?page=${i}">${i + 1}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </nav>
                    </c:if>
                </div>
            </c:if>
        </div>
    </div>
</div>
</body>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="container">
    <div class="card no-accounts-card">
        <div class="card-body">
            <h1 class="card-title">
                <i class="fas fa-ban text-danger"></i> NO Registered Accounts
            </h1>
            <hr>
            <div class="card-text">
                You currently do not have any registered accounts. <br>
                Please click below to register / add a new account.
            </div>
            <br>
            <form:form action="${pageContext.request.contextPath}/app/dashboard" method="post">
                <button class="btn btn-primary btn-lg shadow" type="submit">Register Now</button>
            </form:form>
        </div>
    </div>
</div>
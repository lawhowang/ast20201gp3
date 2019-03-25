<div class="navbar-user" ng-controller="userCtrl">
    <security:authorize access="!isAuthenticated()">
        <div class="btn-group">
            <button type="button" class="btn btn-light" data-toggle="modal" data-target="#signInModel">Sign In</button>
            <button type="button" class="btn btn-light" data-toggle="modal" data-target="#signUpModel">Create
                Account</button>
            <%@ include file="signin.jsp" %>
            <%@ include file="signup.jsp" %>
        </div>
    </security:authorize>
    <security:authorize access="isAuthenticated()">
        <div class="dropdown">
            <button class="btn btn-light dropdown-toggle user-btn" type="button" id="dropdownMenuButton"
                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="far fa-user-circle avatar px-1 align-baseline"></i>
                <security:authentication property="principal.username" />
            </button>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton">
                <a class="dropdown-item" href="#" ng-click="test()">History</a>
                <a class="dropdown-item" href="#">Credits</a>
                <security:authorize access="hasAuthority('ADMIN')">
                    <a class="dropdown-item" href="/admin">Admin Panel</a>
                </security:authorize>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#" ng-click="signOut()">Sign Out</a>
            </div>
        </div>
    </security:authorize>
</div>
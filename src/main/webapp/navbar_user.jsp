<div class="navbar-user" ng-controller="userCtrl">
    <div class="btn-group" ng-hide="isLogin()">
        <button type="button" class="btn btn-light" data-toggle="modal" data-target="#loginModel">Login</button>
        <button type="button" class="btn btn-light" data-toggle="modal" data-target="#signUpModel">Sign Up</button>
        <jsp:include flush="true" page="login.jsp"></jsp:include>
    </div>
    <div class="dropdown" ng-show="isLogin()">
        <button class="btn btn-light dropdown-toggle user-btn" type="button" id="dropdownMenuButton" data-toggle="dropdown"
            aria-haspopup="true" aria-expanded="false">
            <h5><i class="far fa-user-circle avatar"></i></h5>{{username}}
        </button>
        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton">
            <a class="dropdown-item" href="#">History</a>
            <a class="dropdown-item" href="#">Credits</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#" ng-click="logout()">Logout</a>
        </div>
    </div>
</div>
angular
    .module('app')
    .controller('userCtrl', ['$scope', 'userService', 'jwtHelper', function ($scope, userService, jwtHelper) {
        $scope.isLogin = function () {
            var accessToken = localStorage.getItem('accessToken');
            if (!accessToken)
                return false;
            if (jwtHelper.isTokenExpired(accessToken))
                return false;
            var subject = jwtHelper.decodeToken(accessToken).sub;
            var payload = JSON.parse(subject);
            $scope.username = payload.username;
            console.log($scope.username);
            return true;
        }
        $scope.login = function (user) {
            $scope.loading = true;
            userService.login(user.usernameOrEmail, user.password)
                .then(function successCallback(response) {
                    $scope.loading = false;
                    var element = angular.element(document.querySelector('#loginModel .response'));
                    element.html(``);
                    localStorage.setItem('accessToken', response.data.accessToken);
                    $('#loginModel').modal('hide');
                    $scope.isLogin();
                    console.log(response);
                }, function errorCallback(response) {
                    $scope.loading = false;
                    var element = angular.element(document.querySelector('#loginModel .response'));
                    element.html(`
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        ${response.data.error}
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    `);
                    console.log(response);
                });
        }
        $scope.logout = function () {
            localStorage.removeItem('accessToken');
            $scope.isLogin();
        };
    }]);

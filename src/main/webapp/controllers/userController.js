angular
    .module('app')
    .controller('userCtrl', ['$scope', '$http', 'userService', function ($scope, $http, userService) {
        $scope.signInForm = { };
        $scope.signUpForm = { };
        $scope.clearFormData = function () {
            delete $scope.signInForm;
            delete $scope.signUpForm;
        }
        $scope.signIn = function (user) {
            console.log(user);
            $scope.signInForm.loading = true;
            userService.signIn(user.usernameOrEmail, user.password)
                .then(function successCallback(response) {
                    console.log(response);
                    $scope.signInForm.loading = false;
                    var element = angular.element(document.querySelector('#signInModel .response'));
                    element.html(``);
                    $('#signInModel').modal('hide');
                    $scope.clearFormData();
                    location.reload();
                }, function errorCallback(response) {
                    console.log(response);
                    $scope.signInForm.loading = false;
                    $scope.signInForm.error = response.data.error;
                    var element = angular.element(document.querySelector('#signInModel .response'));
                    element.html(`
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        ${response.data.error}
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    `);
                });
        }
        $scope.signUp = function (user) {
            if (user.confirmPassword != user.password) {
                $scope.signUpError = {};
                $scope.signUpError.confirmPassword = ["The confirm password does not match with the password"];
                return;
            }
            $scope.signUpForm.loading = true;
            userService.signUp(user.username, user.password, user.email)
                .then(function successCallback(response) {
                    console.log(response);
                    $scope.signUpForm.loading = false;
                    var element = angular.element(document.querySelector('#signUpModel .response'));
                    element.html(``);
                    $('#signUpModel').modal('hide');
                    $scope.clearFormData();
                    location.reload();
                }, function errorCallback(response) {
                    console.log(response);
                    $scope.signUpForm.loading = false;
                    $scope.signUpForm.error = response.data.error;
                });
        }
        $scope.signOut = function () {
            userService.signOut()
                .then(function successCallback(response) {
                    location.reload();
                }, function errorCallback(response) {
                    alert('Sign out failed');
                });
        };
    }]);

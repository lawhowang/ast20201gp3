(function () {
    'use strict';

    angular
        .module('app')
        .controller('SignupCtrl', SignupCtrl);

    SignupCtrl.$inject = ['authService', '$routeParams'];

    function SignupCtrl(authService, $routeParams) {
        var vm = this;

        vm.init = init;
        vm.signup = signup;
        vm.errors = {};


        function redirect() {
            if (vm.isModal) {
                if (!$routeParams.afterLogin)
                    location.reload();
                else
                    location.href = $routeParams.afterLogin;
            } else {
                location.href = "/";
            }
        }
        
        function init() {
            if (authService.isAuthenticated()) {
                redirect();
            }
        }

        function signup() {
            if (vm.confirmPassword != vm.password) {
                vm.errors.confirmPassword = ["The confirm password does not match with the password"];
                return;
            }

            vm.loading = true;
            authService.signup(vm.username, vm.password, vm.email)
                .then(function successCallback(response) {
                    vm.loading = false;
                    console.log(response);
                    redirect();
                }, function errorCallback(response) {
                    vm.loading = false;
                    console.log(response);
                    vm.error = response.data.error;
                    vm.errors = response.data.errors;
                });
        }
    }
})();
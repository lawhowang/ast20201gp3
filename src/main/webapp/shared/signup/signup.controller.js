(function () {
    'use strict';

    angular
        .module('app')
        .controller('SignupCtrl', SignupCtrl);

    SignupCtrl.$inject = ['signupService'];

    function SignupCtrl(signupService) {
        var vm = this;

        vm.signup = signup;
        vm.errors = {};

        function signup() {
            if (vm.confirmPassword != vm.password) {
                console.log("test");
                vm.errors.confirmPassword = ["The confirm password does not match with the password"];
                return;
            }

            vm.loading = true;
            signupService.signup(vm.username, vm.password, vm.email)
                .then(function successCallback(response) {
                    vm.loading = false;
                    console.log(response);
                    location.reload();
                }, function errorCallback(response) {
                    vm.loading = false;
                    console.log(response);
                    vm.error = response.data.error;
                    vm.errors = response.data.errors;
                });
        }
    }
})();
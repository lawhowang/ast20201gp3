(function () {
    'use strict';

    angular
        .module('app')
        .controller('SigninCtrl', SigninCtrl);

    SigninCtrl.$inject = ['authService', '$routeParams'];

    function SigninCtrl(authService, $routeParams) {
        var vm = this;
        vm.signIn = signIn;
        vm.signOut = signOut;
        
        function signIn() {
            vm.loading = true;
            authService.signIn(vm.usernameOrEmail, vm.password)
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

        function signOut() {
            vm.loading = true;
            authService.signOut();
        }
    }
})();
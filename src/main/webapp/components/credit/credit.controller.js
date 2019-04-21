(function () {
    'use strict';

    angular
        .module('app')
        .controller('CreditCtrl', CreditCtrl);

    CreditCtrl.$inject = ['creditService', 'authService'];

    function CreditCtrl(creditService, authService) {
        var vm = this;
        vm.credits = 0.0;
        vm.username = authService.user.username;

        vm.getCredits = function () {
            creditService.getCredits().then(
                function successCallback(response) {
                    console.log(response);
                    vm.credits = response.data;
                },
                function errorCallback(response) {
                    console.log(response);
                });
        };

        vm.getCredits();
    }
})();
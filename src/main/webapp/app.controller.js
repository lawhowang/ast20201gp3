(function () {
    'use strict';

    angular
        .module('app')
        .controller('AppCtrl', AppCtrl);

    AppCtrl.$inject = ['$scope', 'authService'];

    function AppCtrl($scope, authService) {
        $scope.init = function (username, email, phone, address, role, credits) {
            authService.authenticated = true;
            authService.user = {
                username: username,
                email: email,
                phone: phone,
                address: address,
                role: role,
                credits: credits
            };
        };
    }
})();
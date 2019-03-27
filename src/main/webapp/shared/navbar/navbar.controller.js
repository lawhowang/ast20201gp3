(function () {
    'use strict';

    angular
        .module('app')
        .controller('NavbarCtrl', NavbarCtrl);

    NavbarCtrl.$inject = ['authService'];

    function NavbarCtrl(authService) {
        var vm = this;
        vm.authService = authService;
    }
})();
(function () {
    'use strict';

    angular
        .module('app')
        .controller('AddUserCtrl', AddUserCtrl);

        AddUserCtrl.$inject = ['userService'];

    function AddUserCtrl(userService) {
        var vm = this;
        vm.data = {};
        vm.data.role = "user";
    }
})();
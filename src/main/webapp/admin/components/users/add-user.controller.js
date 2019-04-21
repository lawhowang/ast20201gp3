(function () {
    'use strict';

    angular
        .module('app')
        .controller('AddUserCtrl', AddUserCtrl);

    AddUserCtrl.$inject = ['userService'];

    function AddUserCtrl(userService) {
        var vm = this;
        
        vm.init = function() {
            vm.data = {};
            vm.data.role = "user";
        };
        vm.init();

        vm.submit = function () {
            if (vm.data) {
                userService.addUser(vm.data)
                    .then(function sucessCallback(response) {
                        console.log("su");
                        console.log(response);
                        vm.data = response.data;
                        vm.success = true;
                        vm.init();
                        delete vm.errors;
                    }, function errorCallBack(response) {
                        console.log("fa");
                        console.log(response);
                        vm.errors = response.data.errors;
                        delete vm.success;
                    });
            }
        }
    }
})();
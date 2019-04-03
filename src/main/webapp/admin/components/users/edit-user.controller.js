(function () {
    'use strict';

    angular
        .module('app')
        .controller('EditUserCtrl', EditUserCtrl);

    EditUserCtrl.$inject = ['userService', '$routeParams', '$window'];

    function EditUserCtrl(userService, $routeParams, $window) {
        var vm = this;
        vm.id = $routeParams.id;

        userService.getUser(vm.id)
            .then(function sucessCallback(response) {
                console.log(response);
                vm.data = response.data;
            }, function errorCallBack(response) {
                console.log(response);
            });

        vm.submit = function () {
            if (vm.data) {
                userService.updateUser(vm.data)
                    .then(function sucessCallback(response) {
                        console.log(response);
                        vm.data = response.data;
                        vm.success = true;
                        delete vm.errors;
                    }, function errorCallBack(response) {
                        console.log(response);
                        vm.errors = response.data.errors;
                        delete vm.success;
                    });
            }
        };

        vm.deleteUser = function () {
            userService.deleteUser(vm.data.id)
                .then(function sucessCallback(response) {
                    console.log(response);
                    $window.history.back();
                }, function errorCallBack(response) {
                    console.log(response);
                });
        };
    }
})();
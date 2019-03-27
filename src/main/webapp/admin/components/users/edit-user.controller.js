(function () {
    'use strict';

    angular
        .module('app')
        .controller('EditUserCtrl', EditUserCtrl);

    EditUserCtrl.$inject = ['userService', '$routeParams'];

    function EditUserCtrl(userService, $routeParams) {
        var vm = this;
        vm.id = $routeParams.id;

        userService.getUser(vm.id)
            .then(function sucessCallback(response) {
                console.log(response);
                vm.data = response.data;
            }, function errorCallBack(response) {
                console.log(response);
            });
    }
})();
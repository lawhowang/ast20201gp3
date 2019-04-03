(function () {
    'use strict';

    angular
        .module('app')
        .controller('DashboardCtrl', DashboardCtrl);

    DashboardCtrl.$inject = ['userService'];

    function DashboardCtrl(userService) {
        var vm = this;
        userService.getTotalUserCount()
            .then(function sucessCallback(response) {
                console.log(response);
                vm.totalUserCount = response.data;
            }, function errorCallBack(response) {
                console.log(response);
            });
    }
})();
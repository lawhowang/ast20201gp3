(function () {
    'use strict';

    angular
        .module('app')
        .controller('DashboardCtrl', DashboardCtrl);

    DashboardCtrl.$inject = ['dashboardService', 'productService'];

    function DashboardCtrl(dashboardService, productService) {
        var vm = this;
        dashboardService.getFigures("alltime")
            .then(function sucessCallback(response) {
                console.log(response);
                vm.alltime = response.data;
            }, function errorCallBack(response) {
                console.log(response);
            });
        vm.getFigures = function (range) {
            dashboardService.getFigures(range)
                .then(function sucessCallback(response) {
                    console.log(response);
                    vm.data = response.data;
                    if (vm.data.topSellingProduct != 0)
                        getTopSellingProductInfo(vm.data.topSellingProduct);
                }, function errorCallBack(response) {
                    console.log(response);
                });
        };

        function getTopSellingProductInfo(id) {
            productService.getProduct(id)
                .then(function sucessCallback(response) {
                    console.log(response);
                    vm.product = response.data;
                }, function errorCallBack(response) {
                    console.log(response);
                });
        }
        vm.getFigures("today");
    }
})();
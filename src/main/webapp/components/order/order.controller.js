(function () {
    'use strict';

    angular
        .module('app')
        .controller('OrderCtrl', OrderCtrl);

    OrderCtrl.$inject = ['orderService', '$routeParams'];

    function OrderCtrl(orderService, $routeParams) {
        var vm = this;

        vm.page = 1;
        if ($routeParams.page) {
            vm.page = $routeParams.page;
        }

        vm.getOrders = function (page) {
            orderService.getOrders(page).then(
                function successCallback(response) {
                    console.log(response);
                    vm.currPage = response.data.currPage;
                    vm.maxPages = response.data.maxPages;
                    vm.orders = response.data.items;
                },
                function errorCallback(response) {
                    console.log(response);
                });
        };

        vm.getOrders(vm.page);

        vm.cancelOrder = function (orderId) {
            orderService.cancelOrder(orderId).then(
                function successCallback(response) {
                    console.log(response);
                    location.reload();
                },
                function errorCallback(response) {
                    console.log(response);
                });
        };
        vm.orderStatusMessage = function (orderStatus) {
            switch (orderStatus) {
                case -1:
                    return 'Cancelled';
                case 0:
                    return 'Recieved';
                case 1:
                    return 'Proccessing';
                case 2:
                    return 'Shipping';
                case 3:
                    return 'Completed';
            }
        };
    }
})();
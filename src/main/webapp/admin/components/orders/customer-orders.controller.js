(function () {
    'use strict';

    angular
        .module('app')
        .controller('CustomerOrdersCtrl', CustomerOrdersCtrl);

    CustomerOrdersCtrl.$inject = ['orderService', 'userService', '$location', '$routeParams'];

    function CustomerOrdersCtrl(orderService, userService, $location, $routeParams) {
        var vm = this;

        vm.getOrders = function (query, page) {
            orderService.getOrders(query, page)
                .then(function successCallback(response) {
                    console.log(response);
                    vm.orders = response.data.items;
                    for (var i = 0, len = vm.orders.length; i < len; i++) {
                        vm.getUserName(vm.orders, i);
                        vm.getTotalPrice(vm.orders, i);
                    }
                    vm.currPage = response.data.currPage;
                    vm.maxPages = response.data.maxPages;
                }, function errorCallback(response) {
                    console.log(response);
                });
        }

        vm.submitSearch = function () {
            if (vm.q)
                $location.path('/orders/customer-orders/search/' + vm.q);
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
        vm.getUserName = function (order, i) {
            userService.getUser(order[i].userId).then(
                function successCallback(response) {
                    console.log(response);
                    var username = response.data.username;
                    order[i].username = username;
                },
                function errorCallback(response) {
                    console.log(response);
                });
        };
        vm.getTotalPrice = function (order, i) {
            var totalPrice = 0;
            if (!order[i].orderProducts) return 0;
            for (var j = 0, len = order[i].orderProducts.length; j < len; j++) {
                totalPrice += order[i].orderProducts[j].price;
            }
            order[i].totalPrice = totalPrice;
        };

        if ($routeParams.q) {
            vm.searchMode = true;
        }
        vm.q = $routeParams.q;
        var page = $routeParams.page ? $routeParams.page : 1;
        vm.getOrders(vm.q, page);
    }
})();
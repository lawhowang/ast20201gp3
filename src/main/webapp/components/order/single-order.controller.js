(function () {
    'use strict';

    angular
        .module('app')
        .controller('SingleOrderCtrl', SingleOrderCtrl);

    SingleOrderCtrl.$inject = ['orderService', 'productService', 'authService', 'creditService', '$routeParams', '$location'];

    function SingleOrderCtrl(orderService, productService, authService, creditService, $routeParams, $location) {
        var vm = this;

        vm.getCredits = function () {
            if (!authService.authenticated) return 0;
            creditService.getCredits().then(
                function successCallback(response) {
                    console.log(response);
                    vm.credits = response.data;
                },
                function errorCallback(response) {
                    console.log(response);
                });
        };
        vm.getCredits();

        vm.getBalance = function () {
            return vm.credits - vm.getTotalPrice();
        };

        vm.orderId = $routeParams.id;
        orderService.getOrder(vm.orderId).then(
            function successCallback(response) {
                console.log(response);
                var order = response.data;
                vm.totalPrice = order.totalPrice;
                vm.orderProducts = order.orderProducts;
                vm.orderStatus = order.orderStatus;
                vm.paymentStatus = order.paymentStatus;
                vm.confirmDate = order.confirmDate;
                vm.lastUpdateDate = order.lastUpdateDate;
                vm.message = order.message;
                vm.adminMessage = order.adminMessage;
                for (var i = 0, len = vm.orderProducts.length; i < len; i++) {
                    vm.getProductsWithDetails(vm.orderProducts, i);
                }
            },
            function errorCallback(response) {
                console.log(response);
            });

        vm.getProductsWithDetails = function (orderProducts, i) {
            var orderProduct = orderProducts[i];
            productService.getProduct(orderProduct.id)
                .then(function successCallback(response) {
                    console.log(response);
                    var product = response.data;
                    product.amount = orderProduct.amount;
                    product.price = orderProduct.price;
                    orderProducts[i] = product;
                }, function errorCallback(response) {
                    console.log(response);
                });
        };

        vm.getTotalPrice = function () {
            var totalPrice = 0;
            if (!vm.orderProducts) return 0;
            for (var i = 0, len = vm.orderProducts.length; i < len; i++) {
                totalPrice += vm.orderProducts[i].price * vm.orderProducts[i].amount;
            }
            return totalPrice;
        };

        vm.cancelOrder = function () {
            orderService.cancelOrder(vm.orderId).then(
                function successCallback(response) {
                    console.log(response);
                    $location.path(`/orders`);
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
        vm.paymentStatusMessage = function (paymentStatus) {
            switch (paymentStatus) {
                case 0:
                    return 'Unpaid';
                case 1:
                    return 'Paid';
            }
        };
    }
})();
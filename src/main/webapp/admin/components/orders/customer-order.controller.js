(function () {
    'use strict';

    angular
        .module('app')
        .controller('CustomerOrderCtrl', CustomerOrderCtrl);

    CustomerOrderCtrl.$inject = ['orderService', 'userService', 'productService', '$location', '$routeParams'];

    function CustomerOrderCtrl(orderService, userService, productService, $location, $routeParams) {
        var vm = this;

        vm.orderId = $routeParams.id;

        vm.init = function () {
            orderService.getOrder(vm.orderId).then(
                function successCallback(response) {
                    console.log(response);
                    vm.order = response.data;
                    vm.getCustomerName(vm.order.userId);
                    for (var i = 0, len = vm.order.orderProducts.length; i < len; i++) {
                        vm.getProducts(vm.order.orderProducts, i);
                    }
                },
                function errorCallback(response) {
                    console.log(response);
                });
        };
        vm.init();


        vm.getCustomerName = function (id) {
            userService.getUser(id).then(
                function successCallback(response) {
                    console.log(response);
                    vm.order.username = response.data.username;
                },
                function errorCallback(response) {
                    console.log(response);
                });
        };

        vm.getProducts = function (orderProducts, i) {
            productService.getProduct(orderProducts[i].id).then(
                function successCallback(response) {
                    console.log(response);
                    orderProducts[i].name = response.data.name;
                    orderProducts[i].price = response.data.price;
                    orderProducts[i].image = response.data.image;
                    orderProducts[i].quantity = response.data.quantity;
                },
                function errorCallback(response) {
                    console.log(response);
                });
        };

        vm.decrease = function (product) {
            if (product.amount <= 1)
                return;
            product.amount -= 1;
        };

        vm.increase = function (product) {
            if (product.amount >= product.quantity && product.quantity != null)
                return;
            product.amount += 1;
        };

        vm.updateAmount = function (product, oldAmount) {
            if (product.amount > product.quantity && product.quantity != null) {
                product.amount = oldAmount;
                return;
            }
            if (product.amount < 1) {
                product.amount = oldAmount;
                return;
            }
        };

        vm.removeProduct = function(id) {
            var orderProducts =  vm.order.orderProducts;
            for (var i = 0, len = orderProducts.length; i < len; i++) {
                if (orderProducts[i].id == id) {
                    orderProducts.splice(i, 1);
                }
            }
        };

        vm.getTotalPrice = function () {
            var totalPrice = 0;
            var orderProducts =  vm.order.orderProducts;
            if (!orderProducts) return 0;
            for (var i = 0, len = orderProducts.length; i < len; i++) {
                totalPrice += orderProducts[i].price * orderProducts[i].amount;
            }
            return totalPrice;
        };

        vm.submit = function () {
            orderService.updateOrder(vm.order.id, vm.order)
                .then(function sucessCallback(response) {
                    console.log(response);
                    vm.init();
                    vm.success = true;
                    delete vm.errors;
                }, function errorCallBack(response) {
                    console.log(response);
                    vm.errors = response.data.errors;
                    delete vm.success;
                });
        };
    }
})();
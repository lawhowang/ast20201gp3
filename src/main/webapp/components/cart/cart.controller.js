(function () {
    'use strict';

    angular
        .module('app')
        .controller('CartCtrl', CartCtrl);

    CartCtrl.$inject = ['cartService', 'productService', 'orderService', '$location'];

    function CartCtrl(cartService, productService, orderService, $location) {
        var vm = this;
        vm.cartProducts = [];
        vm.totalPrice = 0.0;

        vm.loadCart = function () {
            //  reset
            vm.cartProducts = [];
            vm.totalPrice = 0.0;

            cartService.getItems()
                .then(function successCallback(response) {
                        console.log(response);
                        vm.totalPrice = response.data.totalPrice;
                        var cartItems = response.data.cartItems;
                        for (var i = 0; i < cartItems.length; i++) {
                            vm.getCartProduct(cartItems[i].product, cartItems[i].amount);
                        }
                        console.log(vm.cartProducts);
                    },
                    function errorCallback(response) {
                        console.log(response);
                    });
        };

        vm.loadCart();

        vm.getCartProduct = function (productId, amount) {
            productService.getProduct(productId)
                .then(function successCallback(response) {
                    vm.cartProducts.push(response.data);
                    vm.cartProducts[vm.cartProducts.length - 1].amount = amount; // attach amount field to product (amount: quantity to buy)
                }, function errorCallback(response) {

                });
        };

        vm.refreshCart = function () {
            vm.loadCart();
        };

        vm.removeCartProduct = function (productId) {
            cartService.removeItem(productId)
                .then(function successCallback(response) {
                        console.log(response);
                        vm.refreshCart();
                    },
                    function errorCallback(response) {
                        console.log(response);
                    });
        };

        vm.decrease = function (product) {
            if (product.amount <= 1)
                return;
            product.amount -= 1;
            cartService.updateItemAmount(product.id, product.amount);
        };

        vm.increase = function (product) {
            if (product.amount >= product.quantity && product.quantity != null)
                return;
            product.amount += 1;
            cartService.updateItemAmount(product.id, product.amount);
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
            cartService.updateItemAmount(product.id, product.amount);
        };

        vm.clear = function () {
            cartService.clear().then(
                function successCallback(response) {
                    console.log(response);
                    vm.refreshCart();
                },
                function errorCallback(response) {
                    console.log(response);
                });
        };

        vm.checkout = function () {
            if (!vm.message) vm.message = "";
            orderService.createOrder(vm.cartProducts, vm.message).then(
                function successCallback(response) {
                    console.log(response);
                    var orderId = response.data.id;
                    $location.path(`/orders/${orderId}`);
                    vm.clear();
                },
                function errorCallback(response) {
                    vm.errors = response.data.errors;
                    console.log(response);
                });
        };
    }
})();
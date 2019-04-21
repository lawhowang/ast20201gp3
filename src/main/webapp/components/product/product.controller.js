(function () {
    'use strict';

    angular
        .module('app')
        .controller('ProductCtrl', ProductCtrl);

    ProductCtrl.$inject = ['productService', 'cartService', 'authService', '$routeParams'];

    function ProductCtrl(productService, cartService, authService, $routeParams) {
        var vm = this;
        var productId = $routeParams.id;
        vm.amount = 1;

        if (productId) {
            productService.getProduct(productId)
                .then(function successCallback(response) {
                    vm.product = response.data;
                    vm.getComments(1);
                    console.log(response);
                }, function errorCallback(response) {
                    console.log(response);
                });
        }
        if (authService.authenticated) {
            cartService.getItems()
                .then(function successCallback(response) {
                        console.log(response);
                        var cartItems = response.data.cartItems;
                        for (var i = 0; i < cartItems.length; i++) {
                            if (cartItems[i].product == productId)
                                vm.addedToCart = true;
                        }
                        console.log(vm.cartProducts);
                    },
                    function errorCallback(response) {
                        console.log(response);
                    });
        }

        vm.addToCart = function () {
            if (!authService.authenticated) {
                angular.element('#sign-in-btn').click();
                return;
            }
            cartService.addItem(productId, vm.amount)
                .then(function successCallback(response) {
                    vm.addedToCartMessage = true;
                    vm.addedToCart = true;
                    console.log(response);
                }, function errorCallback(response) {
                    vm.addedToCartMessage = false;
                    vm.addedToCart = true;
                    console.log(response);
                });
        };

        vm.updateAmount = function (newAmount, oldAmount) {
            if (newAmount > vm.product.quantity && vm.product.quantity != null) {
                vm.amount = oldAmount;
                return;
            }
            if (newAmount < 1) {
                vm.amount = oldAmount;
                return;
            }
        };

        vm.getComments = function (page) {
            productService.getProductComments(productId, page)
                .then(function successCallback(response) {
                    vm.comments = response.data;
                    console.log(response);
                }, function errorCallback(response) {
                    console.log(response);
                });
        };
        vm.rating = 0;
        vm.submitComment = function () {
            if (!authService.authenticated) {
                vm.commentResponse = "Please login first";
                angular.element('#sign-in-btn').click();
                return;
            }
            if (vm.rating == 0) {
                vm.commentResponse = "Please rate the product first";
                return;
            }
            if (!vm.comment || vm.comment.length == 0) {
                vm.commentResponse = "Comment cannot be empty";
                return;
            }
            productService.submitComment(productId, vm.comment, vm.rating)
                .then(function successCallback(response) {
                    vm.comments = response.data;
                    vm.commentResponse = "Comment successfully";
                    delete vm.comment;
                    delete vm.rating;
                    console.log(response);
                }, function errorCallback(response) {
                    console.log(response);
                    vm.commentResponse = "Error while posting the comment";
                });
        };
    }
})();
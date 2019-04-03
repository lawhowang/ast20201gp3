(function () {
    'use strict';

    angular
        .module('app')
        .controller('EditProductCtrl', EditProductCtrl);

    EditProductCtrl.$inject = ['productService', 'categoryService', '$routeParams', '$window'];

    function EditProductCtrl(productService, categoryService, $routeParams, $window) {
        var vm = this;
        vm.id = $routeParams.id;

        categoryService.getCategories()
            .then(function successCallback(response) {
                console.log(response);
                vm.categories = response.data;
            }, function errorCallback(response) {
                console.log(response);
            });

        productService.getProduct(vm.id)
            .then(function sucessCallback(response) {
                console.log(response);
                vm.product = response.data;
            }, function errorCallBack(response) {
                console.log(response);
            });

        vm.categoryIndex = function (category) {
            if (!vm.product) return -1;
            var categories = vm.product.categories;
            for (var i = 0, len = categories.length; i < len; i++) {
                if (categories[i].id == category.id)
                    return i;
            }
            return -1;
        };

        vm.toggleCategory = function (category) {
            var i = vm.categoryIndex(category);
            if (i > -1) {
                vm.product.categories.splice(i, 1);
            } else {
                vm.product.categories.push(category);
            }
        };

        vm.submit = function () {
            if (vm.product) {
                productService.updateProduct(vm.product.id, vm.product, vm.productImage ? vm.productImage[0] : null)
                    .then(function sucessCallback(response) {
                        console.log(response);
                        vm.product = response.data;
                        vm.success = true;
                        delete vm.errors;
                    }, function errorCallBack(response) {
                        console.log(response);
                        vm.errors = response.data.errors;
                        delete vm.success;
                    });
            }
        };

        vm.deleteProduct = function () {
            productService.deleteProduct(vm.product.id)
                .then(function sucessCallback(response) {
                    console.log(response);
                    $window.history.back();
                }, function errorCallBack(response) {
                    console.log(response);
                });
        }
    }
})();
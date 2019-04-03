(function () {
    'use strict';

    angular
        .module('app')
        .controller('AddProductCtrl', AddProductCtrl);

    AddProductCtrl.$inject = ['productService', 'categoryService'];

    function AddProductCtrl(productService, categoryService) {
        var vm = this;

        categoryService.getCategories()
            .then(function successCallback(response) {
                console.log(response);
                vm.categories = response.data;
            }, function errorCallback(response) {
                console.log(response);
            });

        vm.product = {};
        vm.product.categories = [];

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
            productService.addProduct(vm.product, vm.productImage ? vm.productImage[0] : null)
                .then(function sucessCallback(response) {
                    console.log(response);
                    vm.success = true;
                    delete vm.errors;
                    vm.product = {};
                    vm.product.categories = {};
                    delete vm.productImage;
                }, function errorCallBack(response) {
                    console.log(response);
                    vm.errors = response.data.errors;
                    delete vm.success;
                });
        };
    }
})();
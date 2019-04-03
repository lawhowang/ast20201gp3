(function () {
    'use strict';

    angular
        .module('app')
        .controller('CategoryCtrl', CategoryCtrl);

    CategoryCtrl.$inject = ['categoryService', '$routeParams'];

    function CategoryCtrl(categoryService, $routeParams) {
        var vm = this;

        vm.categoryId = $routeParams.id;

        vm.getCategory = function () {
            if (vm.categoryId) {
                categoryService.getCategory(vm.categoryId)
                    .then(function successCallback(response) {
                        vm.category = response.data;
                        console.log(response);
                    }, function errorCallback(response) {
                        console.log(response);
                    });
            }
        }

        vm.getProducts = function () {
            var categoryId = $routeParams.id;
            var page = $routeParams.page;
            if (categoryId) {
                categoryService.getProducts(categoryId, page)
                    .then(function successCallback(response) {
                        vm.products = response.data.items;
                        vm.currPage = response.data.currPage;
                        vm.maxPages = response.data.maxPages;
                        console.log(response);
                    }, function errorCallback(response) {
                        console.log(response);
                    });
            }
        };
        vm.getCategory();
        vm.getProducts();
    }
})();
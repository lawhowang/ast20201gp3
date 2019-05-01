(function () {
    'use strict';

    angular
        .module('app')
        .controller('CategoryCtrl', CategoryCtrl);

    CategoryCtrl.$inject = ['categoryService', '$routeParams', '$location'];

    function CategoryCtrl(categoryService, $routeParams, $location) {
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

        vm.getProducts = function (page) {
            var categoryId = $routeParams.id;
            if (categoryId) {
                categoryService.getProducts(categoryId, page)
                    .then(function successCallback(response) {
                        vm.products = response.data.items;
                        vm.currPage = response.data.currPage;
                        vm.maxPages = response.data.maxPages;
                        $location.search('page', vm.currPage);
                        console.log(response);
                    }, function errorCallback(response) {
                        console.log(response);
                    });
            }
        };
        vm.getCategory();
        if ($routeParams.page)
            vm.getProducts($routeParams.page);
        else
            vm.getProducts(1);
    }
})();
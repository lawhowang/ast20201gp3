(function () {
    'use strict';

    angular
        .module('app')
        .controller('ProductsCtrl', ProductsCtrl);

    ProductsCtrl.$inject = ['productService', 'categoryService', '$location', '$routeParams'];

    function ProductsCtrl(productService, categoryService, $location, $routeParams) {
        var vm = this;

        vm.getProduct = function (page, filter) {
            productService.getProducts(page, filter)
                .then(function successCallback(response) {
                    console.log(response);
                    vm.products = response.data.items;
                    vm.currPage = response.data.currPage;
                    vm.maxPages = response.data.maxPages;
                }, function errorCallback(response) {
                    console.log(response);
                });
        }

        vm.submitSearch = function () {
            if (vm.q)
                $location.path('/products/products/search/' + vm.q);
        };

        if ($routeParams.q) {
            vm.searchMode = true;
            vm.q = $routeParams.q;
            var filter = {};
            filter.name = $routeParams.q;
            filter.category = null;
            filter.priceLowerBound = null;
            filter.priceUpperBound = null;
            var page = $routeParams.page ? $routeParams.page : 1;
            vm.getProduct(page, filter);
        } else {
            if ($routeParams.page)
                vm.getProduct($routeParams.page);
            else
                vm.getProduct(1);
        }

    }
})();
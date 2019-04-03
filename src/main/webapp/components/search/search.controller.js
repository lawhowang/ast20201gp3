(function () {
    'use strict';

    angular
        .module('app')
        .controller('SearchCtrl', SearchCtrl);

    SearchCtrl.$inject = ['searchService', '$routeParams'];

    function SearchCtrl(searchService, $routeParams) {
        var vm = this;
        vm.name = $routeParams.name;
        vm.category = $routeParams.category;
        vm.page = $routeParams.page;

        if (vm.category && vm.name) {
            searchService.searchProduct(vm.name, vm.category, vm.page)
                .then(function successCallback(response) {
                    vm.products = response.data.items;
                    vm.currPage = response.data.currPage;
                    vm.maxPages = response.data.maxPages;
                    console.log(response);
                }, function errorCallback(response) {
                    console.log(response);
                });
        }
    }
})();
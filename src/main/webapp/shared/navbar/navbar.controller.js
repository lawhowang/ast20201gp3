(function () {
    'use strict';

    angular
        .module('app')
        .controller('NavbarCtrl', NavbarCtrl);

    NavbarCtrl.$inject = ['authService', 'categoryListService', 'searchService', '$location', '$routeParams'];

    function NavbarCtrl(authService, categoryListService, searchService, $location, $routeParams) {
        var vm = this;
        vm.authService = authService;
        vm.selectedCategoryIndex = 0;
        vm.searchName = $routeParams.name ? $routeParams.name : '';
        categoryListService.getCategories()
            .then(function successCallback(response) {
                console.log(response);
                vm.categories = [];
                vm.categories.push({
                    id: 0,
                    name: "All"
                });
                vm.categories = vm.categories.concat(response.data);
            }, function errorCallback(response) {
                console.log(response);
            });

        vm.submitSearch = function () {
            var categoryId = vm.categories[vm.selectedCategoryIndex].id;
            var name = vm.searchName;
            if (name) {
                $location.path(`/search/${categoryId}/${name}`);
            }
        };

        vm.searchProduct = function () {

            if (!vm.searchName || vm.searchName.length == 0) {
                delete vm.searchProducts;
                return;
            }
            vm.searching = true;
            var categoryId = vm.categories[vm.selectedCategoryIndex].id;
            searchService.searchProduct(vm.searchName, categoryId, 1)
                .then(function sucessCallback(response) {
                    console.log(response);
                    vm.searchProducts = response.data.items.slice(0, 5);
                    vm.searching = false;
                }, function errorCallBack(response) {
                    console.log(response);
                    vm.searching = false;
                });
        };

        vm.cancelSearch = function () {
            if (vm.searchEnter) return;
            delete vm.searchName;
            delete vm.searchProducts;
        };
    }
})();
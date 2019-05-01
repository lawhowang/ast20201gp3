(function () {
    'use strict';

    angular
        .module('app')
        .controller('NavbarCtrl', NavbarCtrl);

    NavbarCtrl.$inject = ['authService', 'categoryListService', 'searchService', '$location', '$routeParams', '$q'];

    function NavbarCtrl(authService, categoryListService, searchService, $location, $routeParams, $q) {
        var vm = this;
        vm.authService = authService;
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

        vm.selectedCategoryId = 0;
        vm.selectedCategoryText = "All";

        vm.submitSearch = function () {
            var categoryId = vm.selectedCategoryId;
            var name = vm.searchName;
            if (name) {
                $location.path(`/search/${categoryId}/${name}`);
            }
        };

        vm.searchProducts = [];
        var canceller;
        vm.searchProduct = function () {
            //vm.searchProducts.length = 0;

            if (!vm.searchName || vm.searchName.length == 0) {
                return;
            }

            if (canceller) {
                canceller.resolve();
            }
            vm.searching = true;
            canceller = $q.defer();
            var keyword = vm.searchName;
            searchService.searchProduct(keyword, vm.selectedCategoryId, 1)
                .then(function sucessCallback(response) {
                    console.log(response);
                    if (vm.searchName == keyword) {
                        vm.searchProducts = response.data.items.slice(0, 5);
                    }
                    vm.searching = false;
                }, function errorCallBack(response) {
                    console.log(response);
                    vm.searching = false;
                });
        };

        vm.cancelSearch = function () {
            if (vm.searchEnter || vm.searchFocus || vm.searching) return;
            vm.searchName = '';
            vm.searchProducts.length = 0;
        };

        vm.selectCategory = function (catId, catName) {
            angular.element('#search-text').trigger('focus');
            vm.selectedCategoryId = catId;
            vm.selectedCategoryText = catName;
            vm.searchProduct();
        };
    }
})();
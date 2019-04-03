(function () {
    'use strict';

    angular
        .module('app')
        .controller('NavbarCtrl', NavbarCtrl);

    NavbarCtrl.$inject = ['authService', 'categoryListService', '$location', '$routeParams'];

    function NavbarCtrl(authService, categoryListService, $location, $routeParams) {
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

        vm.submitSearch = function() {
            var categoryId = vm.categories[vm.selectedCategoryIndex].id;
            var name = vm.searchName;
            if (name) {
                $location.path(`/search/${categoryId}/${name}`);
            }
        };
    }
})();
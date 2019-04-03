(function () {
    'use strict';

    angular
        .module('app')
        .controller('CategoryListCtrl', CategoryListCtrl);

    CategoryListCtrl.$inject = ['categoryListService'];

    function CategoryListCtrl(categoryListService) {
        var vm = this;
        categoryListService.getCategories()
            .then(function successCallback(response) {
                console.log(response);
                vm.categories = response.data;
            }, function errorCallback(response) {
                console.log(response);
            });
    }
})();
(function () {
    'use strict';

    angular
        .module('app')
        .factory('categoryService', categoryService);

    categoryService.$inject = ['$http'];

    function categoryService($http) {
        var service = {
            getCategories: getCategories,
            updateCategories: updateCategories
        };

        return service;

        function getCategories() {
            return $http.get("/admin/api/products/categories");
        }

        function updateCategories(data) {
            return $http.put("/admin/api/products/categories", data);
        }
    }
})();
(function () {
    'use strict';

    angular
        .module('app')
        .factory('categoryService', categoryService);

    categoryService.$inject = ['$http'];

    function categoryService($http) {
        var service = {
            getCategory: getCategory,
            getProducts: getProducts,
            getCategories: getCategories
        };

        return service;

        function getCategory(id) {
            return $http.get(`/api/categories/${id}`);
        }

        function getProducts(id, page) {
            if (page)
                return $http.get(`/api/categories/${id}/products/${page}`);
            else
                return $http.get(`/api/categories/${id}/products`);
        }

        function getCategories() {
            return $http.get("/api/categories");
        }
    }
})();
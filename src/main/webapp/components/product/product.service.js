(function () {
    'use strict';

    angular
        .module('app')
        .factory('productService', productService);

    productService.$inject = ['$http'];

    function productService($http) {
        var service = {
            getProduct: getProduct
        };

        return service;

        function getProduct(id) {
            return $http.get(`/api/products/${id}`);
        }
    }
})();
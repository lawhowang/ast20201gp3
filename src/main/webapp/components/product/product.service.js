(function () {
    'use strict';

    angular
        .module('app')
        .factory('productService', productService);

    productService.$inject = ['$http'];

    function productService($http) {
        var service = {
            getProduct: getProduct,
            getProductComments: getProductComments,
            submitComment: submitComment,
            getLatestProducts: getLatestProducts
        };

        return service;

        function getProduct(id) {
            return $http.get(`/api/products/${id}`);
        }

        function getProductComments(id, page) {
            var data = {
                page: page
            };
            return $http.get(`/api/comments/${id}`, {
                params: data
            });
        }
        function submitComment(id, comment, rating) {
            var data = {
                comment: comment,
                rating: rating
            };
            return $http.post(`/api/comments/${id}`, data);
        }
        function getLatestProducts() {
            return $http.get(`/api/products/latest`);
        }
    }
})();
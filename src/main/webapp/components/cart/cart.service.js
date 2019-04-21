(function () {
    'use strict';

    angular
        .module('app')
        .factory('cartService', cartService);

    cartService.$inject = ['$http'];

    function cartService($http) {
        var service = {
            getItems: getItems,
            addItem: addItem,
            updateItemAmount: updateItemAmount,
            removeItem: removeItem,
            clear: clear
        };

        return service;

        function getItems() {
            return $http.get(`/api/cart`);
        }

        function addItem(productId, amount) {
            var data = {
                amount: amount
            };
            return $http.post(`/api/cart/${productId}`, null, {
                params: data
            });
        }

        function updateItemAmount(productId, amount) {
            var data = {
                amount: amount
            };
            return $http.put(`/api/cart/${productId}`, null, {
                params: data
            });
        }

        function removeItem(productId) {
            return $http.delete(`/api/cart/${productId}`);
        }

        function clear() {
            return $http.delete(`/api/cart`);
        }
    }
})();
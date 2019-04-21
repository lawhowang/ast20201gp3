(function () {
    'use strict';

    angular
        .module('app')
        .factory('orderService', orderService);

    orderService.$inject = ['$http'];

    function orderService($http) {
        var service = {
            getOrders: getOrders,
            getOrder: getOrder,
            createOrder: createOrder,
            cancelOrder: cancelOrder
        };

        return service;

        function getOrders(page) {
            var data = {
                page: page
            };
            return $http.get(`/api/order/`, {
                params: data
            });
        }

        function getOrder(orderId) {
            return $http.get(`/api/order/${orderId}`);
        }

        function createOrder(products, message) {
            var data = {
                orderProducts: products,
                message: message
            };
            return $http.post(`/api/order`, data);
        }

        function cancelOrder(orderId) {
            return $http.delete(`/api/order/${orderId}`);
        }
    }
})();
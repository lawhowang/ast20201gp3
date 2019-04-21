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
            updateOrder: updateOrder
        };

        return service;

        function getOrders(query, page) {
            var data = {
                query: query,
                page: page
            };
            return $http.get("/admin/api/orders", {
                params: data
            });
        }

        function getOrder(orderId) {
            return $http.get(`/admin/api/orders/${orderId}`);
        }

        function updateOrder(orderId, order) {
            return $http.put(`/admin/api/orders/${orderId}`, order);
        }
    }
})();
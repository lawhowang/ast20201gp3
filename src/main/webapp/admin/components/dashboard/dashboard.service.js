(function () {
    'use strict';

    angular
        .module('app')
        .factory('dashboardService', dashboardService);

    dashboardService.$inject = ['$http'];

    function dashboardService($http) {
        var service = {
            getFigures: getFigures
        };

        return service;

        function getFigures(range) {
            return $http.get(`/admin/api/figures/${range}`);
        }
    }
})();
(function () {
    'use strict';

    angular
        .module('app')
        .factory('categoryListService', categoryListService);

    categoryListService.$inject = ['$http'];

    function categoryListService($http) {
        var service = {
            getCategories: getCategories
        };

        return service;

        function getCategories() {
            return $http.get("/api/categories");
        }
    }
})();
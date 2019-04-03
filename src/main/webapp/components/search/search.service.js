(function () {
    'use strict';

    angular
        .module('app')
        .factory('searchService', searchService);

    searchService.$inject = ['$http'];

    function searchService($http) {
        var service = {
            searchProduct: searchProduct
        };

        return service;

        function searchProduct(name, category, page) {
            if (page)
                return $http.get(`/api/search/${category}/${name}/${page}`);
            else
                return $http.get(`/api/search/${category}/${name}`);
        }
    }
})();
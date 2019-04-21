(function () {
    'use strict';

    angular
        .module('app')
        .factory('creditService', creditService);

        creditService.$inject = ['$http'];

    function creditService($http) {
        var service = {
            getCredits: getCredits
        };

        return service;

        function getCredits() {
            return $http.get(`/api/credits`);
        }
    }
})();
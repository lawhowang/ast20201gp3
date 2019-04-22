(function () {
    'use strict';

    angular
        .module('app')
        .factory('settingService', settingService);

    settingService.$inject = ['$http'];

    function settingService($http) {
        var service = {
            siteTitle: undefined,
            getSiteTitle: getSiteTitle,
            getSiteConfig: getSiteConfig
        };

        return service;

        function getSiteTitle() {
            return $http.get('/api/setting/site-title');
        }
        
        function getSiteConfig() {
            return $http.get('/api/setting');
        }
    }
})();
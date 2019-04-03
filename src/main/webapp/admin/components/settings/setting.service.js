(function () {
    'use strict';

    angular
        .module('app')
        .factory('settingService', settingService);

    settingService.$inject = ['$http'];

    function settingService($http) {
        var service = {
            getSiteConfig: getSiteConfig,
            updateSiteConfig: updateSiteConfig
        };

        return service;

        function getSiteConfig() {
            return $http.get("/admin/api/settings/site-config");
        }

        function updateSiteConfig(config) {
            return $http.put("/admin/api/settings/site-config", config);
        }

    }
})();
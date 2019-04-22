(function () {
    'use strict';

    angular
        .module('app')
        .factory('settingService', settingService);

    settingService.$inject = ['$http'];

    function settingService($http) {
        var service = {
            getSiteConfig: getSiteConfig,
            updateSiteConfig: updateSiteConfig,
            getSiteTitle: getSiteTitle
        };

        return service;

        function getSiteConfig() {
            return $http.get("/admin/api/settings/site-config");
        }

        function updateSiteConfig(config) {

            return $http.put("/admin/api/settings/site-config", {
                config: config
            });
        }

        function getSiteTitle() {
            return $http.get('/api/setting/site-title');
        }
    }
})();
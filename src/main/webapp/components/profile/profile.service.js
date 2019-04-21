(function () {
    'use strict';

    angular
        .module('app')
        .factory('profileService', profileService);

        profileService.$inject = ['$http'];

    function profileService($http) {
        var service = {
            getProfile: getProfile,
            updateProfile: updateProfile
        };

        return service;
        
        function getProfile() {
            return $http.get(`/api/profile`);
        }
        function updateProfile(profile) {
            return $http.put(`/api/profile`, profile);
        }
    }
})();
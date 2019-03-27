(function () {
    'use strict';

    angular
        .module('app')
        .factory('signupService', signupService);

    signupService.$inject = ['$http'];

    function signupService($http) {
        var service = {
            signup: signup
        };

        return service;

        function signup(username, password, email) {
            var data = {
                username: username,
                password: password,
                email: email
            };
            return $http.post('/api/user/', data);
        }
    }
})();
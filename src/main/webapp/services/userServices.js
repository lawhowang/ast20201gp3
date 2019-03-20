(function () {
    'use strict';

    angular
        .module('app')
        .factory('UserService', ['$http'], function ($http) {
            var factory = {};
            factory.Login = function(usernameOrEmail, password) {
                return $http.get('/api/user/login');
            }
            return factory;
        });
})();
(function () {
    'use strict';

    angular
        .module('app')
        .factory('authService', authService);

    authService.$inject = ['$http'];

    function authService($http) {
        var service = {
            signIn: signIn,
            signOut: signOut,
            authenticated: false,
            user: null
        };
        authenticate();

        return service;

        function signIn(usernameOrEmail, password) {
            var data = { usernameOrEmail: usernameOrEmail, password: password };
            return $http.post("/api/user/signin", data);
        }

        function signOut() {
            service.authenticated = false;
            service.user = null;
            return $http.post("/api/user/signout");
        }

        function authenticate() {
            $http.get("/api/user").then(function successCallback(response) {
                console.log(response);
                if (response.data.username) {
                    service.authenticated = true;
                    service.user = response.data;
                }
                else
                    service.authenticate = false;
            }, function errorCallback(response) {
                service.authenticated = false;
            });;
        }
    }
})();
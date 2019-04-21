(function () {
    'use strict';

    angular
        .module('app')
        .factory('authService', authService);

    authService.$inject = ['$http', '$q'];

    function authService($http, $q) {
        var service = {
            signup: signup,
            signIn: signIn,
            signOut: signOut,
            authenticated: false,
            user: null,
            isAuthenticated: isAuthenticated,
            isAnonymous: isAnonymous
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

        function signIn(usernameOrEmail, password) {
            var data = {
                usernameOrEmail: usernameOrEmail,
                password: password
            };
            return $http.post("/api/user/signin", data);
        }

        function signOut() {
            service.authenticated = false;
            service.user = null;
            return $http.post("/api/user/signout");
        }

        function isAuthenticated() {
            if (!service.authenticated) {
                return $q.reject("Unauthenticated");
            }
        }

        function isAnonymous() {
            if (service.authenticated) {
                return $q.reject("Authenticated");
            }
        }
    }
})();
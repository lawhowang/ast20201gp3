(function () {
    'use strict';

    angular
        .module('app')
        .factory('userService', userService);

        userService.$inject = ['$http'];

    function userService($http) {
        var service = {
            getAllUsers: getAllUsers,
            searchUsers: searchUsers,
            getUser: getUser
        };

        return service;

        function getAllUsers(page) {
            var data = { page: page };
            return $http.get("/admin/api/users/all-users", { params: data });
        }

        function searchUsers(username, page) {
            var data = { username: username, page: page };
            return $http.get("/admin/api/users/all-users/search", { params: data });
        }

        function getUser(id) {
            var data = { id: id };
            return $http.get("/admin/api/users/", { params: data });
        }
    }
})();
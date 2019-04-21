(function () {
    'use strict';

    angular
        .module('app')
        .factory('userService', userService);

    userService.$inject = ['$http'];

    function userService($http) {
        var service = {
            getTotalUserCount: getTotalUserCount,
            getAllUsers: getAllUsers,
            searchUsers: searchUsers,
            getUser: getUser,
            updateUser: updateUser,
            addUser: addUser,
            deleteUser: deleteUser
        };

        return service;

        function getTotalUserCount() {
            return $http.get("/admin/api/users/all-users/count");
        }

        function getAllUsers(page) {
            var data = {
                page: page
            };
            return $http.get("/admin/api/users/all-users", {
                params: data
            });
        }

        function searchUsers(username, page) {
            var data = {
                username: username,
                page: page
            };
            return $http.get("/admin/api/users/all-users/search", {
                params: data
            });
        }

        function getUser(id) {
            var data = {
                id: id
            };
            return $http.get("/admin/api/users/", {
                params: data
            });
        }

        function updateUser(data) {
            return $http.put("/admin/api/users/", data);
        }

        function addUser(data) {
            return $http.post(`/admin/api/users/`, data);
        }

        function deleteUser(id) {
            var data = {
                id: id
            };
            return $http.delete("/admin/api/users/", {
                params: data
            });
        }
    }
})();
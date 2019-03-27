(function () {
    'use strict';

    angular
        .module('app')
        .controller('AllUsersCtrl', AllUsersCtrl);

    AllUsersCtrl.$inject = ['userService', '$routeParams'];

    function AllUsersCtrl(userService, $routeParams) {
        var vm = this;
        vm.q = $routeParams.q;
        vm.page = $routeParams.page;

        vm.getAllUsers = function (page) {
            vm.loaded = false;
            vm.searchMode = false;
            userService.getAllUsers(page)
                .then(function successCallback(response) {
                    vm.users = response.data.users;
                    console.log(vm.users);
                    vm.maxPages = response.data.maxPages;
                    vm.currPage = response.data.currPage;
                    vm.loaded = true;
                }, function errorCallback(response) {
                    console.log(response);
                    vm.loaded = true;
                });
        }

        vm.searchUsers = function (q, page) {
            vm.loaded = false;
            vm.searchMode = true;
            userService.searchUsers(q, page)
                .then(function successCallback(response) {
                    vm.users = response.data.users;
                    console.log(vm.users);
                    vm.maxPages = response.data.maxPages;
                    vm.currPage = response.data.currPage;
                    vm.loaded = true;
                }, function errorCallback(response) {
                    console.log(response);
                    vm.loaded = true;
                });
        }
        
        if (!vm.q) {
            if (vm.page)
                vm.getAllUsers(vm.page);
            else
                vm.getAllUsers(1);
        } else {
            if (vm.page)
                vm.searchUsers(vm.q, vm.page);
            else
                vm.searchUsers(vm.q, 1);
        }
    }
})();
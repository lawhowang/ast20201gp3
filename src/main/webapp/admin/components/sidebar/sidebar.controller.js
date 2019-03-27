(function () {
    'use strict';

    angular
        .module('app')
        .controller('SidebarCtrl', SidebarCtrl);

    SidebarCtrl.$inject = ['$location'];

    function SidebarCtrl($location) {
        var vm = this;
        vm.isRoot = function (location) {
            return location === $location.path();
        }
        vm.isActive = function (location) {
            return $location.path().startsWith(location);
        }
    }
})();
(function () {
    'use strict';

    angular
        .module('app')
        .controller('SidebarCtrl', SidebarCtrl);

    SidebarCtrl.$inject = ['$location', 'settingService'];

    function SidebarCtrl($location, settingService) {
        var vm = this;
        
        vm.isRoot = function (location) {
            return location === $location.path();
        };

        vm.isActive = function (location) {
            return $location.path().startsWith(location);
        };

        settingService.getSiteConfig()
            .then(function successCallback(response) {
                console.log(response);
                vm.siteTitle = response.data.config['Site Title'];
            });
    }
})();
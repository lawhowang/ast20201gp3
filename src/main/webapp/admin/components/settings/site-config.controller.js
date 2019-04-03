(function () {
    'use strict';

    angular
        .module('app')
        .controller('SiteConfigCtrl', SiteConfigCtrl);

    SiteConfigCtrl.$inject = ['settingService'];

    function SiteConfigCtrl(settingService) {
        var vm = this;

        settingService.getSiteConfig()
            .then(function sucessCallback(response) {
                vm.configs = response.data;
                console.log(response);
            }, function errorCallBack(response) {
                console.log(response);
            });

        vm.submit = function () {
            console.log(vm.configs);
            settingService.updateSiteConfig(vm.configs)
                .then(function sucessCallback(response) {
                    console.log(response);
                    vm.data = response.data;
                    vm.success = true;
                    delete vm.errors;
                }, function errorCallBack(response) {
                    console.log(response);
                    vm.errors = response.data.errors;
                    delete vm.success;
                });
        }
    }
})();
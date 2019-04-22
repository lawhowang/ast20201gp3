(function () {
    'use strict';

    angular
        .module('app')
        .controller('SiteConfigCtrl', SiteConfigCtrl);

    SiteConfigCtrl.$inject = ['settingService'];

    function SiteConfigCtrl(settingService) {
        var vm = this;
        vm.init = function () {
            settingService.getSiteConfig()
                .then(function sucessCallback(response) {
                    console.log(response);
                    vm.config = response.data.config;
                    vm.slides = JSON.parse(vm.config.Slides);
                    console.log(vm.config);
                }, function errorCallBack(response) {
                    console.log(response);
                });
        };
        vm.init();

        vm.submit = function () {
            vm.config.Slides = JSON.stringify(vm.slides);
            settingService.updateSiteConfig(vm.config)
                .then(function sucessCallback(response) {
                    console.log(response);
                    vm.init();
                    vm.success = true;
                    delete vm.errors;
                }, function errorCallBack(response) {
                    console.log(response);
                    vm.errors = response.data.errors;
                    delete vm.success;
                });
        };

        vm.removeSlide = function (i) {
            vm.slides.splice(i, 1);
        };

        vm.addSlide = function (imageUrl, url) {
            vm.slides.push({
                imageUrl: imageUrl,
                url: url
            });
        };
    }
})();
(function () {
    'use strict';

    angular
        .module('app')
        .controller('HomeCtrl', HomeCtrl);

    HomeCtrl.$inject = ['settingService', 'productService'];

    function HomeCtrl(settingService, productService) {
        var vm = this;

        settingService.getSiteConfig()
            .then(function successCallback(response) {
                console.log(response);
                vm.slides = JSON.parse(response.data.config.Slides);
                console.log(vm.slides);
            });
        productService.getLatestProducts()
            .then(function successCallback(response) {
                console.log(response);
                var temp = response.data;

                vm.latestProducts = [];

                while (temp.length > 0)
                    vm.latestProducts.push(temp.splice(0, 4));

            });
    }
})();
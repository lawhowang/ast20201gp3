(function () {
    'use strict';

    angular
        .module('app')
        .controller('HomeCtrl', HomeCtrl);

    HomeCtrl.$inject = ['settingService', 'productService', '$window', '$scope'];

    function HomeCtrl(settingService, productService, $window, $scope) {
        var vm = this;

        vm.latestProductsOriginal = [];
        vm.latestProducts = [];

        settingService.getSiteConfig()
            .then(function successCallback(response) {
                console.log(response);
                vm.slides = JSON.parse(response.data.config.Slides);
                console.log(vm.slides);
            });

        productService.getLatestProducts()
            .then(function successCallback(response) {
                console.log(response);

                vm.latestProductsOriginal = response.data;

                angular.element($window).on('resize', function () {
                    vm.latestProduct();
                    $scope.$apply();
                });

                vm.latestProduct();

            });

        vm.latestProduct = function () {
            var d = 4;

            if ($window.innerWidth < 992) {
                d = 3;
            }
            if ($window.innerWidth < 768) {
                d = 2;
            }
            if ($window.innerWidth < 576) {
                d = 1;
            }

            var clone = angular.copy(vm.latestProductsOriginal);

            vm.latestProducts.length = 0;
            while (clone.length > 0)
                vm.latestProducts.push(clone.splice(0, d));

        };
        angular.element(function () {
            // fix not sliding automatically in some scenarios
            $('.carousel').carousel();
        });
    }
})();
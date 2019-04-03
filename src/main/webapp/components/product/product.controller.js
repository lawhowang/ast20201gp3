(function () {
    'use strict';

    angular
        .module('app')
        .controller('ProductCtrl', ProductCtrl);

    ProductCtrl.$inject = ['productService', '$routeParams'];

    function ProductCtrl(productService, $routeParams) {
        var vm = this;

        if ($routeParams.id) {
            productService.getProduct($routeParams.id)
                .then(function successCallback(response) {
                    vm.product = response.data;
                    console.log(response);
                }, function errorCallback(response) {
                    console.log(response);
                });
        }
    }
})();
(function () {
    'use strict';

    angular
        .module('app', ['ngAnimate', 'ngRoute'])
        .config(routeConfig);

    routeConfig.$inject = ['$routeProvider'];

    function routeConfig($routeProvider) {
        $routeProvider
            .when('/', { template: '<home></home>', title: 'Home' })
            .when('/user', { template: '<user></user>', title: 'Product' })
            .when('/product', { template: '<product></product>', title: 'Product' })
            .when('/category', { template: '<category></category>', title: 'Category' })
            .otherwise({ redirectTo: '/' });
    }
})();
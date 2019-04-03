(function () {
    'use strict';

    var app = angular
        .module('app', ['ngAnimate', 'ngRoute'])
        .config(routeConfig);

    routeConfig.$inject = ['$routeProvider'];

    function routeConfig($routeProvider) {
        $routeProvider
            .when('/', {
                template: '<home></home>',
                title: 'Home'
            })
            .when('/users', {
                template: '<user></user>',
                title: 'Product'
            })
            .when('/products/:id', {
                template: '<product></product>',
                title: 'Product'
            })
            .when('/categories/:id/:page?', {
                template: '<category></category>',
                title: 'Category'
            })
            .when('/search/:category/:name/:page?', {
                template: '<search></search>',
                title: 'Search'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    app.run(['$rootScope', 'settingService', function ($rootScope, settingService) {
        $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
            $rootScope.title = current.$$route.title;

            settingService.getSiteTitle()
                .then(function successCallback(response) {
                    $rootScope.siteTitle = response.data.val;
                });
        });
    }]);
})();
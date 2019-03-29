(function () {
    'use strict';

    angular
        .module('app', ['ngAnimate', 'ngRoute'])
        .config(routeConfig);

    routeConfig.$inject = ['$routeProvider'];

    function routeConfig($routeProvider) {
        $routeProvider
            .when("/", {
                template: ''
            })
            .when("/users/all-users/:page?", {
                template: '<all-users></all-users>'
            })
            .when("/users/all-users/search/:q/:page?", {
                template: '<all-users></all-users>'
            })
            .when("/users/edit-user/:id", {
                template: '<edit-user></edit-user>'
            })
            .when("/users/add-user", {
                template: '<add-user></add-user>'
            })
            .otherwise({ redirectTo: '/' });
    }
})();
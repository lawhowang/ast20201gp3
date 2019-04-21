(function () {
    'use strict';

    var app = angular
        .module('app', ['ngAnimate', 'ngRoute', 'ui.tree'])
        .config(routeConfig);

    routeConfig.$inject = ['$routeProvider'];

    function routeConfig($routeProvider) {
        $routeProvider
            .when("/", {
                title: 'Dashboard',
                template: '<dashboard></dashboard>'
            })
            .when("/settings/site-config", {
                title: 'Site Configuration',
                template: '<site-config></site-config>'
            })
            .when("/users/all-users/", {
                title: 'All Users',
                template: '<all-users></all-users>'
            })
            .when("/users/all-users/search/:q", {
                title: 'All Users',
                template: '<all-users></all-users>'
            })
            .when("/users/edit-user/:id", {
                title: 'Edit User',
                template: '<edit-user></edit-user>'
            })
            .when("/users/add-user", {
                title: 'Add User',
                template: '<add-user></add-user>'
            })
            .when("/products/categories", {
                title: 'Categories',
                template: '<categories></categories>'
            })
            .when("/products/products", {
                title: 'Products',
                template: '<products></products>'
            })
            .when("/products/products/search/:q", {
                title: 'Products',
                template: '<products></products>'
            })
            .when("/products/edit-product/:id", {
                title: 'Edit Product',
                template: '<edit-product></edit-product>'
            })
            .when("/products/add-product", {
                title: 'Add Product',
                template: '<add-product></add-product>'
            })
            .when("/orders/customer-orders", {
                title: 'Customer Orders',
                template: '<customer-orders></customer-orders>'
            })
            .when("/orders/customer-orders/search/:q", {
                title: 'Customer Orders',
                template: '<customer-orders></customer-orders>'
            })
            .when("/orders/customer-orders/:id", {
                title: 'Customer Order',
                template: '<customer-order></customer-order>'
            })
            .otherwise({
                redirectTo: '/'
            });
    }
    app.config(function ($httpProvider) {
        $httpProvider.interceptors.push(function ($q, $window, $injector) {
            return {
                responseError: function (rejection) {
                    if (rejection.status == 401) {
                        $window.location.reload();
                    }
                    return $q.reject(rejection);
                }
            };
        });
    });
    app.run(['$rootScope', function ($rootScope) {
        $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
            $rootScope.title = current.$$route.title;
        });
    }]);
})();
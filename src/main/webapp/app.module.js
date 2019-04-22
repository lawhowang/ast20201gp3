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
            .when('/signin', {
                template: '<signin></signin>',
                title: 'Sign in',
                resolve: {
                    'auth': function (authService) {
                        return authService.isAnonymous();
                    }
                }
            })
            .when('/signup', {
                template: '<signup></signup>',
                title: 'Sign up',
                resolve: {
                    'auth': function (authService) {
                        return authService.isAnonymous();
                    }
                }
            })
            .when('/credits', {
                template: '<credit></credit>',
                title: 'Credits',
                resolve: {
                    'auth': function (authService) {
                        return authService.isAuthenticated();
                    }
                }
            })
            .when('/profile', {
                template: '<profile></profile>',
                title: 'Profile',
                resolve: {
                    'auth': function (authService) {
                        return authService.isAuthenticated();
                    }
                }
            })
            .when('/profile/edit', {
                template: '<edit-profile></edit-profile>',
                title: 'Edit Profile',
                resolve: {
                    'auth': function (authService) {
                        return authService.isAuthenticated();
                    }
                }
            })
            .when('/profile/:id', {
                template: '<profile></profile>',
                title: 'Profile'
            })
            .when('/products/:id', {
                template: '<product></product>',
                title: 'Product'
            })
            .when('/categories/:id/', {
                template: '<category></category>',
                title: 'Category'
            })
            .when('/search/:category/:name', {
                template: '<search></search>',
                title: 'Search'
            })
            .when("/cart", {
                title: 'Cart',
                template: '<cart></cart>',
                resolve: {
                    'auth': function (authService) {
                        return authService.isAuthenticated();
                    }
                }
            })
            .when('/orders', {
                template: '<order></order>',
                title: 'Orders',
                resolve: {
                    'auth': function (authService) {
                        return authService.isAuthenticated();
                    }
                }
            })
            .when('/orders/:id', {
                template: '<single-order></single-order>',
                title: 'Order',
                resolve: {
                    'auth': function (authService) {
                        return authService.isAuthenticated();
                    }
                }
            })
            .otherwise({
                redirectTo: '/'
            });
    }



    app.config(function ($httpProvider) {
        $httpProvider.interceptors.push(function ($q, $location, $injector) {
            return {
                responseError: function (rejection) {
                    if (rejection.status == 401) {
                        var authService = $injector.get('authService');
                        authService.user = null;
                        authService.authenticated = false;
                        $location.path("/signin");
                    }
                    return $q.reject(rejection);
                }
            };
        });
    });
    app.run(['$rootScope', 'settingService', '$location', function ($rootScope, settingService, $location) {
        $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
            $rootScope.title = current.$$route.title;
            settingService.getSiteConfig()
                .then(function successCallback(response) {
                    console.log(response);
                    $rootScope.siteTitle = response.data.config['Site Title'];
                });
        });
        $rootScope.$on("$routeChangeError", function (event, current, previous, rejection) {
            console.log(rejection);
            if (rejection === 'Unauthenticated') {
                $location.path("/signin");
            }
            if (rejection === 'Authenticated') {
                $location.path("/");
            }
        });
    }]);
})();
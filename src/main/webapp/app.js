var app = angular.module('app', ["ngRoute"]);
app.config(['$httpProvider', function($httpProvider) {
    $httpProvider.defaults.headers.common['Content-Type'] = 'application/json';
}]);
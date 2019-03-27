angular
    .module('app')
    .directive('home', home);

function home() {
    var directive = {
        templateUrl: '/components/home/home.html',
        restrict: 'EA',
        controller: 'HomeCtrl',
        controllerAs: 'homeCtrl',
        bindToController: true
    };
    return directive;
}
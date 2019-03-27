angular
    .module('app')
    .directive('navbar', navbar);

function navbar() {
    var directive = {
        templateUrl: '/shared/navbar/navbar.html',
        restrict: 'EA',
        controller: 'NavbarCtrl',
        controllerAs: 'navbarCtrl',
        bindToController: true
    };
    return directive;
}
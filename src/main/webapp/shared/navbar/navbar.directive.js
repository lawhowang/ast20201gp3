angular
    .module('app')
    .directive('navbar', navbar);

function navbar() {
    var directive = {
        templateUrl: '/shared/navbar/navbar.html',
        restrict: 'EA',
        scope: {
            isAdmin: '@',
            adminPortal: '@',
            siteTitle: '@'
        },
        controller: 'NavbarCtrl',
        controllerAs: 'navbarCtrl',
        bindToController: true
    };
    return directive;
}
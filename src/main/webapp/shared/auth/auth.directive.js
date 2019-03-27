angular
    .module('app')
    .directive('auth', auth);

function auth() {
    var directive = {
        templateUrl: '/shared/auth/auth.html',
        restrict: 'EA',
        controller: 'AuthCtrl',
        controllerAs: 'authCtrl',
        bindToController: true
    };
    return directive;
}
angular
    .module('app')
    .directive('signin', signin);

function signin() {
    var directive = {
        templateUrl: '/shared/auth/signin.html',
        restrict: 'EA',
        controller: 'SigninCtrl',
        controllerAs: 'signinCtrl',
        bindToController: true
    };
    return directive;
}
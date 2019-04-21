angular
    .module('app')
    .directive('signinModal', signinModal);

function signinModal() {
    var directive = {
        templateUrl: '/shared/auth/signin-modal.html',
        restrict: 'EA',
        controller: 'SigninCtrl',
        controllerAs: 'signinCtrl',
        bindToController: true
    };
    return directive;
}
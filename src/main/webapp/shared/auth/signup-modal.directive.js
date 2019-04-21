angular
    .module('app')
    .directive('signupModal', signupModal);

function signupModal() {
    var directive = {
        templateUrl: '/shared/auth/signup-modal.html',
        restrict: 'EA',
        controller: 'SignupCtrl',
        controllerAs: 'signupCtrl',
        bindToController: true
    };
    return directive;
}
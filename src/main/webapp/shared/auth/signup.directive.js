angular
    .module('app')
    .directive('signup', signup);

function signup() {
    var directive = {
        templateUrl: '/shared/auth/signup.html',
        restrict: 'EA',
        controller: 'SignupCtrl',
        controllerAs: 'signupCtrl',
        bindToController: true
    };
    return directive;
}
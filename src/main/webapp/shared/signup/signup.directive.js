angular
    .module('app')
    .directive('signup', signup);

function signup() {
    var directive = {
        templateUrl: '/shared/signup/signup.html',
        restrict: 'EA',
        controller: 'SignupCtrl',
        controllerAs: 'signupCtrl',
        bindToController: true
    };
    return directive;
}
angular
    .module('app')
    .directive('profile', profile);

function profile() {
    var directive = {
        templateUrl: '/components/profile/profile.html',
        restrict: 'EA',
        controller: 'ProfileCtrl',
        controllerAs: 'profileCtrl',
        bindToController: true
    };
    return directive;
}
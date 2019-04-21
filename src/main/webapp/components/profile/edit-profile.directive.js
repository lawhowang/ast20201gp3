angular
    .module('app')
    .directive('editProfile', editProfile);

function editProfile() {
    var directive = {
        templateUrl: '/components/profile/edit-profile.html',
        restrict: 'EA',
        controller: 'EditProfileCtrl',
        controllerAs: 'editProfileCtrl',
        bindToController: true
    };
    return directive;
}
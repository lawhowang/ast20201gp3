angular
    .module('app')
    .directive('editUser', editUser);

function editUser() {
    var directive = {
        replace: true,
        templateUrl: '/admin/components/users/edit-user.html',
        restrict: 'EA',
        scope: {
            id: '='
        },
        controller: 'EditUserCtrl',
        controllerAs: 'editUserCtrl',
        bindToController: true
    };
    return directive;
}
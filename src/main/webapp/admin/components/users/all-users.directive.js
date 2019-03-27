angular
    .module('app')
    .directive('allUsers', allUsers);

function allUsers() {
    var directive = {
        replace: true,
        templateUrl: '/admin/components/users/all-users.html',
        restrict: 'EA',
        scope: {
            page: '='
        },
        controller: 'AllUsersCtrl',
        controllerAs: 'allUsersCtrl',
        bindToController: true
    };
    return directive;
}
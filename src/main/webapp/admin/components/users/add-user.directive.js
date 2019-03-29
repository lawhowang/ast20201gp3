angular
    .module('app')
    .directive('addUser', addUser);

function addUser() {
    var directive = {
        replace: true,
        templateUrl: '/admin/components/users/add-user.html',
        restrict: 'EA',
        scope: {
            page: '='
        },
        controller: 'AddUserCtrl',
        controllerAs: 'addUserCtrl',
        bindToController: true
    };
    return directive;
}
angular
    .module('app')
    .directive('sidebar', sidebar);

function sidebar() {
    var directive = {
        replace: true,
        templateUrl: '/admin/components/sidebar/sidebar.html',
        restrict: 'EA',
        controller: 'SidebarCtrl',
        controllerAs: 'sidebarCtrl',
        bindToController: true
    };
    return directive;
}
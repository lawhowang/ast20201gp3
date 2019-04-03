angular
    .module('app')
    .directive('dashboard', dashboard);

function dashboard() {
    var directive = {
        replace: true,
        templateUrl: '/admin/components/dashboard/dashboard.html',
        restrict: 'EA',
        scope: {
            currPage: '=',
            maxPages: '=',
            hrefPrefix: '@',
        },
        controller: 'DashboardCtrl',
        controllerAs: 'dashboardCtrl',
        bindToController: true
    };
    return directive;
}
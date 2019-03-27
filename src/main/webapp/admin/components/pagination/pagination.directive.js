angular
    .module('app')
    .directive('pagination', pagination);

function pagination() {
    var directive = {
        replace: true,
        templateUrl: '/admin/components/pagination/pagination.html',
        restrict: 'EA',
        scope: {
            currPage: '=',
            maxPages: '=',
            hrefPrefix: '@',
        },
        controller: 'PaginationCtrl',
        controllerAs: 'paginationCtrl',
        bindToController: true
    };
    return directive;
}
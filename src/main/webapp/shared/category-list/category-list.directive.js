angular
    .module('app')
    .directive('categoryList', categoryList);

function categoryList() {
    var directive = {
        templateUrl: '/shared/category-list/category-list.html',
        restrict: 'EA',
        controller: 'CategoryListCtrl',
        controllerAs: 'categoryListCtrl',
        bindToController: true
    };
    return directive;
}
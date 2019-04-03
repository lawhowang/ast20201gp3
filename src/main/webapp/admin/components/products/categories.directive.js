angular
    .module('app')
    .directive('categories', categories);

function categories() {
    var directive = {
        replace: true,
        templateUrl: '/admin/components/products/categories.html',
        restrict: 'EA',
        scope: {
            page: '='
        },
        controller: 'CategoriesCtrl',
        controllerAs: 'categoriesCtrl',
        bindToController: true
    };
    return directive;
}
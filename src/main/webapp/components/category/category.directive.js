angular
    .module('app')
    .directive('category', category);

function category() {
    var directive = {
        templateUrl: '/components/category/category.html',
        restrict: 'EA',
        controller: 'CategoryCtrl',
        controllerAs: 'categoryCtrl',
        bindToController: true
    };
    return directive;
}
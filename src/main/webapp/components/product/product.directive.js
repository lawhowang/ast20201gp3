angular
    .module('app')
    .directive('product', product);

function product() {
    var directive = {
        templateUrl: '/components/product/product.html',
        restrict: 'EA',
        controller: 'ProductCtrl',
        controllerAs: 'productCtrl',
        bindToController: true
    };
    return directive;
}
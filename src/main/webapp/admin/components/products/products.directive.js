angular
    .module('app')
    .directive('products', products);

function products() {
    var directive = {
        replace: true,
        templateUrl: '/admin/components/products/products.html',
        restrict: 'EA',
        scope: {
            page: '='
        },
        controller: 'ProductsCtrl',
        controllerAs: 'productsCtrl',
        bindToController: true
    };
    return directive;
}
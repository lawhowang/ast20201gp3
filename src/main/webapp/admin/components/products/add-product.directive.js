angular
    .module('app')
    .directive('addProduct', addProduct);

function addProduct() {
    var directive = {
        replace: true,
        templateUrl: '/admin/components/products/add-product.html',
        restrict: 'EA',
        scope: {
            page: '='
        },
        controller: 'AddProductCtrl',
        controllerAs: 'addProductCtrl',
        bindToController: true
    };
    return directive;
}
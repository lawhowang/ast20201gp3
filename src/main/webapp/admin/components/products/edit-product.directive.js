angular
    .module('app')
    .directive('editProduct', editProduct);

function editProduct() {
    var directive = {
        replace: true,
        templateUrl: '/admin/components/products/edit-product.html',
        restrict: 'EA',
        scope: {
            page: '='
        },
        controller: 'EditProductCtrl',
        controllerAs: 'editProductCtrl',
        bindToController: true
    };
    return directive;
}
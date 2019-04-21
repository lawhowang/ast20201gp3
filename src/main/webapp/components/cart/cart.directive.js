angular
    .module('app')
    .directive('cart', cart);

function cart() {
    var directive = {
        replace: true,
        templateUrl: '/components/cart/cart.html',
        restrict: 'EA',
        scope: {
            page: '='
        },
        controller: 'CartCtrl',
        controllerAs: 'cartCtrl',
        bindToController: true
    };
    return directive;
}
angular
    .module('app')
    .directive('order', order);

function order() {
    var directive = {
        templateUrl: '/components/order/order.html',
        restrict: 'EA',
        controller: 'OrderCtrl',
        controllerAs: 'orderCtrl',
        bindToController: true
    };
    return directive;
}
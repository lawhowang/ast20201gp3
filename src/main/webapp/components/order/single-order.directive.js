angular
    .module('app')
    .directive('singleOrder', singleOrder);

function singleOrder() {
    var directive = {
        templateUrl: '/components/order/single-order.html',
        restrict: 'EA',
        controller: 'SingleOrderCtrl',
        controllerAs: 'singleOrderCtrl',
        bindToController: true
    };
    return directive;
}
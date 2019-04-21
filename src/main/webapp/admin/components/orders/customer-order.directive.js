angular
    .module('app')
    .directive('customerOrder', customerOrder);

function customerOrder() {
    var directive = {
        replace: true,
        templateUrl: '/admin/components/orders/customer-order.html',
        restrict: 'EA',
        scope: {
            page: '='
        },
        controller: 'CustomerOrderCtrl',
        controllerAs: 'customerOrderCtrl',
        bindToController: true
    };
    return directive;
}
angular
    .module('app')
    .directive('customerOrders', customerOrders);

function customerOrders() {
    var directive = {
        replace: true,
        templateUrl: '/admin/components/orders/customer-orders.html',
        restrict: 'EA',
        scope: {
            page: '='
        },
        controller: 'CustomerOrdersCtrl',
        controllerAs: 'customerOrdersCtrl',
        bindToController: true
    };
    return directive;
}
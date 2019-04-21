angular
    .module('app')
    .directive('credit', credit);

function credit() {
    var directive = {
        templateUrl: '/components/credit/credit.html',
        restrict: 'EA',
        controller: 'CreditCtrl',
        controllerAs: 'creditCtrl',
        bindToController: true
    };
    return directive;
}
angular
    .module('app')
    .directive('search', search);

function search() {
    var directive = {
        templateUrl: '/components/search/search.html',
        restrict: 'EA',
        controller: 'SearchCtrl',
        controllerAs: 'searchCtrl',
        bindToController: true
    };
    return directive;
}
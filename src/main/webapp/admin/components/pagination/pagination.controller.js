(function () {
    'use strict';

    angular
        .module('app')
        .controller('PaginationCtrl', PaginationCtrl);

    function PaginationCtrl() {
        var vm = this;
        vm.$onInit = onInit;

        function onInit() {

            vm.lowerBound = vm.currPage - 5;
            vm.upperBound = vm.currPage + 5;
            vm.currPage = vm.currPage;

            if (vm.lowerBound < 1) vm.lowerBound = 1;
            if (vm.upperBound > vm.maxPages) vm.upperBound = vm.maxPages;

            if (vm.currPage - vm.lowerBound < 5) {
                vm.upperBound = vm.upperBound + 5 > vm.maxPages ? vm.maxPages : vm.upperBound + 5;
            }

            if (vm.upperBound - vm.currPage < 5) {
                vm.lowerBound = vm.lowerBound - 5 < 1 ? 1 : vm.lowerBound - 5;
            }

            vm.prependPages = [];
            vm.appendPages = [];

            for (var i =vm.lowerBound; i < vm.currPage; i++) {
                vm.prependPages.push(i);
            }

            for (var i = vm.currPage + 1; i <= vm.upperBound; i++) {
                vm.appendPages.push(i);
            }
        }
    }
})();
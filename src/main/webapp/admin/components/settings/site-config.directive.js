angular
    .module('app')
    .directive('siteConfig', siteConfig);

function siteConfig() {
    var directive = {
        replace: true,
        templateUrl: '/admin/components/settings/site-config.html',
        restrict: 'EA',
        controller: 'SiteConfigCtrl',
        controllerAs: 'siteConfigCtrl',
        bindToController: true
    };
    return directive;
}
/*
https://stackoverflow.com/questions/17063000/ng-model-for-input-type-file-with-directive-demo
*/
angular.module("app").directive("upload", function () {
    return {
        require: "ngModel",
        link: function postLink(scope, elem, attrs, ngModel) {
            elem.on("change", function (e) {
                var files = elem[0].files;
                ngModel.$setViewValue(files);
            });
        }
    }
});
(function () {
    'use strict';

    angular
        .module('app')
        .factory('productService', productService);

    productService.$inject = ['$http'];

    function productService($http) {
        var service = {
            getProducts: getProducts,
            getProduct: getProduct,
            updateProduct: updateProduct,
            deleteProduct: deleteProduct,
            addProduct: addProduct
        };

        return service;

        function getProducts(page, filter, sortBy) {
            var data = {};
            if (filter) {
                data = {
                    page: page,
                    name: filter ? filter.name : null,
                    category: filter.category,
                    priceUpperBound: filter.priceUpperBound,
                    priceLowerBound: filter.priceLowerBound,
                    sortBy: sortBy
                }
            } else {
                data = {
                    page: page,
                    sortBy: sortBy
                };
            }
            return $http.get("/admin/api/products/products", {
                params: data
            });
        }

        function getProduct(id) {
            return $http.get(`/admin/api/products/products/${id}`);
        }

        function updateProduct(id, product, productImage) {
            var formData = new FormData();

            formData.append('productImage', productImage);
            formData.append('product', new Blob([JSON.stringify(product)], { type: "application/json" }));

            return $http({
                url: `/admin/api/products/products/${id}`,
                headers: {
                    "Content-Type": undefined
                },
                data: formData,
                method: "PUT"
            });
            //return $http.put(`/admin/api/products/products/${id}`, product);
        }

        function deleteProduct(id) {
            return $http.delete(`/admin/api/products/products/${id}`);
        }

        function addProduct(product, productImage) {
            var formData = new FormData();

            formData.append('productImage', productImage);
            formData.append('product', new Blob([JSON.stringify(product)], { type: "application/json" }));

            return $http({
                url: `/admin/api/products/products`,
                headers: {
                    "Content-Type": undefined
                },
                data: formData,
                method: "POST"
            });
            //return $http.post(`/admin/api/products/products`, params);
        }
    }
})();
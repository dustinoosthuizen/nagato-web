'use strict';

nagatoApp.controller('DatasourceController', function ($scope, resolvedDatasource, Datasource) {

        $scope.datasources = resolvedDatasource;

        $scope.create = function () {
            console.log("create datasource");
            Datasource.create($scope.datasource,
                function () {
                    console.log("finished datasource");
                    $scope.datasources = Datasource.findAll();
                    $('#saveDatasourceModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.datasource = Datasource.findById({id: id});
            $('#saveDatasourceModal').modal('show');
        };

        $scope.delete = function (id) {
            Datasource.deleteById({id: id},
                function () {
                    $scope.datasources = Datasource.findAll();
                });
        };

        $scope.clear = function () {
            $scope.datasource = {id: null,  name: null,classname:null,url:null,username:null,password:null};
        };
    });

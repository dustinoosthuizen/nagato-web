'use strict';

nagatoApp.controller('ComparisonDefinitionDatabaseController', function ($scope, resolvedComparisonDefinitionDatabase, ComparisonDefinitionDatabase) {

        $scope.comparisondefinitiondatabases = resolvedComparisonDefinitionDatabase;

        $scope.create = function () {
            ComparisonDefinitionDatabase.save($scope.comparisondefinitiondatabase,
                function () {
                    $scope.comparisondefinitiondatabases = ComparisonDefinitionDatabase.query();
                    $('#saveComparisonDefinitionDatabaseModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.comparisondefinitiondatabase = ComparisonDefinitionDatabase.get({id: id});
            $('#saveComparisonDefinitionDatabaseModal').modal('show');
        };

        $scope.delete = function (id) {
            ComparisonDefinitionDatabase.delete({id: id},
                function () {
                    $scope.comparisondefinitiondatabases = ComparisonDefinitionDatabase.query();
                });
        };

        $scope.clear = function () {
            $scope.comparisondefinitiondatabase = {id: null, sampleTextAttribute: null, sampleDateAttribute: null};
        };
    });

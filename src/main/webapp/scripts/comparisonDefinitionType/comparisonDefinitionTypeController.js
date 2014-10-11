'use strict';

nagatoApp.controller('ComparisonDefinitionTypeController', function ($scope, resolvedComparisonDefinitionType, ComparisonDefinitionType) {

        $scope.comparisondefinitiontypes = resolvedComparisonDefinitionType;

        $scope.create = function () {
            ComparisonDefinitionType.save($scope.comparisondefinitiontype,
                function () {
                    $scope.comparisondefinitiontypes = ComparisonDefinitionType.query();
                    $('#saveComparisonDefinitionTypeModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.comparisondefinitiontype = ComparisonDefinitionType.get({id: id});
            $('#saveComparisonDefinitionTypeModal').modal('show');
        };

        $scope.delete = function (id) {
            ComparisonDefinitionType.delete({id: id},
                function () {
                    $scope.comparisondefinitiontypes = ComparisonDefinitionType.query();
                });
        };

        $scope.clear = function () {
            $scope.comparisondefinitiontype = {id: null, name: null};
        };
    });

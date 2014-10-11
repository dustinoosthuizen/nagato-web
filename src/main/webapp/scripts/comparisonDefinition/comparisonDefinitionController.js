'use strict';

nagatoApp.controller('ComparisonDefinitionController', function ($scope, resolvedComparisonDefinition, ComparisonDefinition, resolvedComparisonDefinitionTypeLookups,resolvedDatasourceLookups,ComparisonDefinitionType,$location,ngDialog, ComparisonDefinitionDatabase) {


    $scope.comparisondefinitions = resolvedComparisonDefinition;
    $scope.comparisonDefinitionDatabase ={};
    $scope.selectedSourceDatasource = {};
    $scope.selectedTargetDatasource = {};
    $scope.edit = false;
    $scope.sourceCompiled = false;
    $scope.targetCompiled = false;
    $scope.sourceCompiledAttributes = {};
    $scope.targetCompiledAttributes = {};

    for (var i in $scope.comparisondefinitions) {
        console.log($scope.comparisondefinitions[i].name);
    }
    $scope.comparisonDefinitionTypes = resolvedComparisonDefinitionTypeLookups;
    $scope.datasources = resolvedDatasourceLookups;
    console.log("saving comparison definition1:"+JSON.stringify($scope.datasources));
    $scope.save = function () {
        console.log("saving comparison definition1:"+JSON.stringify($scope.comparisondefinition));
        var comparisonDefinitionName = $scope.comparisondefinition.name;
            $scope.doesNameExist = ComparisonDefinition.doesNameExist({id:comparisonDefinitionName});
            $scope.doesNameExist.$promise.then(function(data) {
                console.log("Doesn Name Exist"+JSON.stringify(data));
                if($scope.edit)
                {
                    ComparisonDefinition.edit($scope.comparisondefinition,
                        function () {
                            console.log("editing comparison definition:" + JSON.stringify($scope.comparisondefinition));
                            $scope.comparisondefinitions = ComparisonDefinition.findAll();
                            $('#saveComparisonDefinitionModal').modal('hide');
//                            $scope.clear();
                            console.log("editing  comparisonDefinitionDatabase"+JSON.stringify($scope.comparisonDefinitionDatabase));
                            $scope.comparisonDefinitionDatabase.sourceDatasource = $scope.selectedSourceDatasource;
                            $scope.comparisonDefinitionDatabase.targetDatasource = $scope.selectedTargetDatasource;
                            ComparisonDefinitionDatabase.edit($scope.comparisonDefinitionDatabase,
                                function () {

                                    console.log("editing comparison definition database:" + JSON.stringify($scope.comparisonDefinitionDatabase));
                                    $('#saveComparisonDefinitionModal').modal('hide');
                                    $scope.clear();

                                });
                        })
                }else
                {
                    if(!data.value)
                    {
                        ComparisonDefinition.create($scope.comparisondefinition,
                            function () {
                                console.log("create comparison definition:" + JSON.stringify($scope.comparisondefinition));
                                $scope.comparisondefinitions = ComparisonDefinition.findAll();
                                $('#saveComparisonDefinitionModal').modal('hide');
//                                $scope.clear();


                                ComparisonDefinition.findByName({id: comparisonDefinitionName},
                                    function(data){
                                        console.log("created comparison definition:" + JSON.stringify(data));
                                        $scope.comparisonDefinitionDatabase.id = null;
                                        $scope.comparisonDefinitionDatabase.comparisonDefinition = {id:data.id,name:data.name,type:{id:data.type.id,name:data.type.name}};
                                        $scope.comparisonDefinitionDatabase.sourceDatasource = $scope.selectedSourceDatasource;
                                        $scope.comparisonDefinitionDatabase.targetDatasource = $scope.selectedTargetDatasource;
                                        console.log("create comparison definition database:" + JSON.stringify($scope.comparisonDefinitionDatabase));
                                        ComparisonDefinitionDatabase.create($scope.comparisonDefinitionDatabase,
                                            function () {

                                                console.log("saving comparison definition database:" + JSON.stringify($scope.comparisonDefinitionDatabase));
                                                $('#saveComparisonDefinitionModal').modal('hide');
                                                $scope.clear();
                                            });

                                    });
                            })
                    }else{
                        console.log("Name Already Exists, please show error");
                    }}
            });
    };

    $scope.update = function (id) {
        $scope.edit = true;
        $scope.comparisondefinition = ComparisonDefinition.findById({id: id});
        $scope.comparisonDefinitionDatabase = ComparisonDefinitionDatabase.findByComparisonDefinitionId({id: id});
        $scope.comparisonDefinitionDatabase.$promise.then(function(data) {
            console.log("update "+JSON.stringify(data));
            console.log("update "+JSON.stringify($scope.comparisonDefinitionDatabase.sourceDatasource));
            console.log("update "+JSON.stringify($scope.comparisonDefinitionDatabase.targetDatasource));
            $scope.selectedSourceDatasource = $scope.comparisonDefinitionDatabase.sourceDatasource;
            $scope.selectedTargetDatasource = $scope.comparisonDefinitionDatabase.targetDatasource;
            if($scope.comparisonDefinitionDatabase.sourceKeytomatch != null&&$scope.comparisonDefinitionDatabase.sourceAttributetocompare != null);
            {
                $scope.sourceCompiled = true;

            }
            if($scope.comparisonDefinitionDatabase.targetKeytomatch != null&&$scope.comparisonDefinitionDatabase.targetAttributetocompare != null);
            {
                $scope.targetCompiled = true;

            }
        });

        $('#saveComparisonDefinitionModal').modal('show');
    };

    $scope.delete = function (id) {
        ComparisonDefinition.deleteById({id: id},
            function () {
                $scope.comparisondefinitions = ComparisonDefinition.findAll();
            });
    };

    $scope.clear = function () {
        $scope.edit = false;
        $scope.comparisondefinition = {id: null, name: null, type: null};
        $scope.comparisonDefinitionDatabase = {id:null,sourceQuery:null,targetQuery:null,comparisonDefinition:null};
        $scope.selectedSourceDatasource = {};
        $scope.selectedTargetDatasource = {};
        $scope.sourceCompiled = false;
        $scope.targetCompiled = false;
        $scope.sourceCompiledAttributes={};
        $scope.targetCompiledAttributes={};
    };

    $scope.loadConfigType = function(selectedType)
    {
        console.log("selected type:"+selectedType.name);

    }

    $scope.loadSourceDatasource = function(selectedType){
        console.log("updated "+JSON.stringify($scope.selectedSourceDatasource));
    }

    $scope.loadTargetDatasource = function(selectedType){
        console.log("updated "+JSON.stringify($scope.selectedTargetDatasource));
    }

    $scope.compileSourceQuery = function()
    {
        $scope.compileDatabaseQueryRequest = {query:$scope.comparisonDefinitionDatabase.sourceQuery,datasource:$scope.selectedSourceDatasource};
        console.log("compileDatabaseQueryRequest "+JSON.stringify($scope.compileDatabaseQueryRequest));
        $scope.compileDatabaseQueryResponse = ComparisonDefinitionDatabase.compileDatabaseQuery($scope.compileDatabaseQueryRequest);
        $scope.compileDatabaseQueryResponse.$promise.then(function(data) {
            console.log("compileDatabaseQueryResponse "+JSON.stringify(data));
            $scope.sourceCompiledAttributes = data;
        });
        $scope.sourceCompiled = true;
    }

    $scope.compileTargetQuery = function()
    {
        $scope.compileDatabaseQueryRequest = {query:$scope.comparisonDefinitionDatabase.targetQuery,datasource:$scope.selectedTargetDatasource};
        console.log("compileDatabaseQueryRequest "+JSON.stringify($scope.compileDatabaseQueryRequest));
        $scope.compileDatabaseQueryResponse = ComparisonDefinitionDatabase.compileDatabaseQuery($scope.compileDatabaseQueryRequest);
        $scope.compileDatabaseQueryResponse.$promise.then(function(data) {
            console.log("compileDatabaseQueryResponse "+JSON.stringify(data));
            $scope.targetCompiledAttributes = data;
        });
        $scope.targetCompiled = true;
    }

});

'use strict';

nagatoApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/comparisonDefinition', {
                    templateUrl: 'views/comparisonDefinitions.html',
                    controller: 'ComparisonDefinitionController',
                    resolve:{
                        resolvedComparisonDefinition: ['ComparisonDefinition', function (ComparisonDefinition) {
                            return ComparisonDefinition.findAll();
                        }],
                        resolvedComparisonDefinitionTypeLookups: ['ComparisonDefinitionType', function (ComparisonDefinitionType) {
                            return ComparisonDefinitionType.query();
                        }],
                        resolvedDatasourceLookups: ['Datasource', function (Datasource) {
                            return Datasource.findAll();
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        });


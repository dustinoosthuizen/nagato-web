'use strict';

nagatoApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/comparisondefinitiondatabase', {
                    templateUrl: 'views/comparisonDefinitionDatabases.html',
                    controller: 'ComparisonDefinitionDatabaseController',
                    resolve:{
                        resolvedComparisonDefinitionDatabase: ['ComparisonDefinitionDatabase', function (ComparisonDefinitionDatabase) {
                            return ComparisonDefinitionDatabase.query();
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        });

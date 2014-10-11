'use strict';

nagatoApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/comparisonDefinitionType', {
                    templateUrl: 'views/comparisonDefinitionTypes.html',
                    controller: 'ComparisonDefinitionTypeController',
                    resolve:{
                        resolvedComparisonDefinitionType: ['ComparisonDefinitionType', function (ComparisonDefinitionType) {
                            return ComparisonDefinitionType.query();
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        });

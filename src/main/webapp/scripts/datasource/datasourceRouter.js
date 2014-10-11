'use strict';

nagatoApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/datasource', {
                    templateUrl: 'views/datasources.html',
                    controller: 'DatasourceController',
                    resolve:{
                        resolvedDatasource: ['Datasource', function (Datasource) {
                            return Datasource.findAll();
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        });

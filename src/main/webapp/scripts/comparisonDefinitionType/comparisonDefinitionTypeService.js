'use strict';

nagatoApp.factory('ComparisonDefinitionType', function ($resource) {
        return $resource('app/rest/comparisondefinitiontypes/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    });

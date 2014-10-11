package com.webward.web.rest.dto;

/**
 * Created by dustinosthzn on 2014/09/28.
 */
public class CompileDatabaseQueryRequestDTO {

    private String query;
    private DatasourceDTO datasource;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public DatasourceDTO getDatasource() {
        return datasource;
    }

    public void setDatasource(DatasourceDTO datasource) {
        this.datasource = datasource;
    }
}

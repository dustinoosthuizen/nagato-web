package com.webward.service;

import com.webward.domain.ComparisonDatasource;
import com.webward.repository.ComparisonDefinitionRepository;
import org.hibernate.internal.SessionImpl;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dustinosthzn on 2014/09/28.
 */
@Service
public class DatabaseQueryService {

    @Inject
    private ComparisonDefinitionRepository comparisondefinitionRepository;

    Map<String,EntityManager> entityManagerMap = new HashMap();
//    public void recon(String name, Date businessDate) throws Exception {
//        ComparisonDefinition comparisonDefiniDefintion = comparisondefinitionRepository.findByName(name);
//
//        List sourceData = getSourceData(reconDefinition);
//        List targetData = getTargetData(reconDefinition);
//
//
//    }
//
//    public List getSourceData(ReconDefinition reconDefinition) {
//        EntityManager entityManager = getEntityManager(reconDefinition.getSourceDatasource());
//        Query query = entityManager.createNativeQuery(reconDefinition.getSourceQuery());
//        List list = query.getResultList();
//        return list;
//    }
//
//    public List getTargetData(ReconDefinition reconDefinition) {
//        EntityManager entityManager = getEntityManager(reconDefinition.getTargetDatasource());
//        Query query = entityManager.createNativeQuery(reconDefinition.getTargetQuery());
//        List list = query.getResultList();
//        return list;
//    }

    public EntityManager getEntityManager(ComparisonDatasource comparisonDatasource) {
        if (entityManagerMap.get(comparisonDatasource.getName()) == null) {
            Map<String, Object> configOverrides = new HashMap<String, Object>();
            configOverrides.put("hibernate.connection.url", comparisonDatasource.getUrl());
            configOverrides.put("hibernate.connection.username", comparisonDatasource.getUsername());
            configOverrides.put("hibernate.connection.password", comparisonDatasource.getPassword());
            configOverrides.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            configOverrides.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory");
            configOverrides.put("hibernate.connection.driver_class", comparisonDatasource.getClassname());
//            configOverrides.put("hibernate.hbm2ddl.auto", "none");
            EntityManagerFactory programmaticEmf
                    = Persistence.createEntityManagerFactory("override", configOverrides);
            entityManagerMap.put(comparisonDatasource.getName(), programmaticEmf.createEntityManager());
        }

        return entityManagerMap.get(comparisonDatasource.getName());
    }

    public List<String> getColumnNames(String query, ComparisonDatasource comparisonDatasource) {
        List<String> columnNames = new ArrayList();
        try {
            Connection connection = getEntityManager(comparisonDatasource).unwrap(SessionImpl.class).connection();
//            Connection connection = getEntityManager(reconDataSource).unwrap(Connection.class);
            Statement statement = connection.createStatement();
            statement.setMaxRows(1);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int columnCount= resultSet.getMetaData().getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    columnNames.add(resultSet.getMetaData().getColumnName(i+1));
                }
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return columnNames;
    }
}


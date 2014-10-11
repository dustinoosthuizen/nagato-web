package com.webward.service;

import com.webward.Application;
import com.webward.domain.ComparisonDatasource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Test class for the UserResource REST controller.
 *
 * @see com.webward.service.UserService
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("dev")
@Transactional
public class DatabaseQueryServiceTest {

    @Inject
    private DatabaseQueryService databaseQueryService;

    @Test
    public void testDatabaseQueryService() {
        ComparisonDatasource comparisonDatasource = new ComparisonDatasource();
        comparisonDatasource.setClassname("com.mysql.jdbc.Driver");
        comparisonDatasource.setUrl("jdbc:mysql://localhost:3306/RECON_TEST_DB1?zeroDateTimeBehavior=convertToNull");
        comparisonDatasource.setUsername("root");
        comparisonDatasource.setPassword("password");
        List<String> columnNames = databaseQueryService.getColumnNames("SELECT * FROM recon_test_db1.RECON_TEST_TABLE1", comparisonDatasource);
//        for(String anAttribute:attributes)
//        {
//            System.out.println(anAttribute);
//
//        }
    }

}

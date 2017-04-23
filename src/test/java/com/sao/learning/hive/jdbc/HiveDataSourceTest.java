package com.sao.learning.hive.jdbc;

import com.sao.learning.authentication.KerberosAuth;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by saopr on 4/19/2017.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {KerberosAuth.class, HiveDataSource.class})
@ComponentScan
@EnableAutoConfiguration
public class HiveDataSourceTest {

    @Autowired
    HiveDataSource hiveDataSource;

    @Test
    public void getConnectionTest() throws SQLException {
        Connection connection = hiveDataSource.getConnection();
        assertThat(connection, is(not(nullValue())));
    }
}

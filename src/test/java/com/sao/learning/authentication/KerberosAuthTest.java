package com.sao.learning.authentication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by saopr on 4/14/2017.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {KerberosAuth.class})
@ComponentScan
@EnableAutoConfiguration
public class KerberosAuthTest {

    @Autowired
    KerberosAuth kerberosAuth;

    @Test
    public void authTest() {
        kerberosAuth.authenticate();
    }
}

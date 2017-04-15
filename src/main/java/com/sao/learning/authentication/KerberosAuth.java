package com.sao.learning.authentication;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by saopr on 4/14/2017.
 */

@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "kerberos")
public class KerberosAuth {

    Logger logger = LoggerFactory.getLogger(KerberosAuth.class);

    private String principal;
    private String keyTab;
    private String krb5Conf;
    private String realm;
    private String kdc;

    public void authenticate() {
        URL urlKeyTab = this.getClass().getClassLoader().getResource(keyTab);
        URL urlKrb5Conf = this.getClass().getClassLoader().getResource(krb5Conf);
        File fileKeyTab = new File(urlKeyTab.getPath());
        File fileKrb5Conf = new File(urlKrb5Conf.getPath());

        System.setProperty("java.security.krb5.conf", fileKrb5Conf.getAbsolutePath());
        System.setProperty("java.security.krb5.realm", realm);
        System.setProperty("java.security.krb5.kdc",kdc);
        System.setProperty("sun.security.krb5.debug", "true");

        Configuration configuration = new Configuration();
        configuration.set("hadoop.security.authentication", "kerberos");
        configuration.set("hadoop.security.authorization", "true");

        UserGroupInformation.setConfiguration(configuration);

        try {
            UserGroupInformation.loginUserFromKeytab(principal, fileKeyTab.getAbsolutePath());
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getKeyTab() {
        return keyTab;
    }

    public void setKeyTab(String keyTab) {
        this.keyTab = keyTab;
    }

    public String getKrb5Conf() {
        return krb5Conf;
    }

    public void setKrb5Conf(String krb5Conf) {
        this.krb5Conf = krb5Conf;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getKdc() {
        return kdc;
    }

    public void setKdc(String kdc) {
        this.kdc = kdc;
    }
}

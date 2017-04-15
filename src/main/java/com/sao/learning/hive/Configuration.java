package com.sao.learning.hive;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by saopr on 4/14/2017.
 */

@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "app")
public class Configuration {
}

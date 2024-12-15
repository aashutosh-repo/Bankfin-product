package com.fin.bancs.bankconfig;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
public class InternalAccountConfiguration {

    private static final Logger logger = LogManager.getLogger(InternalAccountConfiguration.class);
    private static final Properties properties = new Properties();

    //private InternalAccountConfiguration(){}

    static {
        try (InputStream input = InternalAccountConfiguration.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                logger.info("Sorry, unable to find config.properties");
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            logger.info("There is some issue in properties file");
        }
    }

    public static int getDefaultCreditAccountId() {
        return Integer.parseInt(properties.getProperty("default.credit.account.id"));
    }

    public static int getDefaultDebitAccountId() {
        return Integer.parseInt(properties.getProperty("default.debit.account.id"));
    }
}
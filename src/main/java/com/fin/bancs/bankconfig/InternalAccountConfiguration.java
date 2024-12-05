package com.fin.bancs.bankconfig;

import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
public class InternalAccountConfiguration {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = InternalAccountConfiguration.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                //return ;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static int getDefaultCreditAccountId() {
        return Integer.parseInt(properties.getProperty("default.credit.account.id"));
    }

    public static int getDefaultDebitAccountId() {
        return Integer.parseInt(properties.getProperty("default.debit.account.id"));
    }
}
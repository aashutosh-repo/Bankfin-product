package com.fin.bancs.bankconfig;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;

@Configuration
public class Log4j2Configuration {

    @PostConstruct
    public void configureLog4j2() {
        // Create a ConfigurationBuilder
        ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();

        // Set the root level
        builder.setStatusLevel(org.apache.logging.log4j.Level.WARN);
        builder.setConfigurationName("CustomConfig");

        // Define a Console Appender
        AppenderComponentBuilder consoleAppender = builder.newAppender("Console", "CONSOLE")
                .addAttribute("target", ConsoleAppender.Target.SYSTEM_OUT);
        consoleAppender.add(builder.newLayout("PatternLayout")
                .addAttribute("pattern", "%d{yyyy-MM-dd HH:mm:ss} [%t] %-5level %logger{36} - %msg%n"));
        builder.add(consoleAppender);

        // Define a File Appender
        AppenderComponentBuilder fileAppender = builder.newAppender("FileLogger", "File")
                .addAttribute("fileName", "logs/app.log");
        fileAppender.add(builder.newLayout("PatternLayout")
                .addAttribute("pattern", "%d{yyyy-MM-dd HH:mm:ss} [%t] %-5level %logger{36} - %msg%n"));
        builder.add(fileAppender);

        // Create a root logger
        builder.add(builder.newRootLogger(org.apache.logging.log4j.Level.INFO)
                .add(builder.newAppenderRef("Console"))
                .add(builder.newAppenderRef("FileLogger")));

        // Create a custom logger for a specific package
        builder.add(builder.newLogger("com.fin.bancs", org.apache.logging.log4j.Level.DEBUG)
                .add(builder.newAppenderRef("Console"))
                .add(builder.newAppenderRef("FileLogger"))
                .addAttribute("additivity", false));

        // Build the configuration
        BuiltConfiguration  configuration = builder.build();        

        // Apply the configuration
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        context.start(configuration);
    }
}

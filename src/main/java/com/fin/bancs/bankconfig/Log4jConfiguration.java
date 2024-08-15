@Configuration
public class Log4j2Configuration {
    @Bean
    public LoggerContext loggerContext() {
        return (LoggerContext) LogManager.getContext(false);
    }

    @Bean
    public Appender consoleAppender() {
        ConsoleAppender appender = new ConsoleAppender();
        appender.setName("Console");
        appender.setTarget("SYSTEM_OUT");
        appender.setLayout(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} [%t] %-5level %logger{36} - %msg%n"));
        return appender;
    }

    @Bean
    public Appender fileAppender() {
        FileAppender appender = new FileAppender();
        appender.setName("File");
        appender.setFileName("logs/app.log");
        appender.setLayout(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} [%t] %-5level %logger{36} - %msg%n"));
        return appender;
    }

    @Bean
    public Logger rootLogger() {
        Logger rootLogger = (Logger) LogManager.getRootLogger();
        rootLogger.setLevel(Level.INFO);
        rootLogger.addAppender(consoleAppender());
        rootLogger.addAppender(fileAppender());
        return rootLogger;
    }
}

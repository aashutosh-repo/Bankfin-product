

@Configuration
@EnableJpaAuditing
public class AuditingConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAware<String>() {
                         
    @Override
    public String getCurrentAuditor() {
       // Return the username of the current user
      // You can use Spring Security's SecurityContextHolder to get the current user
     return "admin"; // Replace with your own logic
    }
  };
 }

    @Bean
    public JpaAuditingConfigurer jpaAuditingConfigurer() {
        return new JpaAuditingConfigurer().setAuditorAware(auditorAware());
   }
}
                                                      
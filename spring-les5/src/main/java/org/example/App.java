package org.example;

import org.example.config.ProjectConfig;
import org.example.services.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class)) {
            ProductService productService = context.getBean(ProductService.class);
            productService.addProduct("chocolate");
        }
    }
}

/*
    отже щоб працювати з ТРАНЗАКЦІЯМИ потрібно:
    
    1) до pom.xml
         <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-tx</artifactId>
          <version>${spring.version}</version>
        </dependency>
        
     2) до якогось конфіг класу ( в нашому випадку це ProjectConfig) додати БІН 
       
       @Bean
        public PlatformTransactionManager transactionManager(DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }
        
     3)  також жо конфіг класу додати анотацію  @EnableTransactionManagement
     
            @EnableTransactionManagement
            @Configuration
            @ComponentScan(basePackages = {"org.example.repositories", "org.example.services"})
            public class ProjectConfig {
            ... 
            }
            
            
      4) зазвичай у потрібному сервіс класі додати до методу анотацію @Transactional, який буде тепер транзакційним 
      
      * все що є в межах цього PUBLIC методу має відбутися як одна цілісна операція:
         - або все успішно і всі ці кілька змін (insert / update / delete) до БД відбулися  
         - якщо десь впав RuntimeException, то транзакція відкочується і ніяких змін взагалі у БД не відбувається
            * - якщо це буде checked Exception , то rollback не відбудеться
        
      
            @Transactional
            public void addProduct(String name){
                productRepository.addProject(name);
                throw new RuntimeException(";(");
            } 
            
            
        5) через атрибути анотації @Transactional можна додатково визначити при яких ексепшенах буде/не буде відкат операції
        
            //    @Transactional(rollbackFor = SomeOurException.class) // відкоти операцію при SomeOurException
            //    @Transactional(noRollbackFor = RuntimeException.class) // не ревертати операцію при RuntimeException       
 */

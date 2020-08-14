package web.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
@ComponentScan(value = "web")
public class AppConfig {

//    @Autowired
//    private Environment env;           "${hibernate.dialect}"
    @Value("${db.driver}")
    private String dbdriver;

    @Value("${db.url}")
    String dburl;

    @Value("${db.username}")
    String dbusername;

    @Value("${db.password}")
    String dbpassword;

    @Value("${hibernate.show_sql}")
    String hibernateshowsql;

    @Value("${hibernate.dialect}")
    String hibernatedialect;

    @Value("${hibernate.show_sql}")
    String hibernateshowql;


    @Value("${hibernate.hbm2ddl.auto}")
    String hibernatehbm2ddlauto;

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dbdriver);
        dataSource.setUrl(dburl);
        dataSource.setUsername(dbusername);
        dataSource.setPassword(dbpassword);
        return dataSource;
    }

    @Bean
    public Properties getPropertyHibernate(){
        Properties props = new Properties();
        props.put("hibernate.show_sql", hibernateshowql);
        props.put("hibernate.hbm2ddl.auto", hibernatehbm2ddlauto);
        props.put("hibernate.dialect", hibernatedialect);
        return props;
    }

    @Bean
    public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {

   // информирует jpa что исп-ся Hibernate
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        return adapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(getDataSource());
        entityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter());
        entityManagerFactory.setJpaProperties(getPropertyHibernate());
        entityManagerFactory.setPackagesToScan("web.model");
        //entityManagerFactory.setJpaProperties();

        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {//что транзакции работали
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

//    @Bean
//    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
//        return new PersistenceExceptionTranslationPostProcessor();
//    }


}

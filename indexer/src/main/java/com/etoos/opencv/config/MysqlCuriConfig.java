//package com.etoos.opencv.config;
//
//import com.curi.log.mapper.annotations.MysqlCuriConnMapper;
//import com.zaxxer.hikari.HikariDataSource;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
//@Configuration
//@MapperScan(value = "com.curi.log", annotationClass = MysqlCuriConnMapper.class, sqlSessionFactoryRef = "mysqlCuriSqlSessionFactory")
//@EnableTransactionManagement
//public class MysqlCuriConfig {
//
//    @Bean(name = "mysqlCuriDataSource", destroyMethod = "close")
////    @Primary
//    @ConfigurationProperties(prefix = "spring.curi.datasource")
//    public DataSource mysqlDataSource() {
//        return DataSourceBuilder.create().type(HikariDataSource.class).build();
//    }
//
//    @Bean(name = "mysqlCuriSqlSessionFactory")
////    @Primary
//    public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("mysqlCuriDataSource") DataSource mysqlDataSource, ApplicationContext applicationContext) throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(mysqlDataSource);
//        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/mapper/**/*.xml"));
//        return sqlSessionFactoryBean.getObject();
//    }
//
//    @Bean(name = "mysqlCuriSqlSessionTemplate")
////    @Primary
//    public SqlSessionTemplate mysqlSqlSessionTemplate(SqlSessionFactory mysqlCuriSqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(mysqlCuriSqlSessionFactory);
//    }
//}

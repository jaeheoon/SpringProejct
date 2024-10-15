package spring.conf;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.file.bean.UploadDTO;
import com.user.bean.UserDTO;
import com.user.bean.UserPaging;

/*
 * 스프링 applicationContext 역할을 하는 클래스
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:spring/db.properties")
@MapperScan("com.user.dao com.file.dao")
public class SpringConfiguration {
	private @Value("${jdbc.driver}") String driver;
	private @Value("${jdbc.url}") String url;
	private @Value("${jdbc.username}") String username;
	private @Value("${jdbc.password}") String password;
	
	@Autowired
	private ApplicationContext context;
	
	@Bean
	public List<UserDTO> arrayList() {
		return new ArrayList<UserDTO>();
	}
	
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(driver);
		basicDataSource.setUrl(url);
		basicDataSource.setUsername(username);
		basicDataSource.setPassword(password);
		return basicDataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("spring/mybatis-config.xml"));
		/*
		sqlSessionFactoryBean.setMapperLocations(
			new ClassPathResource[] {
	            new ClassPathResource("mapper/userMapper.xml"),
	            new ClassPathResource("mapper/fileMapper.xml")
	        }
		);
		아래 코드와 같다*/
		sqlSessionFactoryBean.setMapperLocations(
				context.getResources("classpath:mapper/*Mapper.xml"));
		
		//sqlSessionFactoryBean.setTypeAliasesPackage("user.bean");
		sqlSessionFactoryBean.setTypeAliasesPackage("*.*.bean");
		return sqlSessionFactoryBean.getObject();	//sqlSessionFactory 변환
	}
	
	@Bean
	public SqlSessionTemplate sqlTemplate() throws Exception {
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sqlSessionTemplate;
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource());
		return dataSourceTransactionManager;
	}
	
	@Bean
	@Scope("prototype")
	public UserDTO userDTO() {
		return new UserDTO();
	}
	
	@Bean
	public UserPaging userPaging() {
		return new UserPaging();
	}
	
	@Bean
	@Scope("prototype")
	public UploadDTO uploadDTO() {
		return new UploadDTO();
	}
}

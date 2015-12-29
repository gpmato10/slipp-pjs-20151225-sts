package net.slipp.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.slipp.dao.users.User;

public class MyBatisTest {
	private static final Logger logger = LoggerFactory.getLogger(MyBatisTest.class);
	
	@Test
	public void gettingStarted() throws Exception {
		String resource = "mybatis-config-test.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		try {
			User user = session.selectOne("UserMapper.findById", "javajigi");
			logger.debug("User : {}", user);
		} finally {
		  session.close();
		}
	}

}

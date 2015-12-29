package net.slipp.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.slipp.dao.users.User;

public class MyBatisTest {
	private static final Logger logger = LoggerFactory.getLogger(MyBatisTest.class);
	
	private SqlSessionFactory sqlSessionFactory;
	
	@Before
	public void setup() throws Exception {
		String resource = "mybatis-config-test.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	@Test
	public void gettingStarted() throws Exception {
	
		SqlSession session = sqlSessionFactory.openSession();
		try {
			User user = session.selectOne("UserMapper.findById", "javajigi");
			logger.debug("User : {}", user);
		} finally {
		  session.close();
		}
	}
	
	@Test
	public void insert() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			User user = new User("sanjigi", "password", "sanjigi", "sanjigi@slipp.net");
			session.insert("UserMapper.create", user);
			User actual = session.selectOne("UserMapper.findById", user.getUserId());
			assertThat(actual, is(user));
		} finally {
		  session.close();
		}
	}

}

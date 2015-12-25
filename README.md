slipp

2015.12.25 
STS 로 했음.

워크스페이스명은 : 01-PJS-slipp-201512-sts
프로젝트 명 : slipp

---template---

1) logger

${is1:import('org.slf4j.Logger')}${is2:import('org.slf4j.LoggerFactory')}private static final Logger logger = LoggerFactory.getLogger(${primary_type_name}.class);

위의 코드를 template 에 추가해 주면 됨. Java를 꼭 선택해야함.
logger 탭!

2)Unit Test (spring-test)

${is1:import('org.junit.runner.RunWith')}${is2:import('org.springframework.test.context.ContextConfiguration')}${is3:import('org.springframework.test.context.junit4.SpringJUnit4ClassRunner')}
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/${cursor}")

위 코드를 template에 추가함 
springtest  탭!


---template 끝 --- 

08-강

application-properties.xml 파일 설정하기.

--- 박재성님 강의중 샘플코드 (h2 용) ---

<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">
<properties>
	<comment>application-properties</comment>
	<!-- Database -->
	<entry key="database.driverClassName">org.h2.Driver</entry>
	<entry key="database.url">jdbc:h2:~/slipp</entry>
	<entry key="database.username">sa</entry>
	<entry key="database.password"></entry>
</properties>



<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<beans [...]>
	<context:property-placeholder location="classpath*:application-properties.xml" />
	<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource" destroy-method="close">
		<property name="driverClass" value="${database.driverClassName}" />
		<property name="jdbcUrl" value="${database.url}" />
		<property name="user" value="${database.username}" />
		<property name="password" value="${database.password}" />
	</bean>
</beans>

--- 박재성님 강의중 샘플코드 끝 ---

--- 박재성님 강의중 샘플코드 (MySQL 용) ---

<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">
<properties>
	<comment>application-properties</comment>
	<!-- Database -->
	<entry key="database.driverClassName">com.mysql.jdbc.Driver</entry>
	<entry key="database.url">jdbc:mysql://localhost/pjs</entry>
	<entry key="database.username">root</entry>
	<entry key="database.password">123123</entry>
</properties>



<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<beans [...]>
	<context:property-placeholder location="classpath*:application-properties.xml" />
	<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource" destroy-method="close">
		<property name="driverClass" value="${database.driverClassName}" />
		<property name="jdbcUrl" value="${database.url}" />
		<property name="user" value="${database.username}" />
		<property name="password" value="${database.password}" />
	</bean>
</beans>

--- 박재성님 강의중 샘플코드 끝 ---

08강 계속 설명
pom.xml에 
<dependency>
    <groupId>commons-dbcp</groupId>
    <artifactId>commons-dbcp</artifactId>
    <version>1.4</version>
</dependency>
추가.


강의에서 실제 실습한 내용 13:50 까지 완료.
--- org.apache.commons.dbcp.BasicDataSource 이용하기

아래는 application-properties.xml
```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">
<properties>
    <comment>application-properties</comment>

	<!-- Database -->
    <entry key="database.driverClassName">com.mysql.jdbc.Driver</entry>
    <entry key="database.url">jdbc:mysql://localhost/pjs</entry>
    <entry key="database.username">root</entry>
    <entry key="database.password">123123</entry>
</properties>
```

아래는 applicationContext.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:p="http://www.springframework.org/schema/p"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.1.xsd">

	<context:property-placeholder location="classpath*:application-properties.xml"/>
	
	<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource"
		p:driverClassName="${database.driverClassName}"
		p:url="${database.url}"
		p:username="${database.username}"
		p:password="${database.password}"
		>
	
	</bean>
</beans>
```
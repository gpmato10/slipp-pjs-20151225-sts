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

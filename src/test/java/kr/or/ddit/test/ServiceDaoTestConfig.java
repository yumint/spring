package kr.or.ddit.test;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)

//serviceDaoTest는 service,dao에 대한 설정파일만 필요하다 
@ContextConfiguration(locations= {"classpath:kr/or/ddit/config/spring/root-context.xml"})

// 스프링 컨테이너를 웹 기반에서 활용가능 한 WebApplicationContext로 생성 
//@WebAppConfiguration 웹기반이 아니기떄문에 지운것
public class ServiceDaoTestConfig {
	
	@Test
	public void test() {
		assertTrue(true);
	}

}

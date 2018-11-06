package kr.or.ddit.hello;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.user.model.UserVo;

/*
 sevlet
 1. HttpServlet 클래스 상송
 2. @WebServlet 혹은 web.xml에 url-mapping등록
 3. doGet , doPost같은 doXXX메소드를 통해 서비스를 개발
 
 **
 *spring controller
 *1. @Controller 어노테이션 적용
 *2. @RequestMapping 어노테이션 적용(class/ method)
 */

@RequestMapping("/hello")	 // 안써줘도 됨
@Controller
public class HelloController {
	
	private Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	// 사용자 요청 : localhost:8081/hello/hello.do url로 요청하게 되면 아래의 메소들에서 요청을 처리 
	// 만약 class에 적용한 @RequestMapping("/hello") 부분을 삭제하게 되면 
	//  localhost:8081/hello/hello.do url로 요청에 대해 hello() 메소드에서 요청을 처리하게 됨
	@RequestMapping("/hello.do")
	public String hello() {
		
		// viewName을 리턴
		// internalResoutceViewResolver 설정에 의해 
		// prefix = viewName + suffix 위치의 리소스로 응답 위임한다 
		// prefix : /WEB-INF/view
		// suffix : .jsp
		// viewName : hello
		// ==> /WEB-INF/view/hello.jsp
		//기본 : forward
		
		// /WEB-INF/view/hello.jsp
		return "hello";
	}
	
	
	/*
	  servlet doGet, doPost : 메소드 인자가 HttpServletRequest , HttpServletResponse
	  spring controller 메소드 : 비교적 자유롭게 구성이 가능 
	  							HttpServletRequest , HttpServletResponse,
	  							HttSession , ValueObject , 
	  							Model : view에서 표현할 데이터를 저장 
	 */
	@RequestMapping("/model")
	public String model(Model model) {
		
		List<String> rangers = new ArrayList<String>();
		rangers.add("brown");
		rangers.add("cony");
		rangers.add("sally");
		
		// servlet : request.setAttribute("rangers" , rangers);
		model.addAttribute("rangers", rangers);
	
		// redirect로 보내려면 redirect.hello로 사용하기
		return "hello";
	}
	
	@RequestMapping("/request")
	public String vo(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		
		// Logger를 이용한 출력
		logger.debug("userId : {}", userId);
		logger.debug("pass : {}", pass);
		
		return "hello";
	}
	
	// value object의 속성이름과 동일한 이름의 파라미터를 자동으로 설정해준다.
	@RequestMapping("/vo")
	public String vo(UserVo userVo) {
		
		logger.debug("userVo : {}",userVo);
		
		return "hello";
	}

}

package kr.or.ddit.user;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/user")
@Controller
public class UserController {
	
		// 스프링에서는 로그에 찍히게 하려면 syso이 아닌 Logger로 사용해야 한다
		private Logger logger = LoggerFactory.getLogger(UserController.class);
		
		@RequestMapping("/loginView")
		public String hello() {
			return "login/login";
		}

		@RequestMapping("/loginProcess")
		public String loginProcess(HttpServletRequest request) {
			String userId = request.getParameter("userId");
			String pass = request.getParameter("pass");
			
			
			if(userId.equals("brown") && pass.equals("1")) {
				
				// Logger를 이용한 출력
				logger.debug("userId : {}", userId);
				logger.debug("pass : {}", pass);
				
				return "main";
			}
			
			return "login/login";
			
		}
		


}

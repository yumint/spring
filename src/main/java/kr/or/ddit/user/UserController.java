package kr.or.ddit.user;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserServiceInf;
import kr.or.ddit.util.model.PageVo;


@RequestMapping("/user")
@Controller
public class UserController {
	
		// userService 스프링 빈 주입 
		@Resource(name="userService")
		private UserServiceInf userService;
	
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
		
		/**
		* Method : userAllList
		* 작성자 : PC
		* 변경이력 :
		* @return
		* Method 설명 :사용자 전체 조회
		*/
		@RequestMapping("/userAllList")
		public String userAllList(Model model) {
			/*
			UserServiceInf userService = new UserService();	// 캡슐화 한것 
			List<UserVo> userList = userService.selectUserAll();
			*/
			// userService 사용자 전체 정보조회
			List<UserVo> userList = userService.selectUserAll();
			/*
			 	request.setAttribute("userAllList", userList);
			 */
			model.addAttribute("userList" , userList);
			/*
			 RequestDispatcher rd = request.getRequestDispatcher("/user/userAllList.jsp");
			 rd.forward(request, response);
			 */
			return "user/userAllList";
		}
		
		@RequestMapping("/userPageList")
		public String userPageList(Model model , PageVo pageVo) {
			/*
			 	// userservice생성
				UserServiceInf userService = new UserService();	// 캡슐화 한것 
			 */
			// spring 컨테이너 로부터 bean을 주입받기 때문에 new 연산자를 통해서 생성할 필요 없음
			/*
			 	PageVo pageVo = new PageVo();
				//Integer.parseInt-> 숫자로 변환
				pageVo.setPage(Integer.parseInt(request.getParameter("page")));
				pageVo.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
			 */
			// controller method 매개변수로 선언
			
			
			/*
			 	Map<String , Object> resultMap = userService.selectUserPageList(pageVo);
		
				// 페이지 리스트 
				List<UserVo> userList = (List<UserVo>)resultMap.get("userList");
				
				// 페이지 건수 
				int pageCnt = (int) resultMap.get("pageCnt");
				
				// request 객체에 저장 
				request.setAttribute("pageList", userList);
				request.setAttribute("pageCnt", pageCnt);
			 */
			
			Map<String , Object> resultMap = userService.selectUserPageList(pageVo);
			model.addAllAttributes(resultMap);
			
			/*
			 * 	RequestDispatcher rd = request.getRequestDispatcher("/user/userPagingList.jsp");
				rd.forward(request, response);
			 */
			return "/user/userPagingList";
		}
		
		@RequestMapping("/userDetail")
		public String userDetail(@RequestParam("userId")String userId , Model model) {
			/*
			 * String userId = request.getParameter("userId");
			 */
			// @RequestParam 어노테이션을 통해 설정 
			
			/*
			 * 	UserServiceInf userService = new UserService();	// 캡슐화 한것 
				UserVo userInfo = userService.selectUser(userId);
			 */
			UserVo userInfo = userService.selectUser(userId);
			/*
			 * request.setAttribute("userInfo" , userInfo);
			 */
			
			model.addAttribute("userInfo" , userInfo);
			/*
			 	RequestDispatcher rd = request.getRequestDispatcher("/user/userDetail.jsp");
				rd.forward(request, response);
			 */
			
			return "/user/userDetail";
		}
		
		@RequestMapping(value="/userForm", method=RequestMethod.GET)
		public String userFormView() {
			return "user/userForm";
		}
		
		@RequestMapping(value="/userForm", method=RequestMethod.POST)
		public String userForm(@RequestPart("profilePic")MultipartFile part , 
								HttpServletRequest request , UserVo userVo) {
			
			
			try {
				if(part.getSize() > 0) {
					String path = request.getServletContext().getRealPath("/profile");
					String fileName = part.getOriginalFilename();
					
					part.transferTo(new File(path + File.separator + fileName));
					userVo.setProfile("/profile/" + fileName);
				}else {
					userVo.setProfile("");
				}
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			} 
			
			int insertCnt = userService.insertUser(userVo);
			
			//response.sendRedirect("/userPageList?page=1&pageSize=10");

			return "redirect:/user/userPageList?page=1&pageSize=10";
		}
		
		@RequestMapping(value="/userFormUpdate", method=RequestMethod.GET)
		public String userUpdateFormView(UserVo userVo, @RequestParam("userId")String userId ,Model model) {
			
			userVo = userService.selectUser(userId);
			
			model.addAttribute("userVo", userVo);
			
			return "user/userFormUpdate";
		}
		
		@RequestMapping(value="/userFormUpdate", method=RequestMethod.POST)
		public String userUpdateForm(UserVo userVo ,@RequestPart("profilePic")MultipartFile part
									,HttpServletRequest request) {
			
			try {
				if(part.getSize() > 0) {
					String path = request.getServletContext().getRealPath("/profile");
					String fileName = part.getOriginalFilename();
					
					part.transferTo(new File(path + File.separator + fileName));
					userVo.setProfile("/profile/" + fileName);
				}else {
					userVo.setProfile("");
				}
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			} 
			
			int updateCnt = userService.updateUser(userVo);
			
			return "redirect:userDetail?userId="+userVo.getUserId();
		}
		
		
		
		


}

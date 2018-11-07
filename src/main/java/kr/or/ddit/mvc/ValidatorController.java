package kr.or.ddit.mvc;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.model.UserVoJsr303;
import kr.or.ddit.user.model.UserVoValidator;

@RequestMapping("/validator")
@Controller
public class ValidatorController {
	
	// Validator를 테스트할 view(/WEB-INF/view/validator/view.jsp)를 요청할것 만들기 
	// 뷰에 대한 요청이기 때문에 get방식으로 해도 될것 같음 
	// httMethod : get
	// http://localhost:8081/validator/view
	@RequestMapping(value="/view" , method=RequestMethod.GET)
	public String view() {
		return "validator/view";
	}
	
	// view에서 입력할 값을 처리할 method 만들기
	// 검증절차에서 문제가 있을 경우 최초 테스트 view로 가서 메세지를 출력
	// 검증 절차에 문제가 없을 경우 result.jsp로 이동 
	// httMethod : post
	
	// 중요 !!! 
	//BindingResult 객체는 검증하고자 하는 vo 객체 뒤에 메소드 인자로 넣어야 한다
	// @@@메소드 인자 순서 주의 해야 한다
	@RequestMapping(value="/validate" , method=RequestMethod.POST)
	public String validae(UserVo userVo ,BindingResult bindingResult, Model model) {
		new UserVoValidator().validate(userVo, bindingResult);
		
		// 에러가 있는지 없는지 확인하는방법
		// hasErrors() -> 맞다 아니다를 리턴해줌
		if(bindingResult.hasErrors())

			return "validator/view";
		
		// 값넘기기 
		model.addAttribute("userVo", userVo);
		
		return "validator/result";
	}
	
	
	@RequestMapping(value="/validateJsr" ,  method=RequestMethod.POST)
	public String validateJsr(@Valid UserVoJsr303 userVoJsr303 ,BindingResult bindingResult, Model model) {
		
		// 에러가 있는지 없는지 확인하는방법
		// hasErrors() -> 맞다 아니다를 리턴해줌
		if(bindingResult.hasErrors())

			return "validator/view";
		// 값넘기기 
		model.addAttribute("userVoJsr303", userVoJsr303);
		return "validator/result";
	}

}

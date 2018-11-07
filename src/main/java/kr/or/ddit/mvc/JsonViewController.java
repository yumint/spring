package kr.or.ddit.mvc;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Controller
public class JsonViewController {
	
	@ModelAttribute
	public void ModelAttribute(Model model) {
		List<String> rangers = new ArrayList<String>();
		rangers.add("brown");
		rangers.add("cony");
		rangers.add("sally");
		
		model.addAttribute("rangers", rangers);
	}
	
	
	// json 응답을 생성하는 url 요청 
	// 레이져서 정보를 json으로 응답을 생성 
	// http://localhost:8081/rangersJsonView
	@RequestMapping("/rangersJsonView")
	public String rangersJsonView() {
		
		//아래오 같이 직접 작성해야 할 문자열을 
		// "{rangers : [{name:'cony' , id:'rabbit'}, {name:'brown', id:'brown'}
		
		// 컬렉션 , object 정보를 json 문자열로 생성 
		// {rangers : ["brown" ,"cony", "sally"]}
		
		//json을 생성하는 view
		return "jsonView";
		// <bean id="jsonView" class="org.springframework.web.servlet.view.json.MappingJackson2JsonView" />
		// 윗부분을 리턴하고 있다 
		
		// controller에서 리턴하는 viewName(jsonView)를 처리하기 위해 
		// 처리해줄 viewResolver를 우선순위에 따라서 
		// 1. beanNameViewResolver를 통해 view를 생성하려고 시도하게 됨 
		// 2. jsonView라고 하는 이름의 빈이 있는지를 확인  ==> 현재 만들어 줬기 때문에 존재함(servlet-context에 만듬)
		// 3. 해당 viewName과 동일한 이름의 빈이 있으므로 
		// internalResourceViewResolver 까지 가지 않고 beanNameViewResolver에서 처리하게 됨 
		
		// 만약 viewName이 "hello"인 경우라면 
		// 처리해줄 viewResolver를 우선순위에 따라서 
		// 1. beanNameViewResolver를 통해 view를 생성하려고 시도하게 됨 
		// 2. jsonView라고 하는 이름의 빈이 있는지를 확인  ==> 현재 존재하지 않음(servlet-context에 안 만듬)
		// <bean id="hello"..이런 빈은 없음
		// 3. 그렇다면 다음 우선순위인 internalResouceViewResolver가 처리하게 됨 
		// internalResourceViewResolver는 해당 리소스(hello.jsp)가 있는지 여부를 상과하지 않고, 
		// prefix, suffix를 무조건 반영하여 forward 
		// 그렇기 때문에 오타 혹은 존재하지 않는 파일(jsp)을 리턴하게 되면 에러가 발생하게 된다. 
		// 그런 이유로 internalResourceViewResolver는 viewResolver 설정시 우선순위를 최하위로 설정한다 
	}
	
	// http://localhost:8081/rangersJsonViewObj
	@RequestMapping("/rangersJsonViewObj")
	public View rangersJsonViewObj() {
		//json 생성을 담당하는 view
		return new MappingJackson2JsonView();
	}

}

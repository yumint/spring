package kr.or.ddit.prod;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.prod.service.ProdServiceInf;
import kr.or.ddit.util.model.PageVo;

@RequestMapping("/prod")
@Controller
public class ProdController {
	
	// userService 스프링 빈 주입 
	@Resource(name="prodService")
	private ProdServiceInf prodService;


	/**
	* Method : prodList
	* 작성자 : PC
	* 변경이력 :
	* @return
	* Method 설명 : 상품 리스트보기
	*/
	@RequestMapping("/prodList")
	public String prodList(Model model , PageVo pageVo) {
		
		List<ProdVo> prodList = prodService.selectProdAll();
		
		Map<String , Object> resultMap = prodService.selectProdPageList(pageVo);
		model.addAllAttributes(resultMap);
		
		return "/prod/prodList";
		
	}
	
	/**
	* Method : prodList
	* 작성자 : PC
	* 변경이력 :
	* @return
	* Method 설명 : 상품 상세보기
	*/
	@RequestMapping("/prodDetail")
	public String prodDetail(HttpServletRequest request , ProdVo prodVo, Model model) {
		String prodId = request.getParameter("prodId");
		
		prodVo = prodService.selectProd(prodId);
		
		model.addAttribute("prodVo" , prodVo);

		return "/prod/prodDetail";
		
	}

}

package kr.or.ddit.mvc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import kr.or.ddit.file.model.FileVo;
import kr.or.ddit.file.service.FileServiceInf;
import kr.or.ddit.file.util.FileUtil;

@SessionAttributes("rangers")
@Controller
public class MvcController {
	
	@Resource(name="fileService")
	private FileServiceInf fileservice;
	
	private static Logger logger = LoggerFactory.getLogger(MvcController.class);

	/* @RequestMapping이 붙은 메소드가 실행되기 전에 멀저 실행된다.
	 * 해당 컨트롤러에서 공통적으로 사용될 속성이 있을경우 
	 * 중복을 피하기 위해 @ModelAttribute 어노테이션을 적용한 
	 * 메소드를 통해 코드 중복을 막을 수 있다.
	 */	
	@ModelAttribute
	public void setup(Model model) {
		List<String> rangers = new ArrayList<>();
		rangers.add("brown");
		rangers.add("cony");
		rangers.add("sally");
		
		model.addAttribute("rangers", rangers);
	}
	
	//메서드 단위로 맵핑
	@RequestMapping("/mvc/view")
	// 모델에서 사용된 모델에 새로운 값을 추가 할수 있는 방법
	public String mvcView(@ModelAttribute("rangers")List<String> rangers) {
		rangers.add("edword");
		return "mvc/view";
	}
	
	//메서드 단위로 맵핑
	@RequestMapping("/mvc/view2")
	public String mvcView2() {
		
		return "mvc/view2";
	}
	
	
	//@pathvariable : 요청 url의 일부를 메소드 인자로 쉽게 받을수 있는 어노테이션
	// http://localhost:8081/mvc/central : logger central
	// http://localhost:8081/mvc/left : logger left
	@RequestMapping("/mvc/{libcd}")
	public String pathvariable(@PathVariable("libcd") String libcd) {
		logger.debug("libcd : {}" , libcd);
		return "mvc/view";
	}
	
	// fileupload 테스트를 위한 view(get방식) 
	@RequestMapping(value="/mvc/fileupload", method={RequestMethod.GET})
	public String fileuploadView(){
		return "mvc/fileuploadView";
	}
		
	
	//fileupload(파일전송)을 위한 controller method(post방식)
	@RequestMapping(value="/mvc/fileupload", method={RequestMethod.POST})
	public String fileupload(@RequestPart("uploadFile") MultipartFile part , 
								@RequestParam("userId") String userId){
		
		// 파라미터가 오는지 확인하기
		logger.debug("requestParam userId : {}", userId);
		
		
		// 1. file객체 생성(경로 + 파일명==> 파일명 충돌 방지를 위해 유니크 한 임의의 파일명을 생성)
		String path ="C:\\Users\\PC\\D_Other\\upload";	// 업로드할 파일 
		String orginalFileName = part.getOriginalFilename();	// 사용자가 업로드한 실제 파일명 
		String fileExt = FileUtil.getFileExt(orginalFileName);
		String fileName = UUID.randomUUID().toString() + fileExt; // 충돌 방지를 위한 임의의 파일명 
														// UUID.randomUUID().toString() -> 자동으로 파일명이 생성된다
		File file = new File(path + File.separator + fileName);
		FileVo fileVo = new FileVo();
		fileVo.setFile_name(fileName);
		fileVo.setFile_path(path);
		fileVo.setOrg_file_name(orginalFileName);
		// 확장자는 아래 방법으로 작업함
		// 확장자 넣어주기
		// FileUtil.getFileExt(String fileName);
		// 확장자가 있을 경우  .을 포함한 확장자 값을 리턴
		// 확장자가 없을 경우 "" whitespace를 리턴 
		// 테스트 값 : sally.png ==> .png
		// 테스트값 : sally ==> ""
		fileVo.setFile_ext(fileExt);
		
		logger.debug("fileVo : {}", fileVo);
		try {
			
			// 파일을 입력을 하지 않고 할떄 에러 나기 떄문에 입력
			if(part.getSize() > 0) {
				// 정해진 path에 업로드 파일을 작성
				part.transferTo(file);
				
				// 데이터 베이스에 파일을 저장하는 로직 
				//1. file Service
				// 2. 로직호출
				fileservice.insertFile(fileVo);
			}
			
			
		} catch (IllegalStateException |IOException e) {
			e.printStackTrace();
		} 
		return "mvc/fileuploadView";
	}
	
}

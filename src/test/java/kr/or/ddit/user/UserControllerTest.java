package kr.or.ddit.user;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.ControllerTestConfig;
import kr.or.ddit.user.model.UserVo;

public class UserControllerTest extends ControllerTestConfig{

   /**
   * Method : loginViewTest
   * 작성자 : ASUS
   * 변경이력 :
   * Method 설명 :loginView test
    * @throws Exception 
   */
   @Test
   public void loginViewTest() throws Exception {
      /***Given***/
      
      /***When***/
      MvcResult mvcResult = mockMvc.perform(get("/user/loginView")).andReturn();
      ModelAndView mav = mvcResult.getModelAndView();
      
      /***Then***/
      //veiwName
      assertEquals("login/login", mav.getViewName());
   }

   /**
   * Method : loginProcessTest
   * 작성자 : ASUS
   * 변경이력 :
   * Method 설명 : loginProcess test
    * @throws Exception 
   */
   @Test
   public void loginProcessSuccessTest() throws Exception {
      /***Given***/
      
      /***When***/
      MvcResult mvcResult = mockMvc.perform(post("/user/loginProcess").param("userId", "brown")
            .param("pass", "1")).andReturn();
      ModelAndView mav = mvcResult.getModelAndView();
      
      String viewName = mav.getViewName();
      

      /***Then***/
      assertEquals("main", viewName);
   }
   
   @Test
   public void loginProcessFailTest() throws Exception {
      /***Given***/
      
      /***When***/
      MvcResult mvcResult = mockMvc.perform(post("/user/loginProcess").param("userId", "browm")
            .param("pass", "1")).andReturn();
      ModelAndView mav = mvcResult.getModelAndView();
      
      String viewName = mav.getViewName();
      

      /***Then***/
      assertEquals("login/login", viewName);
   }
}
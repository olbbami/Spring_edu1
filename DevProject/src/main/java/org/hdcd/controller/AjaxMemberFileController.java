package org.hdcd.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/ajax")
public class AjaxMemberFileController {

	private static Logger logger =  LoggerFactory.getLogger(AjaxMemberFileController.class);
	
	// 10. 파일 업로드 Ajax 방식 요청 처리
	// 파일 업로드 Ajax 방식 요청 처리 시작 페이지
	@RequestMapping(value="/registerFileForm", method = RequestMethod.GET)
	public String ajaxRegisterFileForm() {
		return "member/ajaxRegisterFile";
	}
	
	// 1)
	// Ajax 방식으로 전달한 파일 요소값을 스프링 MVC가 지원하는 MultipartFile 매개변수로 처리한다.
	@RequestMapping(value="/uploadAjax", method = RequestMethod.POST, produces = "text/plain; charset=utf-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file){
		String originalFileName = file.getOriginalFilename();
		logger.info("originalFileName : " + originalFileName);
		ResponseEntity<String> entity = new ResponseEntity<String>("UPLOAD SUCCESS", HttpStatus.OK);
		return entity;
	}
}
















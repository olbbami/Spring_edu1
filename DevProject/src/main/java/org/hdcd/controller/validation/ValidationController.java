package org.hdcd.controller.validation;

import java.util.List;

import org.hdcd.vo.Address;
import org.hdcd.vo.Card;
import org.hdcd.vo.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/validation")
public class ValidationController {
	private static Logger logger = LoggerFactory.getLogger(ValidationController.class);
	/*
	 * 9장 입력 유효성 검증
	 * 
	 * 1. 입력값 검증
	 * 	스프링 MVC는 Bean Validaion 기능을 이용해 요청 파라미터 값이 바인딩 된 도메인 클래스(또는 커맨드 클래스)의 입력값 검증을 한다.
	 * 
	 * 		[환경 설정]
	 * 		# 의존 관계 정의
	 * 		# 입력값 검증 활성화
	 * 		- 입력값 검증을 하기 위해서는 메소드 매개변수에 도메인 클래스를 정의하고 @Validated를 지정합니다.
	 * 		    입력값 검증 대상의 도메인 클래스 직후에 BindingResult를 정의합니다.
	 * 		  BindingResult에는 요청 데이터의 바인딩 오류와 입력값 검증 오류 정보가 저장됩니다.
	 * 
	 * 		[환경 설정 순서 >>]
	 * 		1. 입력값 검증을 위한 의존 라이브러리를 추가합니다.
	 * 			- hibernate를 추가합니다.
	 * 		2. 입력값 검증 활성화
	 * 			- 활성화를 할 도메인 클래스에다가 @Validated 어노테이션을 지정합니다.
	 * 		3. 도메인 클래스 내 필드에다가 검증을 위한 어노테이션들로 데이터 검증을 설정합니다. (@NotBlank, @Size, @NotNull 등등)
	 * 		4. 에러를 받을 BindingResult를 설정합니다.
	 * 			- 컨트롤러 메소드 내 매개변수로 도메인 클래스를 먼저 선언하고(@Validated 어노테이션이 붙어 있는 클래스) 
	 * 			    해당 오른쪽 매개변수 자리에 BindingResult를 선언하여 에러 메세지 등을 바인딩 할 수 있도록 설정을 맞춰줍니다. 
	 * 		
	 * 			1) 의존 관계 정의
	 * 			- pom.xml에서 hibernate 라이브러리를 추가합니다.
	 * 
	 * 			2) 입력값 검증 활성화
	 * 			- Member 클래스로 넘어가서 userId, password, userName에 규칙을 활성화한다.
	 * 
	 * 			3) 컨트롤러 메소드 내 넘겨받는 자바빈즈 클래스에 @Validated 어노테이션 활성화, 그리고 바로 옆에 BindingResult 객체도
	 * 				매개변수로 받는다.
	 */
	
	// Validation 테스트할 폼 페이지 컨트롤러 메소드
	@RequestMapping(value="/registerValidationForm01", method = RequestMethod.GET)
	public String registerValidationForm01(Model model) {
		logger.info("registerValidationForm01");
		model.addAttribute("member", new Member());
		return "validation/registerValidationForm01";
	}
	
	// 1. 입력값 검증
	// 1) 테스트 하기 위한 폼 페이지에서 등록 요청을 했을때 정상적인 데이터 전송 시, 해당 컨트롤러 메소드에서 처리
	@RequestMapping(value="/result", method = RequestMethod.POST)
	public String registerValidationForm01Process(@Validated Member member, BindingResult result) {
		logger.info("registerValidationForm01Process");
		
		if(result.hasErrors()) {
			return "validation/registerValidationForm01";
		}
		
		logger.info("member.getUserId() : " + member.getUserId());
		logger.info("member.getPassword() : " + member.getPassword());
		logger.info("member.getUserName() : " + member.getUserName());
		logger.info("member.getGender() : " + member.getGender());
		
		return "validation/success";
	}
	
	/*
	 * 2. 입력값 검증 결과
	 * 
	 *  입력값 검증 대상의 도메인 클래스 직후에 BindingResult를 정의한다.
	 *  BindingResult에는 요청 데이터의 바인딩 에러와 입력값 검증 에러 정보가 저장된다.
	 */
	
	// 1) 입력값 검증 대상의 도메인 클래스 직후에 BindingResult를 정의한다.
	@RequestMapping(value="/registerValidationForm02", method = RequestMethod.GET)
	public String registerValidationForm02(Model model) {
		model.addAttribute("member", new Member());
		return "validation/registerValidationForm02";
	}
	
	// 검증 결과 페이지
	@RequestMapping(value="/result2", method = RequestMethod.POST)
	public String registerValidationForm02Process(@Validated Member member, BindingResult result) {
		logger.info("registerValidationForm02Process");
		// 입력값 검증 에러가 발생한 경우 true를 반환합니다.
		logger.info("result.hasError() : " + result.hasErrors());
		
		if(result.hasErrors()) {
			List<ObjectError> allErrors = result.getAllErrors();
			List<ObjectError> globalErrors = result.getGlobalErrors();
			List<FieldError> fieldErrors = result.getFieldErrors();
			
			logger.info("allErrors.size() : " + allErrors.size());
			logger.info("globalErrors.size() : " + globalErrors.size());
			logger.info("fieldErrors.size() : " + fieldErrors.size());
			
			for (int i = 0; i < allErrors.size(); i++) {
				ObjectError objectError = allErrors.get(i);
				logger.info("allError : " + objectError);
			}
			
			for (int i = 0; i < globalErrors.size(); i++) {
				ObjectError objectError = globalErrors.get(i);
				logger.info("globalErrors : " + objectError);
			}
			
			for (int i = 0; i < fieldErrors.size(); i++) {
				FieldError fieldError = fieldErrors.get(i);
				logger.info("fieldError :" + fieldError);
				logger.info("fieldError.getDefaultMessage() :" + fieldError.getDefaultMessage());
			}
			
			return "validation/registerValidationForm02";
		}
		
		// 에러가 없으면 아래 진행합니다.
		logger.info("member.getUserId() : " + member.getUserId());
		logger.info("member.getUserName() : " + member.getUserName());
		logger.info("member.getEmail() : " + member.getEmail());
		logger.info("member.getGender() : " + member.getGender());
		return "validaion/success";
	}
	
	/*
	 * 3. 입력값 검증 규칙
	 * 
	 * 입력값 검증 규칙은 Bean Validation이 제공하는 제약 어노테이션으로 설정한다.
	 * 검사 규칙은 크게 다음 세가지로 분류할 수 있다.
	 * - Bean Validation 표준 제약 어노테이션
	 * - 서드파티에서 구현한 제약 어노테이션
	 * - 직접 구현한 제약 어노테이션
	 */
	
	// 1) Member 클래스에서 테스트위한 어노테이션 설정( 아래 명시되어 있는 내용들을 가지고 테스트 해보길!!)
	// @NotNull 				: 빈 값이 아닌지를 검사한다.
	// @Null					: null인지를 검사한다.
	// @NotBlank				: 문자열이 null이 아니고 trim한 길이가 0보다 크다는 것을 감시한다.
	// @NotEmpty				: 문자열이 null이거나 비어있는지 검사한다.
	// @Size					: 글자 수나 컬렉션의 요소 개수를 검사한다.
	// 	> @Size(max=, min=)
	// @Max(value=)				: value보다 작거나 같은걸 검사한다.
	// @Min(value=)				: value보다 크거나 같은걸 검사한다.
	// @Email					: 이메일 주소 형식인지를 검사한다.
	// @Past					: 과거 날짜인지를 검사한다.
	// @Future					: 미래 날짜인지를 검사한다.
	// @Pattern(regexp=)		: CharSequence는 지정된 정규식과 일치해야하고, 정규식은 Java 정규식 규칙을 따른다.
	// @Positive				: 양수여야합니다.(0은 에러)
	// @PositiveOrZero			: 양수 또는 0이어야합니다.
	// @Length(min=,max=)		: 문자열의 길이가 min과 max사이인지 확인합니다. 
	
	/*
	 * 4. 중첩된 자바빈즈 입력값 검증
	 * 
	 * 중첩된 자바빈즈와 자바빈즈의 컬렉션에서 정의한 프로퍼티에 대해 입력값 검증을 할때는 @Valid를 지정한다.
	 * 
	 * 1) 중첩된 자바빈즈 클래스를 정의하고 @Valid를 지정한다.
	 * 	- Member 클래스 내 Address address 필드에 @Valid 어노테이션을 지정합니다.
	 * 	- Member 클래스 내 List<Card> cardList 필드에 @Valid 어노테이션을 지정합니다.
	 * 
	 * 2) Address 클래스에도 validation을 설정합니다.
	 * 	- Address 항목에 검사할 수 있도록 규칙 어노테이션을 지정합니다.
	 * 
	 * 3) Card 클래스에도 validation을 설정합니다.
	 * 	- Card 항목에 검사할 수 있도록 규칙 어노테이션을 지정합니다.
	 */
	
	@RequestMapping(value="/registerValidationForm03", method = RequestMethod.GET)
	public String registerValidationForm03(Model model) {
		model.addAttribute("member", new Member());
		return "validation/registerValidationForm03";
	}
	
	@RequestMapping(value="/result3", method=RequestMethod.POST)
	public String registerFormValidationResult(@Validated Member member, BindingResult result) {
		logger.info("registerFormValidationResult");
		
		if(result.hasErrors()) {
			return "validation/registerValidationForm03";
		}
		
		logger.info("member.getUserId() : " + member.getUserId());
		logger.info("member.getDateOfBirth() : " + member.getDateOfBirth());
		
		Address address = member.getAddress();
		
		if(address != null) {
			logger.info("address != null address.getPoseCode() : " + address.getPostCode());
			logger.info("address != null address.getLocation() : " + address.getLocation());
		}else {
			logger.info("address == null");
		}
		
		List<Card> cardList = member.getCardList();
		
		if(cardList != null) {
			logger.info("cardList != null : " + cardList.size());
			
			for (int i = 0; i < cardList.size(); i++) {
				Card card = cardList.get(i);
				logger.info("card.getNo() : " + card.getNo());
				logger.info("card.getValidMonth() : " + card.getValidMonth());
			}
		}else {
			logger.info("cardList == null");
		}
		
		return "validation/success";
	}
}














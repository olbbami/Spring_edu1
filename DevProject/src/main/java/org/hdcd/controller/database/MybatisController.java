package org.hdcd.controller.database;

public class MybatisController {
	/*
	 * 12장 마이바티스 (p514)
	 * 
	 * 1. 마이바티스란
	 * 
	 * 		1) What?
	 * 		마이바티스는 자바 퍼시스턴스 프레임워크의 하나로 XML 서술자나 어노테이션을 사용하여 저장프로시저나 SQL문으로 객체들을 연결시킨다.
	 * 		마이바티스는 Apache 라이선스 2.0으로 배포되는 자유 소프트웨이다.
	 * 
	 * 		2) 마이바티스를 사용함으로써 얻을 수 있는 이점
	 * 		- SQL의 체계적인 관리
	 * 		- 자바 객체와 SQL 입출력 값의 투명한 바인딩
	 * 		- 동적 SQL 조합
	 * 		
	 * 		3) 마이바티스 설정
	 * 
	 * 			3-1) 의존 관계 정의
	 * 			- mybatis
	 * 			- mybatis-spring
	 * 			- spring-jdbc
	 * 			- commons-dbcp2
	 * 			- log4jdbc-log4j2-jdbc4
	 * 			- ojdbc6
	 * 
	 * 				> 의존 관계 정의 후 Maven > Update Project를 진행한다.
	 * 
	 * 				*** 그런데 에러가 나? ...
	 * 				- 1) Maven 라이브러리 부분에 내가 추가한 라이브러리가 잘 들어있는지 확인
	 * 				- 2) Project Clean!!!!!!!!!!!!!!!!!!!!!!!!
	 * 					> 대번 이거까지하면 해결 됨! 
	 * 				- 3) 이클립스를 껐다 킨다
	 * 				- 4) 오타 확인(pom.xml)
	 * 				- 5) 프로젝트 Preferences 열고, 빌드 패스확인
	 * 				- 6) 프로젝트 Preferences 열고, Java Facet 확인
	 * 					> Java 1.8, Servlet 3.1 확인
	 * 				- 7) Window > Show View > Markers 뷰  활성화하고 에러 삭제
	 * 				- 8) Project Clean
	 * 				- 에러 해결 짜잔
	 * 
	 * 			3-2) 스프링과 마이바티스 연결 설정
	 * 			- root-context.xml 설정
	 * 				> dataSource
	 * 				> sqlSessionFactory
	 * 				> sqlSessionTemplate
	 * 				> basePackage
	 * 
	 * 			3-3) 마이바티스 설정
	 * 			- WEB-INF/mybatisAlias/mybatisAlias.xml 설정
	 * 			- 마이바티스의 위치 설정은 root-context의 'sqlSessionFactory'를 설정할 때 property 요소로 적용
	 * 
	 * 		4) 테이블 생성
	 * 
	 * 			4-1) board, member, member_auth 테이블 생성
	 * 2. Mapper 인터페이스
	 * - 인터페이스의 구현을 mybatis-spring 에서 자동으로 생성할 수 있다.
	 * 
	 * 		1) 마이바티스 구현
	 * 			
	 * 			1-1) Mapper 인터페이스 
	 * 			- BoardMapper.java 생성
	 * 
	 * 			1-2) Mapper 인터페이스와 매핑할 Mapper
	 * 			- sqlmap/boardMapper_SQL.xml 생성
	 * 				> namespace를 설정했는데, 그 namespace는 BoardMapper 인터페이스의 경로로 지정(패키지명을 포함한 경로)
	 * 				> _SQL.xml안에 있는 쿼리를 실행하기 위해서 namespace를 통해 위치를 찾고자 설정함!
	 * 
	 *  		1-3) 게시판 구현 설명
	 *  		- 게시판 컨트롤러 만들기 (board/CrudBoardController)
	 *  		- 게시판 등록 화면 컨트롤러 메소드 만들기 (crudRegister:get)
	 *  		- 게시판 등록 화면 만들기 (crud/register.jsp)
	 *  		- 게시판 등록 기능 컨트롤러 메소드 만들기 (crudRegister:post)
	 *  		- 게시판 등록 기능 서비스 인터페이스 메소드 만들기
	 *  		- 게시판 등록 기능 서비스 클래스 메소드 만들기
	 *  		- 게시판 등록 기능 Mapper 인터페이스 메소드 만들기
	 *  		- 게시판 등록 기능 Mapper xml 쿼리 만들기
	 *  		- 게시판 등록 완료 페이지 만들기
	 *  		- 		여기서 한번 기능 및 화면을 체크
	 *  		- 게시판 목록 화면 컨트롤러 메소드 만들기 (crudList:get)
	 *  		- 게시판 목록 화면 서비스 인터페이스 메소드 만들기
	 *  		- 게시판 목록 화면 서비스 클래스 메소드 만들기
	 *  		- 게시판 목록 화면 Mapper 인터페이스 메소드 만들기
	 *  		- 게시판 목록 화면 Mapper xml 쿼리 만들기
	 *  		- 게시판 목록 화면 만들기 (crud/list.jsp)
	 *  		-		여기서 한번 기능 및 화면을 체크
	 *  		- 게시판 상세 화면 컨트롤러 메소드 만들기
	 *  		- 게시판 상세 화면 서비스 인터페이스 메소드 만들기
	 *  		- 게시판 상세 화면 서비스 클래스 메소드 만들기
	 *  		- 게시판 상세 화면 Mapper 인터페이스 메소드 만들기
	 *  		- 게시판 상세 화면 Mapper xml 쿼리 만들기
	 *  		- 게시판 상세 화면 만들기 (crud/read.jsp)
	 *  		- 		여기서 한번 기능 및 화면을 체크
	 *  		- 게시판 수정 화면 컨트롤러 메소드 만들기(crudModify:get)
	 *  		- 게시판 수정 화면 서비스 인터페이스 메소드 만들기
	 *  		- 게시판 수정 화면 서비스 클래스 메소드 만들기
	 *  		- 게시판 수정 화면 Mapper 인터페이스 메소드 만들기
	 *  		- 게시판 수정 화면 Mapper xml 쿼리 만들기
	 *  		- 게시판 수정 화면 만들기 (crud/register.jsp - status 'u' 전달)
	 *  		- 게시판 수정 기능 컨트롤러 메소드 만들기 (crudModify:post)
	 *  		- 게시판 수정 기능 서비스 인터페이스 메소드 만들기
	 *  		- 게시판 수정 기능 서비스 클래스 메소드 만들기
	 *  		- 게시판 수정 기능 Mapper 인터페이스 메소드 만들기
	 *  		- 게시판 수정 기능 Mapper xml 쿼리 만들기
	 *  		-		여기서 한번 기능 및 화면을 체크
	 *  		- 게시판 삭제 기능 컨트롤러 메소드 만들기 (crudRemove:post)
	 *  		- 게시판 삭제 기능 서비스 인터페이스 메소드 만들기
	 *  		- 게시판 삭제 기능 서비스 클래스 메소드 만들기
	 *  		- 게시판 삭제 기능 Mapper 인터페이스 메소드 만들기
	 *  		- 게시판 삭제 기능 Mapper xml 쿼리 만들기
	 *  				여기서까지 확인
	 *  		- 기본적인 CRUD 끝!!!!
	 *  		
	 * 3. 별칭 적용
	 * - TypeAlias로 맵핑 파일에서 반복적으로 사용될 패키지의 이름을 정의한다.
	 * 
	 * 		1) 마이바티스 설정
	 * 
	 * 			1-1) mybatisAlias.xml 설정
	 * 			- typeAlias 설정을 한다.
	 * 
	 * 			1-2) boardMapper_SQL.xml 수정
	 * 			- 쿼리 태그에 각각 셋팅한 패키지명 대신 alias로 설정한 별칭으로 대체한다.		
	 * 
	 * 4. _로 구분된 컬럼명 자동 매핑
	 * - 마이바티스 설정의 maxUnderscoreToCamelCase 프로퍼티 값을 true로 지정하면 _로 구분된 컬러명을 소문자 낙타표기법의 프로퍼티명으로
	 * 	  자동 매핑할 수 있다.
	 * 
	 * 		'_' 포함되어 있는 데이터베이스 컬럼명을 카멜기법 셋팅으로 인해서 bo_no가 boNo로 처리된다. 
	 * 
	 * 		1) 마이바티스 설정 
	 * 			
	 * 			1-1) mybatisAlias.xml 설정 
	 * 				- <settings>
	 * 				-    <setting name="mapUnderscoreToCamelCase" value="true"/> 설정 추가 
	 * 			    - </settings>
	 * 				
	 * 			1-2) 매핑 파일 수정( boardMapper_SQL.xml)
	 * 			  - read 부분에서 as boardNo, as regDate 삭제 
	 * 			  - list부분에서 as boardNo, as regDate 삭제 	
	 * 
	 * 5. 기본키 취득 
	 * 	- 마이바티스는 useGeneratedKeys 속성을 이용하여 INSERT할 때 데이터베이스 측에서 체변된 기본키를 취득할 수 있다. 
	 * 		
	 * 		1) 데이터베이스 테이블 준비 
	 * 			
	 * 			1-1) 위에서 회원 테이블 만들어 놓음 
	 * 				- member 
	 * 			    - member_auth 
	 * 				- 2개의 테이블을 미리 준비 해놓음 
	 * 		    
 * 			2) 마이바티스 설정 
 * 				2-1) 매핑 파일 수정(boardMapper_SQL.xml)
 * 				  - create 부분에서 속성 추가 
 * 					> useGeneratedKeys = "true" keyProperty="boardNo"
 * 					> <selectKey order="BEFORE" resultType="int" keyProperty="boardNo">
 * 					>	select seq_board.nextval from dual 
 * 					> </selectKey>    
 * 					> insert into board(board_no, title, content, writer, reg_date
 * 					>) values (
 * 					> #{boardNo}, #{title}, #{content}, #{writer}, sysdate
 * 					> )  		
 * 					
 * 					*** currval 사용 시 주의 사항 
 * 					- select seq_board.currval from dual 
 * 					위 select 쿼리를 사용 시 , currval를 사용하는 데에 있어서 사용 불가에 대한 에러가 발생할 수 있다. 
 * 					currval를 사용할 때는 select seq_board.nextval from dual로 먼저 한번 실행후,
 * 					select seq_board.currval from dual로 사용하면 에러가 없음 
	 *				
	 *				** 대체 할 쿼리  
 * 			 		- select last_number from user_sequences where sequence_name = 'seq_board' 
	 * 				
	 * 			2-2) 컨트롤러 메소드에서 crudRegister 부분 수정
	 * 				- 전달받은 시퀀스가 boardNo에 들어 있기 때문에, 등록 후 전달받은 boardNo로 상세보기 화면으로 접근하도록 합시다!
	 */			
}

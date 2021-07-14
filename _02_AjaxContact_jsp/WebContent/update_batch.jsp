<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.ajaxstudy.contact.domain.*" %>
<%@ page import="com.ajaxstudy.contact.util.*" %>
<%
	// 브라우저로부터 json 데이터를 여러개 한번에 받아서
	// ContactList로 변환
	// UpdateBatch메서드 호출
	request.setCharacterEncoding("UTF-8");
	String status = "ok";
	String message = "";
	
	if(request.getMethod().equals("POST")){
		ContactList contactList = 
				Converter.convertFromJsonStream(
						request.getInputStream(),
						ContactList.class);
		if(contactList == null){
			status = "fail";
			message = "요청 정보 json 데이터 객체 변환 실패";
		}else{
			int count = SampleDAO.updateBatch(contactList);
			if(count > 0){
				message = "총 " + count + "건의 업데이트 성공";
			}else{
				status = "fail";
				message = "업데이트할 데이터가 존재하지 않습니다";
			}
		}
		
	}else{
		status = "fail";
		message = "POST 메서드만 지원합니다";
	}
%>
{
	"status":"<%=status%>",
	"message":"<%=message%>"
}














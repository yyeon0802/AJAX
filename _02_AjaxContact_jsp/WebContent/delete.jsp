<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.ajaxstudy.contact.domain.*" %>
<%@ page import="com.ajaxstudy.contact.util.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	String status = "ok";
	String message = "";
	
	if(request.getMethod().equals("POST")){
		boolean isDel = true;
		long no = 0;
		try{
			no = Long.parseLong(request.getParameter("no"));
		}catch(Exception e){
			isDel = false;
			status = "fail";
			message = "번호를 숫자로 변경할 수 없습니다";
		}
		if(isDel){
			int count = SampleDAO.deleteContact(no);
			if(count == 1){
				status = "ok";
				message = "일련번호 " + no + "번 데이터가 삭제되었습니다";
			}else{
				status = "fail";
				message = "삭제하려는 데이터가 존재하지 않습니다";
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










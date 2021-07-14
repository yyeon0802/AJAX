package com.ajaxstudy.contact.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ajaxstudy.contact.domain.Contact;
import com.ajaxstudy.contact.domain.Result;
import com.ajaxstudy.contact.util.Converter;
import com.ajaxstudy.contact.util.SampleDAO;

@SuppressWarnings("serial")
@WebServlet("/add.do")
public class ContactAddServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");

		// POST 방식을 사용
		request.setCharacterEncoding("UTF-8");
		String status = "ok";
		String message = "";

		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");

		if (name == null || name.equals("") || tel == null || tel.equals("")) {
			status = "fail";
			message = "이름과 전화번호는 필수 입력 사항입니다.";
		} else {
			Contact c = new Contact(0, name, tel, address);
			SampleDAO.addContact(c);
			message = "일련번호 " + c.getNo() + "번 데이터가 추가되었습니다";
		}
		
		// Java 객체 -> json 문자열로 변환
		Result result = new Result(status, message);
		String json = Converter.convertToJson(result);
		
		// 브라우저에 전송
		PrintWriter writer = response.getWriter();
		writer.println(json);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Result result = new Result("fail", "POST 메서드만 지원합니다");
		String json = Converter.convertToJson(result);
		PrintWriter writer = response.getWriter();
		writer.println(json);
	}
}





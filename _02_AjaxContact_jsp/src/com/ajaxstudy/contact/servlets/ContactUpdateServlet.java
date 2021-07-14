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
@WebServlet("/update.do")
public class ContactUpdateServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");

		request.setCharacterEncoding("UTF-8");
		String status = "ok";
		String message = "";

		long no = 0;
		try {
			no = Long.parseLong(request.getParameter("no"));
		} catch (Exception e) {
			System.out.println("번호를 정수로 변환할 수 없습니다");
			return;
		}
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");

		if (name == null || name.equals("") || tel == null || tel.equals("")) {
			status = "fail";
			message = "수정을 원하면 이름과 전화번호는 필수";
		} else {
			Contact c = new Contact(no, name, tel, address);
			int count = SampleDAO.updateContact(c);
			if (count == 1) {
				status = "ok";
				message = "일련번호 " + c.getNo() + "번 데이터가 수정되었습니다";
			} else {
				status = "fail";
				message = "수정하려는 데이터가 존재하지 않습니다";
			}
		}

		Result result = new Result(status, message);
		String json = Converter.convertToJson(result);
		
		PrintWriter writer = response.getWriter();
		writer.println(json);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		Result result = new Result("fail", "POST 메서드만 지원합니다");
		String json = Converter.convertToJson(result);
		
		PrintWriter writer = response.getWriter();
		writer.println(json);
	}
}














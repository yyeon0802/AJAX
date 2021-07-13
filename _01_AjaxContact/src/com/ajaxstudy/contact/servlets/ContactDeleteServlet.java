package com.ajaxstudy.contact.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ajaxstudy.contact.domain.Result;
import com.ajaxstudy.contact.util.Converter;
import com.ajaxstudy.contact.util.SampleDAO;

@SuppressWarnings("serial")
@WebServlet("/delete.do")
public class ContactDeleteServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		request.setCharacterEncoding("UTF-8");
		String status = "ok";
		String message = "";

		boolean isDel = true;
		long no = 0;
		try {
			no = Long.parseLong(request.getParameter("no"));
		} catch (Exception e) {
			isDel = false;
			status = "fail";
			message = "번호를 숫자로 변경할 수 없습니다";
		}
		if (isDel) {
			int count = SampleDAO.deleteContact(no);
			if (count == 1) {
				status = "ok";
				message = "일련번호 " + no + "번 데이터가 삭제되었습니다";
			} else {
				status = "fail";
				message = "삭제하려는 데이터가 존재하지 않습니다";
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

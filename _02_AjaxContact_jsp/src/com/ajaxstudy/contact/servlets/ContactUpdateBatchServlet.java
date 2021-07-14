package com.ajaxstudy.contact.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ajaxstudy.contact.domain.ContactList;
import com.ajaxstudy.contact.domain.Result;
import com.ajaxstudy.contact.util.Converter;
import com.ajaxstudy.contact.util.SampleDAO;

@SuppressWarnings("serial")
@WebServlet("/update_batch.do")
public class ContactUpdateBatchServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		// 브라우저로부터 json 데이터를 여러개 한번에 받아서
		// ContactList로 변환
		// UpdateBatch메서드 호출
		request.setCharacterEncoding("UTF-8");
		String status = "ok";
		String message = "";

		ContactList contactList = Converter.convertFromJsonStream(request.getInputStream(), ContactList.class);
		if (contactList == null) {
			status = "fail";
			message = "요청 정보 json 데이터 객체 변환 실패";
		} else {
			int count = SampleDAO.updateBatch(contactList);
			if (count > 0) {
				message = "총 " + count + "건의 업데이트 성공";
			} else {
				status = "fail";
				message = "업데이트할 데이터가 존재하지 않습니다";
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

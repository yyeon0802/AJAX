package com.ajaxstudy.contact.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ajaxstudy.contact.domain.Contact;
import com.ajaxstudy.contact.domain.ErrorMessage;
import com.ajaxstudy.contact.util.Converter;
import com.ajaxstudy.contact.util.SampleDAO;

@SuppressWarnings("serial")
@WebServlet("/search.do")
public class ContactSearchServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		boolean isNum = true;
		String strNo = request.getParameter("no");
		long no = -1L;
		try{
			no = Long.parseLong(strNo);
		}catch(Exception e){
			isNum = false;
		}
		String json = "";
		if(isNum){
			Contact c = SampleDAO.getContactByNo(no);		
			if(c != null){
				json = Converter.convertToJson(c);
			}else{
				ErrorMessage errorMessage = new ErrorMessage("해당 데이터가 없습니다");
				json = Converter.convertToJson(errorMessage);
			}
		}else{
			ErrorMessage errorMessage = new ErrorMessage("번호를 숫자로 변환할 수 없습니다");
			json = Converter.convertToJson(errorMessage);
		}
		
		PrintWriter writer = response.getWriter();
		writer.println(json);
	}
}










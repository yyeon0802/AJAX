package com.ajaxstudy.contact.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ajaxstudy.contact.domain.ContactList;
import com.ajaxstudy.contact.util.Converter;
import com.ajaxstudy.contact.util.SampleDAO;

@SuppressWarnings("serial")
@WebServlet("/list_long.do")
public class ContactListLongServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html;charset=UTF-8");
		
		String strPageno = req.getParameter("pageno");
		String strPagesize = req.getParameter("pagesize");
		int pageno = 0;	// 0이면 전체 조회, 1이상이면 해당 페이지
		int pagesize = 3; // 한 페이지 기본 크기
		
		try {
			pageno = Integer.parseInt(strPageno);
		}catch(Exception e) {
			pageno = 0;
			System.out.println("pageno가 없음");
		}
		try {
			pagesize = Integer.parseInt(strPagesize);
		}catch(Exception e) {
			pagesize = 3;
			System.out.println("pagesize가 없음");
		}
		
		// 브라우저가 요청한 주소록 데이터를 json으로 변환
		ContactList contactList = null;
		if(pageno==0)
			contactList = SampleDAO.getContacts();
		else
			contactList = SampleDAO.getContacts(
									pageno, pagesize);
		String json = Converter.convertToJson(contactList);
		
		try {
			Thread.sleep(1500);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 클라이언트로 전송
		PrintWriter writer = resp.getWriter();
		writer.println(json);
	}
}








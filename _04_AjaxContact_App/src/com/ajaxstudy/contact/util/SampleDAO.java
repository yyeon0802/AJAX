package com.ajaxstudy.contact.util;

import java.util.ArrayList;
import java.util.List;

import com.ajaxstudy.contact.domain.Contact;
import com.ajaxstudy.contact.domain.ContactList;

/*
연락처 리스트를 저장하고 있는 클래스
DB에 연결하지 않고 컬렉션 객체에 주소데이터를 보관
*/
public class SampleDAO {
	private static String namelist = "고수,공유,권상우,권율,김범,김수현," +
									"김우빈,현빈,남주혁,마동석,박보검,박서준," +
									"박해일,박해진,박형식,서강준,서인국,소지섭," +
									"송강호,신구,안재욱,양택조,옥택연,원기준," +
									"원빈,유오성,유지태,윤계상,윤상현,이계인," +
									"이광기,이덕화,이동욱,이병헌,이원종,이준기," +
									"인교진,임창정,임채무,전무송,전국환,전노민," +
									"정우성,정보석,정웅인,조인성,조정석,조진웅,"+
									"주지훈,지성,지창욱,지진희,지현우,주원," +
									"차인표,차승원,최다니엘,최민수,최민식,최불암,"+
									"하석진,현빈,홍광호,황정민";
	private static List<Contact> contacts;
	private static long nextNo = 0;
	
	// 데이터 초기화(주소록 정보 생성)
	static {
		String[] names = namelist.split(",");
		contacts = new ArrayList<Contact>();
		for(int i=0;i<names.length;i++) {
			nextNo++;
			String tel = "010-1111-22" + (i+10);
			Contact c = new Contact(nextNo, names[i], tel, "서울특별시");
			contacts.add(0, c);
		}
	}
	
	// 전체 주소 요청
	public static ContactList getContacts() {
		ContactList cList = 
				new ContactList(0, 0, contacts.size(), contacts);
		return cList;
	}
	
	// 특정 페이지와 페이지에 포함될 주소 개수 요청(일부 데이터만 전송)
	public static ContactList getContacts(int pageno, int pagesize) {
		int startIndex = (pageno-1) * pagesize; // 전송 시작 주소 위치
		int endIndex = startIndex + pagesize;	// 전송 종료 주소 위치
		
		List<Contact> temps = null;
		
		// 범위를 벗어난 요청을 한다면
		if(startIndex > contacts.size()-1 ||
		   startIndex < 0 || pagesize < 1) {
			temps = new ArrayList<Contact>();	// 빈 객체를 생성
		}
		// 정상 범위의 요청을 한다면
		else {
			// 전송 종료 주소 위치가 주소록 크기를 넘어서면 
			// 마지막 주소 위치로 정해준다
			if(endIndex > contacts.size())
				endIndex = contacts.size();
			
			// 시작과 끝에 해당하는 부분 리스트를 추출해서 저장한다
			temps = contacts.subList(startIndex, endIndex);
		}
		ContactList cList = new ContactList(pageno, pagesize,
									contacts.size(), temps);
		return cList;
	}
	
	// 주소록에 주소 추가
	public static void addContact(Contact c) {
		nextNo++;
		c.setNo(nextNo);
		contacts.add(0, c);
	}
	
	// 주소 삭제
	public static int deleteContact(long no) {
		int count = 0;	// 지워진 주소의 개수
		for(int i=0;i<contacts.size();i++) {
			Contact c = contacts.get(i);
			if(c.getNo() == no) {
				contacts.remove(i);
				count++;
				break;
			}
		}
		return count;
	}
	// 주소 수정
	public static int updateContact(Contact c) {
		long no = c.getNo();
		int count = 0;
		for(int i=0;i<contacts.size();i++) {
			Contact con = contacts.get(i);
			if(con.getNo() == no) {
				contacts.set(i, c);
				count++;
				break;
			}
		}
		return count;
	}
	// 여러 개의 주소들을 업데이트
	public static int updateBatch(ContactList contactList) {
		int count = 0;
		List<Contact> list = contactList.getContacts();
		if(list.size() > 0) {
			for(Contact c : list) {
				SampleDAO.updateContact(c);
				count++;
			}
		}
		return count;
	}
	
	public static Contact getContactByNo(long no) {
		Contact c = null;
		for(int i=0;i<contacts.size();i++) {
			Contact temp = contacts.get(i);
			if(temp.getNo() == no) {
				c = temp;
				break;
			}
		}
		return c;
	}
}












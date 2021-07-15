package com.ajaxstudy.contact.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.google.gson.Gson;

/*
 * java객체 <-> json 문자열
 *  : 상호변환이 가능한 라이브러리 
 * */

public class Converter {
	
	 private static Gson gson;

	 /*
	 	util이라 어디서나 사용가능하도록 static 생성
		 ->static은 객체를 만들지 않아도, class가 선언되면 메모리에 올라가있다.(사용할 준비가 되어있다.)
	 */
	
	 //	static 초기화 영역
	 static {
		 gson = new Gson();
	 }
	 
	 // java객체 -> json 문자열 변환
	 public static String convertToJson(Object obj) {
		 return gson.toJson(obj);
	 }
	 
	 //	json 문자열 -> (T타입 클래스)java객체로 변환
	 public static <T> T convertFromJson(String json, Class<T> type) {
		 return gson.fromJson(json, type);
	 }
	 
	 /* 브라우저와 연결된 스트림에서 전송된 데이터를 직접 읽기
	  *  -> (해당 클래스 타입의) java객체로 전환
	  * */	 
	  public static <T> T convertFromJsonStream(InputStream is, Class<T> type) {
		  try {
			Reader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			return gson.fromJson(reader, type);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	  }
}
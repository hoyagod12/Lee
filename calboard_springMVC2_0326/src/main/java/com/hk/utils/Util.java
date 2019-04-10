package com.hk.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import com.hk.caldtos.CalDto;

public class Util {

	//usebean을 쓰기 위한 구조를 만든다: dto처럼 맴버필드가 있고, get/set메서드가 있는 구조
	private String toDates;//날짜를 나타내는 문자열(mdate)을 날짜형식("yyyy-MM-dd hh:mm")으로 만들어서 저장할 변수
	
	//static, non-static, 파라미터존재여부, 반환타입(o,x void)
	//한자릿수 값을 두자릿수 값으로 반환해주는 메서드
	public static String isTwo(String s) {
		return s.length()<2?"0"+s:s;
	}

	//화면에서 getproperty 태그가 호출해줄 메서드
	public String getToDates() {
		return toDates;
	}
	
	//문자열을 날짜형식으로 표현하는 메서드
	//화면에서 setProperty 태그가 호출해줄 메서드
	public void setToDates(String mDate) {//mDate-->화면으로부터 12자리 문자열을 받게될 파라미터
		//date형식: yyyy-MM-dd hh:mm:ss---> 날짜형식으로 변환하려면 date형식으로 모양을 바꿔줘야한다.
		//문자열을 ---> date타입으로 변환해야된다.
		String m=mDate.substring(0, 4)+"-"   //year
				+mDate.substring(4, 6)+"-"   //month
				+mDate.substring(6, 8)+" "   //date
				+mDate.substring(8, 10)+":"  //hour
				+mDate.substring(10)+":00";   //min
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy년MM월dd HH시mm분");
		Timestamp tm=Timestamp.valueOf(m);//문자열을 date타입으로 변환
//		Integer.parseInt("a"); // 숫자형의 문자열을 정말 숫자형으로 변환하는 기능
		this.toDates = sdf.format(tm);
	}
	
	//달력에서 i(현재일)을 파라미터로 받아서 토요일/일요일을 구별해서 "blue"또는 "red" 문자열을 반환하는 메서드
		public static String fontColor(int dayOfWeek,int i){
			String color="";
			if((dayOfWeek-1+i)%7==0){//토요일이면
				color="blue";
			}else if((dayOfWeek-1+i)%7==1){//일요일이면
				color="red";
			}else{
				color="black";
			}
			return color;
		}
		
		public static String getCalView(List<CalDto> list, int i) {
			//list에는 mdate에 "201903011530"로 저장되어 있음
			//--->mdate에서 "01" 추출 == i에서 int형 1 --> "01"
			String d=isTwo(i+"");//숫자1을 문자열 "01"바꿔줌
			String calList="";//화면에 출력해줄 문자열(결과값)
			//"<p>title</p><p>title</p><p>title</p>"
			for (CalDto calDto : list) {
				if(calDto.getMdate().substring(6, 8).equals(d)) {
					calList+="<p title='"+calDto.getTitle()+"' class='clist' style='font-size:8px;'>"
						   +(calDto.getTitle().length()>6?calDto.getTitle().substring(0, 5)+"..":calDto.getTitle())   
						   +"</p>";
				}
			}
			return calList;
		}
}













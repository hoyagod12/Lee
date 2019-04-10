package com.hk.calboard.daos;

import java.util.List;

import com.hk.caldtos.CalDto;

public interface ICalDao {
		//일정목록조회: 파라미터(id,년월일8자리)
		public List<CalDto> getCalList(String id, String yyyyMMdd);
		
		//일정추가하기
		public boolean insertCalboard(CalDto dto);

		//달력에 일정 일부를 보여주는 기능(달력에 일정표시)
		public List<CalDto> getCalViewList(String id, String yyyyMM);

		//달력에서 일정개수를 보여주는 기능
		public int getCalViewCount(String id, String yyyyMMdd);

		//일정상세보기
		public CalDto getCalBoard(int seq);

		//일정수정하기
		public boolean updateCalBoard(CalDto dto);

		//일정삭제하기
		public boolean calMulDel(String[] seqs);

}

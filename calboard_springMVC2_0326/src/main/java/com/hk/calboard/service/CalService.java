package com.hk.calboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hk.calboard.daos.ICalDao;
import com.hk.caldtos.CalDto;
@Service
public class CalService implements ICalService {
	
	@Autowired
	private ICalDao caldao;
	

	//일정목록조회: 파라미터(id,년월일8자리)
	@Override
	public List<CalDto> getCalList(String id, String yyyyMMdd){
		return caldao.getCalList(id, yyyyMMdd);


	}

	//일정추가하기
	@Override
	public boolean insertCalboard(CalDto dto) {
		return caldao.insertCalboard(dto);

	}

	//달력에 일정 일부를 보여주는 기능(달력에 일정표시)
	@Override
	public List<CalDto> getCalViewList(String id, String yyyyMM){
		return caldao.getCalViewList(id, yyyyMM);

	}

	//달력에서 일정개수를 보여주는 기능
	@Override
	public int getCalViewCount(String id, String yyyyMMdd) {
		return caldao.getCalViewCount(id, yyyyMMdd);

	}

	//일정상세보기
	@Override
	public CalDto getCalBoard(int seq) {
		return caldao.getCalBoard(seq);

	}

	//일정수정하기
	@Override
	public boolean updateCalBoard(CalDto dto) {
		return caldao.updateCalBoard(dto);

	}

	//일정삭제하기
	@Override
	public boolean calMulDel(String[] seqs) {
		return caldao.calMulDel(seqs);


	}


}

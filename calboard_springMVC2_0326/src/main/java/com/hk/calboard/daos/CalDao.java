package com.hk.calboard.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.caldtos.CalDto;

@Repository
public class CalDao implements ICalDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private String namespace="com.hk.calboard.";

	//일정목록조회: 파라미터(id,년월일8자리)
	@Override
	public List<CalDto> getCalList(String id, String yyyyMMdd){
		Map<String, String>map = new HashMap<String, String>();
		map.put("id", id);
		map.put("yyyyMMdd", yyyyMMdd);
		return sqlSession.selectList(namespace+"callist", map);


	}

	//일정추가하기
	@Override
	public boolean insertCalboard(CalDto dto) {
		
		int count = sqlSession.insert(namespace+"calinsert" , dto);
		return count>0?true:false;

	}

	//달력에 일정 일부를 보여주는 기능(달력에 일정표시)
	@Override
	public List<CalDto> getCalViewList(String id, String yyyyMM){
		Map<String, String>map = new HashMap<String, String>();
		map.put("id", id);
		map.put("yyyyMM", yyyyMM);
		
		return sqlSession.selectList(namespace+"calviewlist", map);
	}

	//달력에서 일정개수를 보여주는 기능
	@Override
	public int getCalViewCount(String id, String yyyyMMdd) {
		Map<String, String>map = new HashMap<String, String>();
		map.put("id", id);
		map.put("yyyyMMdd", yyyyMMdd);
		int count = sqlSession.selectOne(namespace+"calcount", map);
		
		return count;

	}

	//일정상세보기
	@Override
	public CalDto getCalBoard(int seq) {
		return sqlSession.selectOne(namespace+"caldetail", seq);

	}

	//일정수정하기
	@Override
	public boolean updateCalBoard(CalDto dto) {
		int count = sqlSession.update(namespace+"calupdate", dto);
		return count>0?true:false;
	}

	//일정삭제하기
	@Override
	public boolean calMulDel(String[] seqs) {
		Map<String, String[]>map = new HashMap<String, String[]>();
		map.put("seqs", seqs);
		System.out.println("what is seqs= " +seqs);
		
		int count  = sqlSession.delete(namespace+"calMuldel", map);

		return count>0?true:false;
	}

}

























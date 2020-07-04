package com.nexon.narket.api.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.nexon.narket.api.common.vo.ImgVO;
import com.nexon.narket.core.base.BaseDao;

@Repository
public class FileDao extends BaseDao {
	
	public List<ImgVO> getImgs(int productNo) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("productNo", productNo);
		
		return sqlSession.selectList("common.file.getImgs", param);
	}
	
	public ImgVO getImg(int productNo, int imgNo) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("productNo", productNo);
		param.put("imgNo", imgNo);
		
		return sqlSession.selectOne("common.file.getImg", param);
	}
	
	public int insertImg(ImgVO img) throws Exception {
		return sqlSession.insert("common.file.insertImg", img);
	}

}

package com.onebus.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onebus.beans.GoodsScore;
import com.onebus.dao.BaseDAO;
import com.onebus.service.GoodsScoreService;

@Service("goodsScoreService")
@Transactional
public class GoodsScoreServiceImpl implements GoodsScoreService {

	@Resource
	private BaseDAO<GoodsScore> baseDAO;

	@Override
	public void saveGoodsScore(GoodsScore goodsScore) {
		baseDAO.save(goodsScore);

	}

	@Override
	public void delete(GoodsScore goodsScore) {
		baseDAO.delete(goodsScore);

	}

	@Override
	public List<GoodsScore> getAllGoodsScore() {
		String hql = "from com.onebus.beans.GoodsScore";
		return baseDAO.find(hql);
	}

	@Override
	public void updateGoodsScore(GoodsScore goodsScore) {
		baseDAO.update(goodsScore);

	}
}

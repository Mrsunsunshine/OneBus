package com.onebus.service;

import java.util.List;

import com.onebus.beans.GoodsScore;

public interface GoodsScoreService {

	public void saveGoodsScore(GoodsScore goodsScore);

	public void delete(GoodsScore goodsScore);

	public List<GoodsScore> getAllGoodsScore();

	public void updateGoodsScore(GoodsScore goodsScore);

}

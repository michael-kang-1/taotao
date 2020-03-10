package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {
	TaotaoResult deleteContent(long id);
	TaotaoResult insertContent(TbContent content);
	TaotaoResult updateContent(TbContent content);
	EUDataGridResult getContentList(long catId, Integer page, Integer rows);

}

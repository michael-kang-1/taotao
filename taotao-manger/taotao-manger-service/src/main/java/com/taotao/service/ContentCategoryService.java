package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;

public interface ContentCategoryService {

	List<EUTreeNode> getCategoryList(long parent_ID);
	TaotaoResult insertContentCategory(long parent_id, String name);
	TaotaoResult deleteContentCategory(Long parent_id, Long id);
	TaotaoResult renameContentCategory(long id, String name);
}

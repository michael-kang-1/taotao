package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemService;

/**
 * 商品管理Service
 * @author woc
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired 
	private TbItemDescMapper itemDescMapper;
	
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	@Override
	public TbItem getItemById(long itemId) {
		
		//TbItem item = itemMapper.selectByPrimaryKey(itemId);
		//添加查询条件
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			TbItem item = list.get(0);
			return item;
		}
		return null;
	}

	/**
	 * 商品列表查询
	 * <p>Title: getItemList</p>
	 * <p>Description: </p>
	 * @param page
	 * @param rows
	 * @return
	 * @see com.taotao.service.ItemService#getItemList(long, long)
	 */
	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		//查询商品列表
		TbItemExample example = new TbItemExample();
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbItem> list = itemMapper.selectByExample(example);
		//创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
/**
 * @throws Exception 
 * 
 */
@Override
public TaotaoResult createItem(TbItem item, String desc,String itemParams) throws Exception {
			//生成商品ID
			Long itemId = IDUtils.genItemId();
			item.setId(itemId);
			//设置商品的状态 1正常 2 下架 3 删除
			item.setStatus((byte)1);
			item.setCreated(new Date());
			item.setUpdated(new Date());
			//插入数据库
			itemMapper.insert(item);
			
			//添加商品描述信息
			TaotaoResult result = insertItemDesc(itemId, desc);
			if (result.getStatus() != 200) {
				throw new Exception();
			}
			
			//添加规格参数
			result=insertItemParamItem(itemId,itemParams);
			if (result.getStatus() != 200) {
				throw new Exception();
			}
			return TaotaoResult.ok();
}
/**
 * 添加商品描述
 * <p>Title: insertItemDesc</p>
 * <p>Description: </p>
 * @param desc
 */
private TaotaoResult insertItemDesc(Long itemId, String desc) {
	TbItemDesc itemDesc = new TbItemDesc();
	itemDesc.setItemId(itemId);
	itemDesc.setItemDesc(desc);
	itemDesc.setCreated(new Date());
	itemDesc.setUpdated(new Date());
	itemDescMapper.insert(itemDesc);
	return TaotaoResult.ok();
}

/**
 * 添加规格参数
 * <p>Title: insertItemParamItem</p>
 * <p>Description: </p>
 * @param itemId
 * @param itemParam
 * @return
 */
private TaotaoResult insertItemParamItem(Long itemId, String itemParam) {
	//创建一个pojo
	TbItemParamItem itemParamItem = new TbItemParamItem();
	itemParamItem.setItemId(itemId);
	itemParamItem.setParamData(itemParam);
	itemParamItem.setCreated(new Date());
	itemParamItem.setUpdated(new Date());
	//向表中插入数据
	itemParamItemMapper.insert(itemParamItem);
	
	return TaotaoResult.ok();
	
}



}
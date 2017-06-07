package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;

public interface ContentCategoryService {

	List<EUTreeNode> getCategoryList(long parentId);

	TaotaoResult insertContentCategory(long parentId, String name);
    /*课后作业 删除节点*/
	TaotaoResult deleteContentCategory(long parentId, String name);
	/*课后作业 重命名结点*/
	TaotaoResult renameContentCategory(long parentId, String name);

}

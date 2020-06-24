package service;

import java.util.List;

import domain.Category;

/**
 * 分类管理
 */
public interface CategoryService {
	/**
	 * 得到所有的分类
	 */
	List<Category> findAll();
	
	/**
	 * 通过Id查找
	 * @param id
	 * @return
	 */
	Category findById(int id);
}

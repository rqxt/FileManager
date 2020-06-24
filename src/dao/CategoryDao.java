package dao;

import java.util.List;

import domain.Category;

public interface CategoryDao {
	/**
	 * 查询所有
	 * @return
	 */
	List<Category> findAll();

	/**
	 * 通过id查找一个
	 * @param id
	 * @return
	 */
	Category findById(int id);
}

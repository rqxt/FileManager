package dao;

import java.util.List;

import domain.Category;

public interface CategoryDao {
	/**
	 * ��ѯ����
	 * @return
	 */
	List<Category> findAll();

	/**
	 * ͨ��id����һ��
	 * @param id
	 * @return
	 */
	Category findById(int id);
}

package service;

import java.util.List;

import domain.Category;

/**
 * �������
 */
public interface CategoryService {
	/**
	 * �õ����еķ���
	 */
	List<Category> findAll();
	
	/**
	 * ͨ��Id����
	 * @param id
	 * @return
	 */
	Category findById(int id);
}

package dao;

import java.util.List;

import domain.Tag;

public interface TagDao {
	
	/**
	 * ��ѯ����
	 * @return
	 */
	List<Tag> findByCategoryName(String name); 
}

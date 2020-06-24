package dao;

import java.util.List;

import domain.Tag;

public interface TagDao {
	
	/**
	 * 查询所有
	 * @return
	 */
	List<Tag> findByCategoryName(String name); 
}

package dao;

import java.util.List;

import domain.Tag;

public interface TagDao {
	
	/**
	 * ²éÑ¯ËùÓĞ
	 * @return
	 */
	List<Tag> findByCategoryName(String name); 
}

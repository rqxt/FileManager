package service;

import java.util.List;

import domain.Tag;

/**
 * 标签管理
 */
public interface TagService {
	/**
	 * 根据分类Id查找标签
	 */
	List<Tag> findByCategoryName(String id);
}

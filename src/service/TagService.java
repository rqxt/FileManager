package service;

import java.util.List;

import domain.Tag;

/**
 * ��ǩ����
 */
public interface TagService {
	/**
	 * ���ݷ���Id���ұ�ǩ
	 */
	List<Tag> findByCategoryName(String id);
}

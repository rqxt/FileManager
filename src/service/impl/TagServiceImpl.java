package service.impl;

import java.util.List;

import dao.TagDao;
import dao.impl.TagDaoImpl;
import domain.Tag;
import service.TagService;

public class TagServiceImpl implements TagService{
	private TagDao tagDao = new TagDaoImpl();
	
	@Override
	public List<Tag> findByCategoryName(String name) {
		
		return tagDao.findByCategoryName(name);
	}

}

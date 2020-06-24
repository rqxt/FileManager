package service.impl;

import java.util.List;

import dao.CategoryDao;
import dao.impl.CategoryDaoImpl;
import domain.Category;
import service.CategoryService;

public class CategoryServiceImpl implements CategoryService{
	private CategoryDao categoryDao = new CategoryDaoImpl();
	
	@Override
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	@Override
	public Category findById(int id) {
		return categoryDao.findById(id);
	}
	
}

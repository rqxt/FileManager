package dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import dao.CategoryDao;
import domain.Category;

public class CategoryDaoImpl implements CategoryDao {
	private BufferedReader br;
	private List<Category> list = null;
	
	public CategoryDaoImpl() {
		try {
			// 从当前目录下读取配置文件
			br = new BufferedReader(new InputStreamReader(new FileInputStream("category.txt")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			list = new ArrayList<Category>();
			String line = null;
			int i = 0;
			while ((line = br.readLine()) != null) {
				Category category = new Category();
				category.setName(line);
				category.setId(i++);
				list.add(category);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Category> findAll() {
		return list;
	}

	@Override
	public Category findById(int id) {
		Category ret = null;
		for(Category category : list) {
			if (category.getId() == id) {
				ret = category;
			}
		}
		return ret;
	}

}

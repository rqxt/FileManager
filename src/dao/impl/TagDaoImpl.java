package dao.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.TagDao;
import domain.Category;
import domain.Tag;

public class TagDaoImpl implements TagDao {
	private BufferedReader br;
	private Map<String, List<Tag>> cache;

	public TagDaoImpl() {
		try {
			// 从当前目录下读取配置文件
			br = new BufferedReader(new InputStreamReader(new FileInputStream("tag.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		cache = new HashMap<String, List<Tag>>();
		try {
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] split = line.split(":");
				List<Tag> tags = new ArrayList<Tag>();
				String[] strs = split[1].split("&&");
				for (int i = 0; i < strs.length; i++) {
					Tag tag = new Tag();
					tag.setCategoryName(split[0]);
					tag.setName(strs[i]);
					tag.setId(i);
					tags.add(tag);
				}
				cache.put(split[0], tags);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Tag> findByCategoryName(String name) {
		return cache.get(name);
	}

}

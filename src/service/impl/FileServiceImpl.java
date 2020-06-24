package service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import gui.Service;
import service.FileService;
import util.DateUtils;
import util.File7zUtils;

public class FileServiceImpl implements FileService {

	@Override
	public void copyFile(String path, String categroyName, String tagName) {
		// 获取当前路径
		String currentPath = FileServiceImpl.class.getClassLoader().getResource("").getPath();
		File source = new File(path);
		// 拷贝文件的路径
		String targetPath = currentPath + "//代码存储//" + categroyName + "//" + tagName;
		File target = new File(targetPath);

//		System.out.println(source);
//		System.out.println(target);
		// 递归拷贝文件目录结构
		iterate(source, target, tagName, 0);
	}

	@Override
	public void deleteFile() {

	}

	/**
	 * 递归复制文件
	 * 
	 * @param sourceFile 源文件
	 * @param targetFile 目标目录 （当前/分类/标签/
	 */
	private void iterate(File sourceFile, File targetFile, String tagName, int flag) {
		// 判断源文件对象类型
		if (sourceFile.isFile()) {
			// 添加时间戳和标签
			targetFile = new File(targetFile, sourceFile.getName());
			copyFile(sourceFile, targetFile);
		} else {
			// 如果是文件的话，sourceFile.getName()可以得到文件目录的名字
			String name = sourceFile.getName();
			if (flag == 0)
				name = addTagAndTime(name, tagName);
			targetFile = new File(targetFile, name);
			targetFile.mkdirs();
			File[] files = sourceFile.listFiles();
			for (int i = 0; i < files.length; i++) {
				iterate(files[i], targetFile, tagName, 1);
			}
		}

	}

	// 添加标签戳
	private String addTagAndTime(String name, String tagName) {
		String today = DateUtils.getToday();
		name = String.format("[%s].%s", tagName, name);
		return name;
	}

	/**
	 * 拷贝单个文件
	 * 
	 * @param sourceFile 源文件
	 * @param targetFile 目标文件
	 */
	private void copyFile(File sourceFile, File targetFile) {
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(sourceFile));
			out = new BufferedOutputStream(new FileOutputStream(targetFile));
			Service.showBoard.append("正在复制文件: " + sourceFile.getName() + " \n");
			int len;
			while ((len = in.read()) != -1) {
				out.write(len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 备份文件
	@Override
	public void backupFile(String from, String categoryName, String tagName) {
		// 获取当前路径
		String currentPath = FileServiceImpl.class.getClassLoader().getResource("").getPath();
		// 拷贝文件的路径
		String targetPath = currentPath + "/[备份]代码存储/" + categoryName + "/" + tagName;

		// 生成目标目录
		File f = new File(targetPath);
		f.mkdirs();

		// 添加标签戳
		String name = from.substring(from.lastIndexOf("\\") + 1);
		name = String.format("\\[%s].%s.7z", tagName, name);
		
		try {
			// 压缩文件
			File7zUtils.Compress7z(from, targetPath + name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

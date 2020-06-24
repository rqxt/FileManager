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
		// ��ȡ��ǰ·��
		String currentPath = FileServiceImpl.class.getClassLoader().getResource("").getPath();
		File source = new File(path);
		// �����ļ���·��
		String targetPath = currentPath + "//����洢//" + categroyName + "//" + tagName;
		File target = new File(targetPath);

//		System.out.println(source);
//		System.out.println(target);
		// �ݹ鿽���ļ�Ŀ¼�ṹ
		iterate(source, target, tagName, 0);
	}

	@Override
	public void deleteFile() {

	}

	/**
	 * �ݹ鸴���ļ�
	 * 
	 * @param sourceFile Դ�ļ�
	 * @param targetFile Ŀ��Ŀ¼ ����ǰ/����/��ǩ/
	 */
	private void iterate(File sourceFile, File targetFile, String tagName, int flag) {
		// �ж�Դ�ļ���������
		if (sourceFile.isFile()) {
			// ���ʱ����ͱ�ǩ
			targetFile = new File(targetFile, sourceFile.getName());
			copyFile(sourceFile, targetFile);
		} else {
			// ������ļ��Ļ���sourceFile.getName()���Եõ��ļ�Ŀ¼������
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

	// ��ӱ�ǩ��
	private String addTagAndTime(String name, String tagName) {
		String today = DateUtils.getToday();
		name = String.format("[%s].%s", tagName, name);
		return name;
	}

	/**
	 * ���������ļ�
	 * 
	 * @param sourceFile Դ�ļ�
	 * @param targetFile Ŀ���ļ�
	 */
	private void copyFile(File sourceFile, File targetFile) {
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(sourceFile));
			out = new BufferedOutputStream(new FileOutputStream(targetFile));
			Service.showBoard.append("���ڸ����ļ�: " + sourceFile.getName() + " \n");
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

	// �����ļ�
	@Override
	public void backupFile(String from, String categoryName, String tagName) {
		// ��ȡ��ǰ·��
		String currentPath = FileServiceImpl.class.getClassLoader().getResource("").getPath();
		// �����ļ���·��
		String targetPath = currentPath + "/[����]����洢/" + categoryName + "/" + tagName;

		// ����Ŀ��Ŀ¼
		File f = new File(targetPath);
		f.mkdirs();

		// ��ӱ�ǩ��
		String name = from.substring(from.lastIndexOf("\\") + 1);
		name = String.format("\\[%s].%s.7z", tagName, name);
		
		try {
			// ѹ���ļ�
			File7zUtils.Compress7z(from, targetPath + name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

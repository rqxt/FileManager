package service;

import java.io.File;

/**
 * 文件管理
 */
public interface FileService {
	/**
	 * 复制文件
	 * @param tagName 
	 * @param categroyName 
	 * @param path 
	 */
	void copyFile(String path, String categroyName, String tagName);
	
	/**
	 * 删除文件
	 */
	void deleteFile(File deleteFile);
	
	/**
	 * 备份文件
	 * @param from
	 * @param to
	 */
	void backupFile(String from, String categoryName, String tabName);
}

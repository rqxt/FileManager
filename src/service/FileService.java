package service;

/**
 * �ļ�����
 */
public interface FileService {
	/**
	 * �����ļ�
	 * @param tagName 
	 * @param categroyName 
	 * @param path 
	 */
	void copyFile(String path, String categroyName, String tagName);
	
	/**
	 * ɾ���ļ�
	 */
	void deleteFile();
	
	/**
	 * �����ļ�
	 * @param from
	 * @param to
	 */
	void backupFile(String from, String categoryName, String tabName);
}

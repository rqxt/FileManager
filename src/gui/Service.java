package gui;

import javax.swing.JTextArea;

import service.CategoryService;
import service.FileService;
import service.TagService;
import service.impl.CategoryServiceImpl;
import service.impl.FileServiceImpl;
import service.impl.TagServiceImpl;

public class Service {
	// Ԥ�ȼ�����Դ
	public static CategoryService categoryService = new CategoryServiceImpl();
	public static TagService tagService = new TagServiceImpl();
	public static FileService fileService = new FileServiceImpl();
	// ȫ�ֹ������
	public static String categroyName;
	public static String tagName;
	public static JTextArea showBoard;
}

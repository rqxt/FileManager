package gui;

import javax.swing.JTextArea;

import service.CategoryService;
import service.FileService;
import service.TagService;
import service.impl.CategoryServiceImpl;
import service.impl.FileServiceImpl;
import service.impl.TagServiceImpl;

public class Service {
	// 预先加载资源
	public static CategoryService categoryService = new CategoryServiceImpl();
	public static TagService tagService = new TagServiceImpl();
	public static FileService fileService = new FileServiceImpl();
	// 全局共享变量
	public static String categroyName;
	public static String tagName;
	public static JTextArea showBoard;
}

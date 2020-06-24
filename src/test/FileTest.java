package test;

import java.io.File;

public class FileTest {
	public static void main(String[] args) {
		File f1 = new File("D:\\eclipseBigBig\\安装包\\eclipse-java-2019-12-R-win32-x86_64.zip");
		File f2 = new File("C:\\Users\\llf\\Desktop");
		// 复制的是一个文件
		if (f1.isFile()) {
			// 源路径：C:\\Users\\llf\\Desktop\\每日一览.url
			System.out.println(f1.getTotalSpace());
			// 目标路径：分类/标签/标签.日期.文件名
		}
		// 如果复制的是一个文件夹的话，去递归
		if (f2.isDirectory()) {
			System.out.println(f2.getName());
		}
	}
}

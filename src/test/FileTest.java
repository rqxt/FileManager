package test;

import java.io.File;

public class FileTest {
	public static void main(String[] args) {
		File f1 = new File("D:\\eclipseBigBig\\��װ��\\eclipse-java-2019-12-R-win32-x86_64.zip");
		File f2 = new File("C:\\Users\\llf\\Desktop");
		// ���Ƶ���һ���ļ�
		if (f1.isFile()) {
			// Դ·����C:\\Users\\llf\\Desktop\\ÿ��һ��.url
			System.out.println(f1.getTotalSpace());
			// Ŀ��·��������/��ǩ/��ǩ.����.�ļ���
		}
		// ������Ƶ���һ���ļ��еĻ���ȥ�ݹ�
		if (f2.isDirectory()) {
			System.out.println(f2.getName());
		}
	}
}

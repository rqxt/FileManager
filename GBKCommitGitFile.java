
//package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.SequenceInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.print.attribute.standard.MediaSize.Other;

/**
 * �ύGit����
 */
public class GBKCommitGitFile {
	public static void main(String[] args) throws Exception {
		// ��û��push��commit��push��ȥ
		clearUnCommit();

		// �ύ���ʱ������
		String commentPath = "�����ύ��������¼.log";
		// ����·�� �ύ����.log
		String backupPath = "�ύ����.log";
		// ��ȡ�����ύ����
		File commentFile = new File(commentPath);
		File backupFile = new File(backupPath);
		// ����
		String comment = readFile(commentFile);
		// ����
		String backup = readFile(backupFile);
		// ����
		String date = getDate();

		// �߼�����
		print("��ʼ�ύ...");
		boolean commit = commit(comment);
		if (!commit) {
			print("---------------- �ύʧ�ܣ����˳�");
			return;
		}
		print("��ʼ��¼����...");

		writeFile(backupFile, date, comment, backup);
		print("��¼���ݳɹ�");
		// �������֮��ɾ�� "�����ύ��������¼.log"
		commentFile.delete();
		commentFile.createNewFile();
		print("������ '�����ύ��������¼.log'");
	}

	private static void clearUnCommit() throws Exception {
		print("���ύ3�Σ���֮ǰû�ύ�����ύ");
		Runtime runtime = Runtime.getRuntime();
		for (int i = 0; i < 3; i++) {
			print("git push");
			log(runtime.exec("cmd /c git push"));
		}
	}

	private static boolean log(Process pc) throws Exception {
		SequenceInputStream sis = new SequenceInputStream(pc.getInputStream(), pc.getErrorStream());
		InputStreamReader inst = new InputStreamReader(sis, "GBK");
		BufferedReader br = new BufferedReader(inst);
		String line;
		// �Ƿ���Ҫ�ύ
		boolean flag = true;
		while ((line = br.readLine()) != null) {
			if (line.contains("Everything up-to-date"))
				flag = false;
			print(line);
		}
		br.close();
		return flag;
	}

	private static boolean commit(String comment) throws Exception {
		// �ύGit
		Runtime runtime = Runtime.getRuntime();

		print("git add -A");
		log(runtime.exec("cmd /c git add -A"));
		// ���comment��Ϊ�գ���commit�ύ
		if (comment.equals("")) {
			print("����д�ύ˵����");
			return false;
		}
		String[] split = comment.split("\\n");
		StringBuilder sb = new StringBuilder("cmd /c git commit ");
		for (String str : split) {
			sb.append(" -m \"" + str + "\" ");
		}
		print("git commit -m\"...\"");
		log(runtime.exec(sb.toString()));

		print("git push");
		log(runtime.exec("cmd /c git push"));

		return true;

	}

	// д�ļ�ǰ���Ƚ��ļ����
	private static void writeFile(File file, String date, String comment, String backup) throws Exception {
		file.delete();
		file.createNewFile();

		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file), "GBK");
		BufferedWriter bw = new BufferedWriter(out);
		bw.write(date);
		bw.newLine();
		bw.write(comment);
		bw.newLine();

		// ��֮ǰ�ļ�¼������
		bw.newLine();
		bw.newLine();

		bw.write(backup);
		bw.flush();
		bw.close();
	}

	// ���ļ�
	private static String readFile(File file) throws Exception {
		InputStreamReader in = new InputStreamReader(new FileInputStream(file), "GBK");
		BufferedReader br = new BufferedReader(in);
		StringBuilder ret = new StringBuilder();
		char[] buf = new char[1024];
		int len;
		while ((len = br.read(buf)) != -1) {
			ret.append(buf, 0, len);
		}
		br.close();
		return ret.toString();
	}

	private static String getDate() {
		SimpleDateFormat format = new SimpleDateFormat("��¼ʱ�䣺yyyy��MM��dd�� HH:mm:ss");
		Date date = new java.util.Date();
		return format.format(date);
	}

	private static void print(String str) {
		System.out.println(str);
	}

	private static void print() {
		System.out.println();
	}
}

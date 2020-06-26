
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
	// �����ļ�·��
	private static String commentPath = "�����ύ��������¼.log";
	// ����·��
	private static String backupPath = "�ύ����.log";

	public static void main(String[] args) throws Exception {

		print("��ȡ�����ļ�...");
		File commentFile = new File(commentPath);
		String comment = readFile(commentFile);
		if (comment.equals("")) {
			print("----------�����ļ�Ϊ�գ�����д���ύ��");
			return;
		}

		print("���֮ǰ�Ƿ���δ����(push)���ύ(commit)...");
		clearUnCommit();

		print("��ʼ�ύ...");
		commit(comment);

		print("��ʼ������־...");
		File backupFile = new File(backupPath);
		String backup = readFile(backupFile);
		String date = getDate();
		writeFile(backupFile, date, comment, backup);
		print("---------������־���");

		print("ɾ���ļ��������ύ��������¼.log");
		commentFile.delete();
		commentFile.createNewFile();
		print("---------ɾ�����'");
	}

	private static void clearUnCommit() throws Exception {
		print("���Լ��3�Σ���������������ϣ����˳�");
		Runtime runtime = Runtime.getRuntime();
		for (int i = 0; i < 10; i++) {
			print("��" + (i + 1) + "�� " + " git push");
			boolean needPush = log(runtime.exec("cmd /c git push"));
			if (needPush == false) {
				print("----------ȫ�����ύ(commit)��������(push)��ȫ");
				return;
			}
		}
	}

	private static boolean log(Process pc) throws Exception {
		SequenceInputStream sis = new SequenceInputStream(pc.getInputStream(), pc.getErrorStream());
		InputStreamReader inst = new InputStreamReader(sis);
		BufferedReader br = new BufferedReader(inst);
		String line;
		// �Ƿ���Ҫ�ύ
		boolean needPush = true;
		while ((line = br.readLine()) != null) {
			if (line.contains("Everything up-to-date"))
				needPush = false;
			print(line);
		}
		br.close();
		return needPush;
	}

	private static void commit(String comment) throws Exception {
		Runtime runtime = Runtime.getRuntime();

		print("git add -A");
		log(runtime.exec("cmd /c git add -A"));
		
		String[] split = comment.split("\\n");
		StringBuilder sb = new StringBuilder("cmd /c git commit ");
		for (String str : split) {
			sb.append(" -m \"" + str + "\" ");
		}
		print("git commit -m\"...\"");
		log(runtime.exec(sb.toString()));

		print("git push");
		log(runtime.exec("cmd /c git push"));
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


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
 * 提交Git数据
 */
public class GBKCommitGitFile {
	public static void main(String[] args) throws Exception {
		// 将没有push的commit，push上去
		clearUnCommit();

		// 提交变更时的留言
		String commentPath = "本次提交解决问题记录.log";
		// 备份路径 提交备份.log
		String backupPath = "提交备份.log";
		// 读取本次提交内容
		File commentFile = new File(commentPath);
		File backupFile = new File(backupPath);
		// 留言
		String comment = readFile(commentFile);
		// 备份
		String backup = readFile(backupFile);
		// 日期
		String date = getDate();

		// 逻辑部分
		print("开始提交...");
		boolean commit = commit(comment);
		if (!commit) {
			print("---------------- 提交失败，已退出");
			return;
		}
		print("开始记录备份...");

		writeFile(backupFile, date, comment, backup);
		print("记录备份成功");
		// 备份完成之后，删除 "本次提交解决问题记录.log"
		commentFile.delete();
		commentFile.createNewFile();
		print("已重置 '本次提交解决问题记录.log'");
	}

	private static void clearUnCommit() throws Exception {
		print("先提交3次，将之前没提交的先提交");
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
		// 是否需要提交
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
		// 提交Git
		Runtime runtime = Runtime.getRuntime();

		print("git add -A");
		log(runtime.exec("cmd /c git add -A"));
		// 如果comment不为空，则commit提交
		if (comment.equals("")) {
			print("请填写提交说明！");
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

	// 写文件前，先将文件清空
	private static void writeFile(File file, String date, String comment, String backup) throws Exception {
		file.delete();
		file.createNewFile();

		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file), "GBK");
		BufferedWriter bw = new BufferedWriter(out);
		bw.write(date);
		bw.newLine();
		bw.write(comment);
		bw.newLine();

		// 与之前的记录空两行
		bw.newLine();
		bw.newLine();

		bw.write(backup);
		bw.flush();
		bw.close();
	}

	// 读文件
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
		SimpleDateFormat format = new SimpleDateFormat("记录时间：yyyy年MM月dd日 HH:mm:ss");
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

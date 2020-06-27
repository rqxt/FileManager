
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
public class CommitGitFile {
	// 留言文件路径
	private static String commentPath = "本次提交解决问题记录.log";
	// 备份路径
	private static String backupPath = "提交备份.log";

	public static void main(String[] args) throws Exception {

		print("读取留言文件...");
		File commentFile = new File(commentPath);
		String comment = readFile(commentFile);
		if (comment.equals("")) {
			print("----------留言文件为空，请填写后提交！");
			return;
		}

		print("检查之前是否有未推送(push)的提交(commit)...");
		clearUnCommit();

		print("开始提交...");
		boolean committed = commit(comment);
		if (!committed) {
			print("----------提交失败，没有需要推送的修改");
			return;
		}

		print("----------提交成功");
		print("开始备份日志...");
		File backupFile = new File(backupPath);
		String backup = readFile(backupFile);
		String date = getDate();
		writeFile(backupFile, date, comment, backup);
		print("---------备份日志完成");

		print("删除文件：本次提交解决问题记录.log");
		commentFile.delete();
		commentFile.createNewFile();
		print("---------删除完毕'");
	}

	private static void clearUnCommit() throws Exception {
		print("尝试检查3次，若发现已推送完毕，则退出");
		Runtime runtime = Runtime.getRuntime();
		for (int i = 0; i < 10; i++) {
			print("第" + (i + 1) + "次 " + " git push");
			boolean needPush = log(runtime.exec("cmd /c git push"));
			if (needPush == false) {
				print("----------全部的提交(commit)均已推送(push)完全");
				return;
			}
		}
	}

	private static boolean log(Process pc) throws Exception {
		SequenceInputStream sis = new SequenceInputStream(pc.getInputStream(), pc.getErrorStream());
		InputStreamReader inst = new InputStreamReader(sis);
		BufferedReader br = new BufferedReader(inst);
		String line;
		// 是否需要提交
		boolean needPush = true;
		while ((line = br.readLine()) != null) {
			if (line.contains("Everything up-to-date"))
				needPush = false;
			print(line);
		}
		br.close();
		return needPush;
	}

	private static boolean commit(String comment) throws Exception {
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
		boolean committed = log(runtime.exec("cmd /c git push"));
		return committed;
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

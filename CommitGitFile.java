
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
	public static void main(String[] args) throws Exception {
		// 提交变更时的留言
		String commentPath = "本次提交解决问题记录.log";
		// 备份路径 提交备份.log
		String backupPath = "提交备份.log";
		// 读取本次提交内容
		File commentFile = new File(commentPath);
		File backupFile = new File(backupPath);

		// 留言
		String comment = null;
		// 备份
		String backup = null;
		// 日期
		String date = null;

		// 逻辑部分
		comment = readFile(commentFile);
		backup = readFile(backupFile);
		date = getDate();
		System.out.println("开始提交...");
		commit(comment);
		System.out.println("开始备份...");
		// 如果comment "本次提交解决问题记录.log" 为空，不做备份
		if (comment.equals("")) {
			System.out.println("无需备份");
			return;
		}
		System.out.println("备份成功");
		writeFile(backupFile, date, comment, backup);
		// 备份完成之后，删除 "本次提交解决问题记录.log"
		commentFile.delete();
		commentFile.createNewFile();
		System.out.println("重置 '本次提交解决问题记录.log'");
	}

	private static void log(Process pc) throws Exception {
		SequenceInputStream sis = new SequenceInputStream(pc.getInputStream(), pc.getErrorStream());
		InputStreamReader inst = new InputStreamReader(sis, "GBK");
		BufferedReader br = new BufferedReader(inst);
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();
	}

	private static void commit(String comment) throws Exception {
		// 提交Git
		Runtime runtime = Runtime.getRuntime();

		log(runtime.exec("cmd /c git add -A"));
		// 如果comment不为空，则commit提交
		if (!comment.equals("")) {
			String[] split = comment.split("\\n");
			StringBuilder sb = new StringBuilder("cmd /c git commit ");
			for (String str : split) {
				sb.append(" -m \"" + str + "\" ");
			}
			log(runtime.exec(sb.toString()));
		}

		log(runtime.exec("cmd /c git push"));

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
}

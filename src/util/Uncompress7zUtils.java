package util;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

import java.io.*;


public class Uncompress7zUtils {

    public static void Uncompress(String inputFile, String destDirPath) throws Exception {
        /**
         * zip解压
         * @param inputFile 待解压文件名
         * @param destDirPath  解压路径
         */
        File srcFile = new File(inputFile);//获取当前压缩文件
        // 判断源文件是否存??
        if (!srcFile.exists()) {
            throw new Exception(srcFile.getPath() + "??指文件不存在");
        }
        //??始解??
        SevenZFile zIn = new SevenZFile(srcFile);
        SevenZArchiveEntry entry = null;
        File file = null;
        while ((entry = zIn.getNextEntry()) != null) {
            if (!entry.isDirectory()) {
                file = new File(destDirPath, entry.getName());
                if (!file.exists()) {
                    new File(file.getParent()).mkdirs();//创建此文件的上级目录
                }
                OutputStream out = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(out);
                int len = -1;
                byte[] buf = new byte[1024];
                while ((len = zIn.read(buf)) != -1) {
                    bos.write(buf, 0, len);
                }
                // 关流顺序，先打开的后关闭
                bos.close();
                out.close();
            }
        }
    }
    public static void main (String[]args){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("uncompress7z.txt"));
            String from = br.readLine();
            String to = br.readLine();
            Uncompress(from, to);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

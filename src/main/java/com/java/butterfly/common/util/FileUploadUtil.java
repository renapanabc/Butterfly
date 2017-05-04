package com.java.butterfly.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 文件上传工具类    .
 *
 * @author jiangqiubai
 * @version  v 1.0
 * @ClassName: FileUploadUtil
 * @date: 2016年5月18日 下午4:46:21
 */
public class FileUploadUtil {
    
    /** 临时使用常量， 返回项目的基本访问路径，包含协议至项目名称内容，如：如http://bqjr:8080/cms/ */
    private static String projectBasePath = "http://localhost:8080/cm/";
    
    /** 临时使用常量， 上传文件存放的本地路径. */
    private static String projectUploadPath = "D:/cms-upload-123456";
    
    static {
        if (isLinuxOs()) {
            // 生产环境或者测试环境
            projectBasePath = "http://10.80.6.158:8080/contractmanagement/";
            projectUploadPath = getProjectPath() + File.separator + "upload";
        }
    }
    
    /**
     * 将输入流中的文件内容写入到磁盘    .
     *
     * @author jiangqiubai
     * @param fis 文件输入流
     * @param outputFilePath 文件的全路径，包含名称
     * @throws IOException void 返回值
     * @date: 2016年5月18日 下午4:52:29
     * @Title: writeFile
     */
    public static void writeFile(InputStream fis, String outputFilePath) throws IOException {
        byte[] bytes = new byte[1024];
        int length = 0;
        //RandomAccessFile raf = new RandomAccessFile(outputFilePath, "rw");
        File file = new File(outputFilePath);
        FileOutputStream fos = new FileOutputStream(file);
        while ((length = fis.read(bytes)) > 0) {
            fos.write(bytes, 0, length);
        }
        fis.close();
        fos.close();
    }
    
    /**
     * 根据文件名称获取文件访问的全路径，如http://www.baidu.com/file/logo.jpg
     *
     * @author jiangqiubai
     * @param fileName 文件名称
     * @return String 返回值 全路径 ，包含文件名称在内
     * @date: 2016年5月18日 下午4:58:48
     * @Title: getFileFullPath
     */
    public static String getFileFullPath(String fileName) {
        System.out.println(projectBasePath + "/upload/" + fileName);
        return projectBasePath + "upload/" + fileName;
    }
    
    /**
     * TODO(这里用一句话描述这个方法的作用).
     *
     * @author mali
     * @param fileName the file name
     * @return String
     * @title: getFileStoreFullPath
     * @date: 2016-5-20 15:11:47
     */
    public static String getFileStoreFullPath(String fileName) {
        System.out.println(getProjectUploadPath());
        String uploadDirectoryPath = getProjectUploadPath();
        return uploadDirectoryPath + File.separator + fileName;
    }
    
    /**
     * 生成新的文件名称，采用uuid，去掉"-", 共32位   .
     *
     * @author jiangqiubai
     * @return String 返回值
     * @date: 2016年5月18日 下午8:42:59
     * @Title: generateNewFileName
     */
    public static String generateNewFileName() {
        String uuid = UUID.randomUUID().toString();
        String newFileName = uuid.replaceAll("\\-", "");
        return newFileName;
    }
    
    /**
     * 
     * 临时使用方法， 返回项目的基本访问路径，包含协议至项目名称内容，如：如http://bqjr:8080/cms/    
     * @author jiangqiubai    
     * @date: 2016年5月20日 下午12:56:02
     * @Title: getProjectBasePath    
     * @return String 返回值
     */
    public static String getProjectBasePath() {
        return projectBasePath;
    }
    
    /**
     * 
     * 临时使用方法，  上传文件存放的本地路径
     * @author jiangqiubai    
     * @date: 2016年5月20日 下午12:56:02
     * @Title: getProjectBasePath    
     * @return String 返回值
     */
    private static String getProjectUploadPath() {
        File file = new File(projectUploadPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        System.out.println(getProjectUploadPath());
        System.out.println(getProjectPath());
    }
    
    public static boolean isLinuxOs() {
        String osName = System.getProperty("os.name").toLowerCase();
        if ("linux".equals(osName)) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean isWindowsOs() {
        String osName = System.getProperty("os.name").toLowerCase();
        if ("windows".equals(osName)) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 获取项目的绝对路径
     * @author jiangqiubai    
     * @date: 2016年5月30日 下午12:30:54
     * @Title: getProjectPath    
     * @return String 返回值
     */
    public static String getProjectPath() {
        String classPath = FileUploadUtil.class.getResource("/").getPath();
        File file = new File(classPath);
        if (!file.exists()) {
            throw new IllegalStateException("classpath目录不存在,classpath:" + classPath);
        }
        File projectPath = file.getParentFile().getParentFile();
        return projectPath.getAbsolutePath();
    }
}

package cn.hlsd.websys.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Eangaie
 * @date 2018/9/4 0004 上午 8:45
 */
@Slf4j
@Component
public class FileUtil {

    private String ROOT_PATH = "static/";

    /**
     * @return java.lang.String
     * @author Eangaie
     * @Description 单文件上传, 直接上传到服务器
     * @Date 15:22 2019/5/16
     * @Param [path: path/, file, fix]
     **/
    public synchronized String singleUpload(String path, byte[] file, String fix) {
        File targetFile = new File(ROOT_PATH + path);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = null;
        String fileName = String.valueOf(System.currentTimeMillis());
        try {
            out = new FileOutputStream(ROOT_PATH + path + fileName + fix);
            out.write(file);
            out.flush();
            out.close();
            return path + fileName + fix;
        } catch (IOException e) {
            log.error("文件名为" + ROOT_PATH + path + fileName + fix + "的文件上传失败");
        }
        return null;
    }
    /**
     * 文件删除
     *
     * @param fileName
     * @return
     */
    public boolean delFile(String fileName) {
        fileName = ROOT_PATH + fileName;
        File file = new File(fileName);
        if (file.exists() && file.isFile())
            file.delete();
        return true;
    }

    /**
     * 获取后缀
     *
     * @param fileName
     * @return
     */
    public static String getFix(String fileName) {
        String fix;
        fix = fileName.substring(fileName.lastIndexOf("."));
        return fix;
    }

}

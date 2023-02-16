package com.admin.frame;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;

public class ImgUtil {


    public static void saveFile(MultipartFile mf, String admindir, String custdir) throws Exception {
        byte[] data;
        String imgname = mf.getOriginalFilename();
        int index = imgname.indexOf("/");
        admindir += "women/아우터/경량패딩/" + imgname.substring(0, index);
        custdir += "women/아우터/경량패딩/" + imgname.substring(0, index);
        createFile(admindir);
        createFile(custdir);
        String jpg_name = imgname.substring(index);
        try {
            data = mf.getBytes();
            FileOutputStream fo =
                    new FileOutputStream(admindir + jpg_name);
            fo.write(data);
            fo.close();
            FileOutputStream fo2 =
                    new FileOutputStream(custdir + jpg_name);
            fo2.write(data);
            fo2.close();
        } catch (Exception e) {
            throw e;
        }
    }

    public static void createFile(String dir) {
        String path = dir;
        File Folder = new File(path);
        if (!Folder.exists()) {
            Folder.mkdir();
        }
    }

}

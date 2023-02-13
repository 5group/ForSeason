package com.admin.frame;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;

public class ImgUtil {


    public static void saveFile(MultipartFile mf, String admindir, String custdir) throws Exception {
        byte[] data;
        String imgname = mf.getOriginalFilename();
        int index = imgname.indexOf("/");
        admindir += "women/아우터/경량패딩/"+imgname.substring(0, index);
        custdir += "women/아우터/경량패딩/"+imgname.substring(0, index);
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
        String path = dir; //폴더 경로
        File Folder = new File(path);
        // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
        if (!Folder.exists()) {
            Folder.mkdir(); //폴더 생성합니다.
        }
    }

}

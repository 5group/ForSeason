package com.shop.service;

import com.shop.dto.Category;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileService {
    public String[] getFileList(String custdir, String topName, String midName, String botName, String itemName) {
        try {
            String src = custdir + topName + "/" + midName + "/" + botName + "/" + itemName;
            File dir = new File(src);
            String imgnames[] = dir.list();
            return imgnames;
        }catch (Exception e){
            //("해당경로에 파일이 존재하지 않음");
            return null;
        }
    }
}

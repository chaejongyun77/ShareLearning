package com.fullstack4.sharelearning.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class CommonFileUtil {

    public List<String> fileuploads(MultipartHttpServletRequest files, String uploadFolder) {

        List<String> filenames = new ArrayList<>();
        List<MultipartFile> list = files.getFiles("files");
        if(files.getFile("files").getSize()<=0){
            return null;
        }
        for (MultipartFile file : list) {
            String fileRealName = file.getOriginalFilename();
            long size = file.getSize();
            String fileExt = fileRealName.substring(fileRealName.indexOf("."), fileRealName.length());


         /*   UUID uuid = UUID.randomUUID();
            String[] uuids = uuid.toString().split("-");
            String newName = uuids[0] + fileRealName;*/


            File saveFile = new File(uploadFolder + File.separator + fileRealName);
            try {
                file.transferTo(saveFile);
                filenames.add(fileRealName);
            } catch (IllegalStateException e) {

                e.printStackTrace();
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        return filenames;

    }


}

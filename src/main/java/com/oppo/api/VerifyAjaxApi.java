package com.oppo.api;

import com.oppo.Entity.Sessions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

/**
 * Created by JieChen on 2018/11/19.
 */
@RestController
@RequestMapping(value = "/verify", produces = "application/json")
public class VerifyAjaxApi {
    Logger LOGGER = LoggerFactory.getLogger(VerifyAjaxApi.class);
    @Value("${upload.uploadingdir}")
    String uploadingdir;

    @RequestMapping(value = "/openPreviewModal", method = RequestMethod.POST)
    public File[] openPreviewModal(@RequestBody String id) {
        File file = new File(uploadingdir,id);
        return file.listFiles();
    }
}

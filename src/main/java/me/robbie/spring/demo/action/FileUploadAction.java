package me.robbie.spring.demo.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author:闻西
 * @see: [相关类/方法]
 * @date 2018/12/26 9:37 PM
 * @since [产品/模块版本]
 */
@Controller
public class FileUploadAction {
    public static final Logger logger = LoggerFactory.getLogger(FileUploadAction.class);

    @RequestMapping(value = "/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, method = RequestMethod.POST)
    public ResponseEntity<Void> uploadFile(@RequestPart String description, @RequestPart MultipartFile file) {
        logger.info("description={},file={}",description, file.getOriginalFilename());

        return ResponseEntity.ok(null);
    }

}

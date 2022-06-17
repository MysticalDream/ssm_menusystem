package com.mysticaldream.web.controller;

import com.mysticaldream.common.constant.ProjectVariables;
import com.mysticaldream.web.vo.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @description: UploadController
 * @date: 2022/6/16 0:34
 * @author: MysticalDream
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/upload")
public class UploadController {


    @PostMapping("/menu_img")
    @ResponseBody
    public ApiResult upload(@RequestPart("coverUrl") MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        fileName = UUID.randomUUID().toString().replaceAll("-", "") + fileName;
        File file = new File(ProjectVariables.MENU_IMG_PATH, fileName);
        multipartFile.transferTo(file);
        return ApiResult.success(ProjectVariables.MENU_RESOURCE + "/" + fileName);
    }

}

package com.dh.domestic.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dh.domestic.base.BaseController;

@RestController
@RequestMapping(value = "/upload")
public class UploadController extends BaseController {

	@RequestMapping(value = "/file")
	public String file(MultipartFile file) throws IllegalStateException, IOException {

		// 参考： https://blog.csdn.net/superlover_/article/details/80893007
		file.transferTo(new File("‪‪13.bat"));
		// 可自定义文件路径
		IOUtils.write(file.getBytes(), new FileOutputStream(new File("C:\\Users\\xzzz\\Desktop\\upload\\33.bat")));
		return "success";
	}
}

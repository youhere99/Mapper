package com.dh;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.junit.Test;
import org.springframework.core.io.UrlResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class TestUpload {

	@Test
	public void upload() throws URISyntaxException, MalformedURLException {
		// MultiValueMap<String, Object> multiPartBody = new LinkedMultiValueMap<>();
		// multiPartBody.add("file", new
		// File("‪C:\\Users\\xzzz\\Desktop\\AmlTool.bat"));
		//
		// URI uri = new URI("http://127.0.0.1:8085/workflow/file");
		// RequestEntity<MultiValueMap<String, Object>> requestEntity =
		// RequestEntity.post(uri)
		// .contentType(MediaType.MULTIPART_FORM_DATA).body(multiPartBody);
		// System.err.println(requestEntity.getType().getTypeName());

		// 提交到另一个服务
		// package parameter.
		// FileSystemResource resource = new
		// FileSystemResource("‪C:\\Users\\xzzz\\Desktop\\AmlTool.bat");
		UrlResource resource = new UrlResource("file:C:\\Users\\xzzz\\Desktop\\AmlTool.bat");
		MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("file", resource);

		String remoteaddr = "http://127.0.0.1:8084/upload/file";
		String res = new RestTemplate().postForObject(remoteaddr, multiValueMap, String.class);
	}
}

package com.evnit.ttpm.stdv.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class FileService {

	@Value("${xdocreport.template}")
	private String path;
	@Value("${app.file.upload.url}")
	private String urlFileService;
	@Value("${app.convert.service}")
	private String urlConvert;

	public ResponseData callConvertPdf(Object dataPdfBase64, String bearerToken) {
		ResponseData rsData = null;
		try {

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", bearerToken);
			headers.set("Authorization-type", "Header");
			// headers.set("Org-code", "EVN");
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<Object> requestEntity = new HttpEntity<>(dataPdfBase64, headers);
			ResponseEntity<ResponseData> result = restTemplate.postForEntity(urlConvert + "/v1/convert/word.savepdf",
					requestEntity, ResponseData.class);
			rsData = result.getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsData;
	}

	public ResponseData callConvertExcelToPdf(Object dataPdfBase64, String bearerToken) {
		ResponseData rsData = null;
		try {

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", bearerToken);
			headers.set("Authorization-type", "Header");
			// headers.set("Org-code", "EVN");
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<Object> requestEntity = new HttpEntity<>(dataPdfBase64, headers);
			ResponseEntity<ResponseData> result = restTemplate.postForEntity(urlConvert + "/v1/convert/excel.topdf",
					requestEntity, ResponseData.class);
			rsData = result.getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsData;
	}

	public Object callPostFile(final String fileName, String pathMogodb, byte[] fileByte, String bearerToken)
			throws IOException {
		// HttpHeaders
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.set("Authorization", bearerToken);
		// HttpEntity<String>: To get result as String.
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		ByteArrayResource contentsAsResource = new ByteArrayResource(fileByte) {
			@Override
			public String getFilename() {
				return fileName; // Filename has to be returned in order to be able to post.
			}
		};
		body.add("file", contentsAsResource);
		body.add("path", pathMogodb);
		// RestTemplate
		RestTemplate restTemplate = new RestTemplate();
		// Dữ liệu đính kèm theo yêu cầu.
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
		// Gửi yêu cầu với phương thức POST.
		ResponseEntity<Object> result = restTemplate.postForEntity(urlFileService + "/file/upload", requestEntity,
				Object.class);
		return result.getBody();
	}

	public Object callPostFileSSL(final String fileName, String pathMogodb, byte[] fileByte, String bearerToken)
			throws IOException {
		// HttpHeaders
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.set("Authorization", bearerToken);
		// HttpEntity<String>: To get result as String.
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		ByteArrayResource contentsAsResource = new ByteArrayResource(fileByte) {
			@Override
			public String getFilename() {
				return fileName; // Filename has to be returned in order to be able to post.
			}
		};
		body.add("file", contentsAsResource);
		body.add("path", pathMogodb);
		// RestTemplate
		RestTemplate restTemplate = new RestTemplate();
		// Dữ liệu đính kèm theo yêu cầu.
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
		// Gửi yêu cầu với phương thức POST.
		ResponseEntity<Object> result = restTemplate.postForEntity("https://imistest.evn.com.vn/rpc/utils/file/upload",
				requestEntity, Object.class);
		return result.getBody();
	}

	public byte[] callDataUtilsSSL(String pathMongdb, String bearerToken) {
		try {
			// System.out.println(path + pathMongdb);
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
			headers.set("Authorization", bearerToken);
			RestTemplate restTemplate = new RestTemplate();

			HttpEntity<String> entity = new HttpEntity<>(headers);
			ResponseEntity<byte[]> response = restTemplate.exchange(pathMongdb, HttpMethod.GET, entity, byte[].class);
			return response.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String callDataFile(String pathMongdb, String bearerToken) {
		try {
			int index = pathMongdb.lastIndexOf("/");
			String pathFile = pathMongdb.substring(0, index);
			// System.out.println(path + pathMongdb);
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
			headers.set("Authorization", bearerToken);
			RestTemplate restTemplate = new RestTemplate();

			HttpEntity<String> entity = new HttpEntity<>(headers);
			ResponseEntity<byte[]> response = restTemplate.exchange(urlFileService + "/file/download" + pathMongdb,
					HttpMethod.GET, entity, byte[].class);

			File dirFile = new File(path + pathFile);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			String dirPathFile = path + pathMongdb;
			Path filepath = Paths.get(dirPathFile);
			Files.write(filepath, response.getBody());
			return dirPathFile;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public byte[] callDataUtils(String pathMongdb, String bearerToken) {
		try {
			// System.out.println(path + pathMongdb);
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
			headers.set("Authorization", bearerToken);
			RestTemplate restTemplate = new RestTemplate();

			HttpEntity<String> entity = new HttpEntity<>(headers);
			ResponseEntity<byte[]> response = restTemplate.exchange(urlFileService + "/file/download" + pathMongdb,
					HttpMethod.GET, entity, byte[].class);
			return response.getBody();
		} catch (Exception e) {
			System.out.println("Lỗi gọi service: " + urlFileService + "/file/download" + pathMongdb );
			System.out.println("Chi tiết lỗi: " + e.getMessage());
			return null;
		}
	}

	public Object callDataFileURL(String url, String bearerToken) {
		try {
			// System.out.println(path + pathMongdb);
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
			headers.set("Authorization", bearerToken);
			headers.set("Authorization-type", "Header");
			headers.set("Org-code", "EVN");
			RestTemplate restTemplate = new RestTemplate();

			HttpEntity<String> entity = new HttpEntity<>(headers);
			ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
			return response.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public byte[] callDataFileURL2(String urlFileService, String bearerToken) {
		try {
			// System.out.println(path + pathMongdb);
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
			headers.set("Authorization", bearerToken);
			headers.set("Authorization-type", "Header");
			headers.set("Org-code", "HUB");
			RestTemplate restTemplate = new RestTemplate();

			HttpEntity<String> entity = new HttpEntity<>(headers);
			ResponseEntity<byte[]> response = restTemplate.exchange(urlFileService, HttpMethod.GET, entity,
					byte[].class);
			return response.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateToConvert);
		Date date = calendar.getTime();
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

}

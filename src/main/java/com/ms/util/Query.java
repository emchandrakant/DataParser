package com.ms.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

public class Query {
	
	public String readQueryAsString (String queryName) {
		if(queryName==null || queryName.isEmpty()) {
			throw new IllegalArgumentException("queryName can't be null");
		}
		try(InputStream in = new ClassPathResource(String.format("queries/"+queryName+".sql")).getInputStream()){
			return new String(FileCopyUtils.copyToByteArray(in), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
			return null;
		}
	}
}

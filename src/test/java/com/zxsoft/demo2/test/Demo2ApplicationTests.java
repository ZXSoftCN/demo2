package com.zxsoft.demo2.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo2ApplicationTests {

	@Autowired
	private WebApplicationContext webContext;

	private MockMvc mockMvc;

	@Before
	public void setupMockMvc(){
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void homeTestGet(){
		RequestBuilder request;
		request = get("/testGet/leolee");
		try {
			mockMvc.perform(request)
					.andExpect(status().isOk())
					.andDo(print());
		}catch (Exception ex){
			System.out.print(ex.getMessage());
		}
	}

	@Test
	public void homeTestGet2(){
		RequestBuilder request;
		request = get("/testGet2/leolee");
		try {
			mockMvc.perform(request)
					.andExpect(status().isOk())
					.andDo(print());
		}catch (Exception ex){
			System.out.print(ex.getMessage());
		}
	}

	@Test
	public void homeTestGetEncryt(){

		String requestBody = "{\"userName\":\"TT\"}";
		RequestBuilder request;
		request = post("/encrypt/leolee")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody);

		try {
			mockMvc.perform(request)
					.andExpect(status().isOk())
					.andDo(print());
		}catch (Exception ex){
			System.out.print(ex.getMessage());
		}
	}

	@Test
	public void homeTestGetDecryt(){

		String requestBody = "{\"userName\":\"TT\"}";
		RequestBuilder request;
		request = post("/decrypt/decrypt")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)
				.param("enData","68Zj5OQvBQnqDxxixjCDMk2TFw5grLWnZoZoPuWsxqk=");

		request = post("/decrypt/decrypt2")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content("68Zj5OQvBQnqDxxixjCDMk2TFw5grLWnZoZoPuWsxqk=")//leolee的AES加密后再生成Base64编码
				.param("enData","68Zj5OQvBQnqDxxixjCDMk2TFw5grLWnZoZoPuWsxqk=");
		try {
			mockMvc.perform(request)
					.andExpect(status().isOk())
					.andDo(print());
		}catch (Exception ex){
			System.out.print(ex.getMessage());
		}
	}

	@Test
	public void homeTestDataEncryt(){

		RequestBuilder request;
		request = post("/home/encryptData")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.param("enData","leolee");

		try {
			mockMvc.perform(request)
					.andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.content().string("68Zj5OQvBQnqDxxixjCDMk2TFw5grLWnZoZoPuWsxqk="))
					.andDo(print());
		}catch (Exception ex){
			System.out.print(ex.getMessage());
		}
	}
}

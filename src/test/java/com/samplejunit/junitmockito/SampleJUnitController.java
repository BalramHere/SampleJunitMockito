package com.samplejunit.junitmockito;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samplejunit.junitmockito.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest
class SampleJUnitController {



	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private static MockMvc mockMvc;
	
	@BeforeEach
	public void setup()
	{
		if(mockMvc == null)
		{
			synchronized(SampleJUnitController.class) {
				if(mockMvc == null)
				{
					mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
				}
			}
		}
	}
	
	@Test 
	public void testGetStudent() throws Exception {
		mockMvc.perform(get("/studentapi/getStudentbyId/101"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name").value("Meenakshi"));
	}

	
	@Test
	public void testAddStudent() throws Exception
	{
		Student student = new Student(232,"Gamma","Sai Prasanna","Marvelous");
		ObjectMapper objMap = new ObjectMapper();
		String str= objMap.writeValueAsString(student);
		MvcResult mvcResult = mockMvc.perform(post("/studentapi/addStudent").contentType(MediaType.APPLICATION_JSON).content(str)).andReturn();
		assertEquals(200,mvcResult.getResponse().getStatus());
	}
	

}

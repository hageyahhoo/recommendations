package com.metflix;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.NestedServletException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RecommendationsControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private RestTemplate restTemplate;


	@Test
	public void findRecommendationsForUser_MemberNotFound() throws Exception {

		given(this.restTemplate.exchange(any(RequestEntity.class), eq(Member.class)))
				.willReturn(new ResponseEntity(null, HttpStatus.OK));

		try {
			this.mvc.perform(MockMvcRequestBuilders.post("/api/recommendations/are").accept(MediaType.APPLICATION_JSON));
			fail("想定通りの例外が発生しませんでした。");

		} catch (NestedServletException expected) {
			assertThat(
					expected.getMessage(),
					is("Request processing failed; nested exception is com.metflix.UserNotFoundException"));
			assertTrue(expected.getRootCause() instanceof UserNotFoundException);

		} catch (Throwable unexpected) {
			fail("想定外の例外が発生しました。" + unexpected.getMessage());
		}
	}


	@Test
	public void findRecommendationsForUser_MemberFound_and_Kid() throws Exception {

		given(this.restTemplate.exchange(any(RequestEntity.class), eq(Member.class)))
				.willReturn(new ResponseEntity<Member>(new Member("kore", 16), HttpStatus.OK));

		this.mvc.perform(MockMvcRequestBuilders.post("/api/recommendations/kore").accept(MediaType.APPLICATION_JSON))
				.andExpect(content().string(equalTo("[{\"title\":\"lion king\"},{\"title\":\"frozen\"}]")));
	}


	@Test
	public void findRecommendationsForUser_MemberFound_and_Adult() throws Exception {

		given(this.restTemplate.exchange(any(RequestEntity.class), eq(Member.class)))
				.willReturn(new ResponseEntity<Member>(new Member("sore", 17), HttpStatus.OK));

		this.mvc.perform(MockMvcRequestBuilders.post("/api/recommendations/sore").accept(MediaType.APPLICATION_JSON))
				.andExpect(content().string(equalTo("[{\"title\":\"shawshank redemption\"},{\"title\":\"spring\"}]")));
	}
}

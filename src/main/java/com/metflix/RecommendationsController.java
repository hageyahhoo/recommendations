package com.metflix;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RestController
@RequestMapping("/api/recommendations")
public class RecommendationsController {

	private List<Movie> kidRecommendations    = Arrays.asList(new Movie("lion king"), new Movie("frozen"));
	private List<Movie> adultRecommendations  = Arrays.asList(new Movie("shawshank redemption"), new Movie("spring"));
	private List<Movie> familyRecommendations = Arrays.asList(new Movie("hook"), new Movie("the sandlot"));

	@Autowired
	private RestTemplate restTemplate;

	@Value("${member.api:http://localhost:4444}")
	private URI memberApi;


	@RequestMapping("/{user}")
	public List<Movie> findRecommendationsForUser(@PathVariable String user) throws UserNotFoundException {

		Member member = this.restTemplate.exchange(
				RequestEntity.get(UriComponentsBuilder
								.fromUri(this.memberApi)
								.pathSegment("api", "members", user)
								.build()
								.toUri())
						.build(),
				Member.class)
				.getBody();

		if (member == null) {
			throw new UserNotFoundException();
		}

		return member.getAge() < 17 ? kidRecommendations : adultRecommendations;
	}
}

package com.example.TestEmbdSol.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TestEmbdSol.entity.Reviews;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/review")
public class ReviewController {
	
	List<Reviews> allReviews = getAllReviews();
	List<Reviews> text = getAllSortedByText();
	List<Reviews> rating = getAllSortedByRating();
	
	 @GetMapping("/getAll")
	    public List<Reviews> getAll() {
		 
			return allReviews;	        
	    }
	 
	 @GetMapping("/{id}")
	    public Reviews getReviewById(@PathVariable int id) throws IOException {
		 
	     Optional<Reviews> reviews = allReviews.stream().filter(r -> r.getId() == id).findFirst();
		    return reviews.orElse(null);
	    }
	 
	 @GetMapping("/sortedByText")
	    public List<Reviews> getReviewsSortedByText() {
		 
			return getAllSortedByText(); 
	    }
	 
	 @GetMapping("/sortedByRating")
	    public List<Reviews> getReviewsSortedByRating() {
		 
			return getAllSortedByRating(); 
	    }
	 
	 @GetMapping("/sortedByDate")
	    public List<Reviews> getReviewsSortedByDate() {
		 
			return getAllSortedByDate(); 
	    }
	 
	 public List<Reviews> getAllReviews() {
		 		
	     ObjectMapper mapper = new ObjectMapper();

	        try {
	        	InputStream inputStream = new ClassPathResource("reviews.json").getInputStream();
	            List<Reviews> reviews = mapper.readValue(inputStream, new TypeReference<List<Reviews>>(){});
	            return reviews;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	 
	 public List<Reviews> getAllSortedByText() {
		 
	        return allReviews.stream()
	        		.filter(reviews -> reviews.getReviewText().contains("star"))
	                .sorted(Comparator.comparing(Reviews::getReviewText))
	                .collect(Collectors.toList());
	    }
	 
	 public List<Reviews> getAllSortedByRating() {
		 
	        return text.stream()
	        		.filter(reviews -> reviews.getRating() >= 3)
	        		.sorted((a, b) -> b.getRating() - a.getRating())
	                .collect(Collectors.toList());
	    }
	 
	 public List<Reviews> getAllSortedByDate() {
		 
	        return rating.stream()
	                .sorted(Comparator.comparing(Reviews::getReviewCreatedOnDate))
	                .collect(Collectors.toList());
	    }

}

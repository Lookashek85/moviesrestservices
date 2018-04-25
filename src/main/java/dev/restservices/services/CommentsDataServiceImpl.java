package dev.restservices.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import dev.restservices.models.Comment;

@Service
@CacheConfig(cacheNames="comments")
public class CommentsDataServiceImpl implements CommentsDataService {
	
	static Map<Long, List<Comment>> commentsMap ;
	static{
 
		 Comment comment1 = new Comment(1*10, 1,"Paul", "Best part so far");
		 Comment comment2 = new Comment(2*10, 1,"Mick", "Worst SW movie I've seen");
		 
		 Comment comment3 = new Comment(3*10, 2,"Anna", "My kids loved it!");
		 Comment comment4 = new Comment(4*10, 2,"Peter", "I couldn't stop crying!");
		 
		 List<Comment> commentsMovie1 = new ArrayList<>();
		 commentsMovie1.add(comment1); 
		 commentsMovie1.add(comment2);
		 
		 List<Comment> commentsMovie2 = new ArrayList<>();
		 commentsMovie2.add(comment3); 
		 commentsMovie2.add(comment4);
	     
		 commentsMap = new HashMap<>();
		 commentsMap.put(comment1.getMovieId(), commentsMovie1);
		 commentsMap.put(comment3.getMovieId(), commentsMovie2);
		 
	}
	
 
	@Override
	public Comment addComment(Long movieId, Comment comment) {
		//NO real implementation required..
		return null;
	}

	
	@Override
	@Cacheable(cacheNames="comments")
	public List<Comment> getCommentsForTheMovie(long movieId) {
		 
		return commentsMap.get(movieId);
	}
	
	
 
}

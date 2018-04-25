package dev.restservices.services;

import java.util.List;

import dev.restservices.models.Comment;

public interface CommentsDataService {
	
	public Comment addComment(Long movieId, Comment comment);
	public List<Comment> getCommentsForTheMovie(long movieId);
}

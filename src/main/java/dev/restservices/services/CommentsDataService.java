package dev.restservices.services;

import dev.restservices.models.Comment;

public interface CommentsDataService {
	public Comment addComment(Long movieId, Comment comment);
}

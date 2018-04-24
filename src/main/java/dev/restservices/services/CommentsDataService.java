package dev.restservices.services;

import dev.restservices.models.Comment;
import dev.restservices.models.Movie;

public interface CommentsDataService {
	public void addComment(Movie movie, Comment comment);
}

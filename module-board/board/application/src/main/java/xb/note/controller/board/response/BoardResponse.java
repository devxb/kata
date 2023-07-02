package xb.note.controller.board.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class BoardResponse {

    private final int id;
    private final String title;
    private final String article;
    @JsonProperty("created_at")
    private final LocalDate createdAt;
    private final AuthorResponse author;

    public BoardResponse(int id, String title, String article, LocalDate createdAt, AuthorResponse author) {
        this.id = id;
        this.title = title;
        this.article = article;
        this.createdAt = createdAt;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArticle() {
        return article;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public AuthorResponse getAuthor() {
        return author;
    }

}

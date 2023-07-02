package xb.note.service.board.management.dto;

import java.time.LocalDate;

public class BoardDto {

    private int id;
    private final String title;
    private final String article;
    private final AuthorDto author;

    private LocalDate createdAt;

    public BoardDto(String title, String article, AuthorDto author){
        this.title = title;
        this.article = article;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getArticle() {
        return article;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public static class AuthorDto{
        private final int id;
        private final String name;

        public AuthorDto(int id, String name){
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

}

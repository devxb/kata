package xb.note.domain;

import java.time.LocalDate;

public class Board {
    private final int id;
    private final String title;
    private final String article;
    private final LocalDate createdAt;
    private final User user;

    public Board(int id, String title, String article, User user) {
        this.id = id;
        this.title = title;
        this.article = article;
        createdAt = LocalDate.now();
        this.user = user;
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

    public User getUser() {
        return user;
    }
}

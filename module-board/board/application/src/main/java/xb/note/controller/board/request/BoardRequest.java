package xb.note.controller.board.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BoardRequest {

    @NotEmpty
    private String title;
    @NotEmpty
    private String article;
    @NotNull
    private AuthorRequest author;

    public BoardRequest(String title, String article, AuthorRequest author){
        this.title = title;
        this.article = article;
        this.author = author;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setArticle(String article){
        this.article = article;
    }

    public void setAuthor(AuthorRequest author){
        this.author = author;
    }

    public String getTitle(){
        return this.title;
    }

    public String getArticle(){
        return this.article;
    }

    public AuthorRequest getAuthor(){
        return this.author;
    }

}

package applecare.com.applecare.Fragment;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Question  implements Serializable {
    private int id;
    private String description;
    private String author;
    private String photo;
    private String thumbnail;
    private boolean isAnswered;
    private String title;
    @SerializedName("answer_count")
    private int answerCount;

    public Question(int id, String description, String author, String photo, String thumbnails, boolean isAnswered, String title, int answerCount) {
        this.id = id;
        this.description = description;
        this.author = author;
        this.photo = photo;
        this.thumbnail = thumbnails;
        this.isAnswered = isAnswered;
        this.title = title;
        this.answerCount = answerCount;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getPhoto() {
        return photo;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public String getTitle() {
        return title;
    }

    public int getAnswerCount() {
        return answerCount;
    }
}





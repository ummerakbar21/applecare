package applecare.com.applecare.Model;

import java.io.Serializable;

/**
 * Created by shabir on 03-03-2018.
 */

public class QuestionHistory implements Serializable {
    private String title;
    private Integer faqId;
    private String prevalenceTime;
    private String drawable;
    private  String localName;
    private String description;
    private String author;
    private String photo;
    private boolean isAnswered;

    public QuestionHistory() {

    }

    public String getDrawable() {
        return drawable;
    }

    public void setDrawable(String drawable) {
        this.drawable = drawable;
    }

    public QuestionHistory(Integer faqId, String title, String prevalenceTime, String drawable, String localName) {
        this.faqId=faqId;
        this.title=title;
        this.prevalenceTime=prevalenceTime;
        this.drawable=drawable;
        this.localName = localName;
    }

    public Integer getFaqId() {
        return faqId;
    }

    public void setFaqId(Integer faqId) {
        this.faqId = faqId;
    }

    public String getPrevalenceTime() {
        return prevalenceTime;
    }

    public void setPrevalenceTime(String prevalenceTime) {
        this.prevalenceTime = prevalenceTime;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

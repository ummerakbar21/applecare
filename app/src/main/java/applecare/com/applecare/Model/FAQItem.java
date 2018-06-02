package applecare.com.applecare.Model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by shabir on 02-03-2018.
 */

public class FAQItem implements Serializable{
    private Integer faqId;
    private String diseaseName;
    private String prevalenceTime;
    private List<Symptom> symptoms;
    private List<Recommendation> recommendations;
    private Drawable drawable;

    public FAQItem(Integer faqId,String diseaseName,String prevalenceTime,Drawable drawable) {
        this.faqId=faqId;
        this.diseaseName=diseaseName;
        this.prevalenceTime=prevalenceTime;
        this.drawable=drawable;
    }


    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public Integer getFaqId() {
        return faqId;
    }

    public void setFaqId(Integer faqId) {
        this.faqId = faqId;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getPrevalenceTime() {
        return prevalenceTime;
    }

    public void setPrevalenceTime(String prevalenceTime) {
        prevalenceTime = prevalenceTime;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }
}

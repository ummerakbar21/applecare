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
    private String drawable;
    private  String localName;
    private String symptom;
    private String recommendation;

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public FAQItem(String diseaseName, String symptom, String recommendation) {
        this.diseaseName = diseaseName;
        this.symptom = symptom;
        this.recommendation = recommendation;
    }

    public FAQItem() {
    }

    public FAQItem(Integer faqId, String diseaseName, String prevalenceTime, String drawable, String localName) {
        this.faqId=faqId;
        this.diseaseName=diseaseName;
        this.prevalenceTime=prevalenceTime;
        this.drawable=drawable;
        this.localName = localName;
    }


    public String getDrawable() {
        return drawable;
    }

    public void setDrawable(String drawable) {
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

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }
}

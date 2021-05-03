package applecare.com.applecare.Model;

import com.google.gson.annotations.SerializedName;

public class Answer {
    private int id;
    @SerializedName("disease_name")
    private String diseaseName;
    private String symptoms;
    private String recommendation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public String getRecommendation() {
        return recommendation;
    }

    @SerializedName("added_on")


    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
}

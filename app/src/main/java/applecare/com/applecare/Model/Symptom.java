package applecare.com.applecare.Model;

import java.io.Serializable;

/**
 * Created by shabir on 02-06-2018.
 */

class Symptom implements Serializable{
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

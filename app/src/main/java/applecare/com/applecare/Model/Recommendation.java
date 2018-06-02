package applecare.com.applecare.Model;

import java.io.Serializable;

/**
 * Created by shabir on 02-06-2018.
 */

class Recommendation implements Serializable{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

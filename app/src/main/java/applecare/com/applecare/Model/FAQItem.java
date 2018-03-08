package applecare.com.applecare.Model;

/**
 * Created by shabir on 02-03-2018.
 */

public class FAQItem {
    private String title;


    public FAQItem() {

    }

    public FAQItem( String title) {
        this.title = title;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

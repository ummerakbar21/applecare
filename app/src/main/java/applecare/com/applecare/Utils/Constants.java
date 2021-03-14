package applecare.com.applecare.Utils;

import applecare.com.applecare.Model.User;

/**
 * Created by shabir on 05-03-2018.
 */

public class Constants {

    public static final String		REGEXP_EMAIL = "(\\w[-._\\w]*\\w@\\w[-._\\w]*\\w\\.\\w{2,3})";
    public static final String		REGEXP_PHONE = "^[0-9]{9,13}$";
    public static final long DELAY = 2000;
    public static User currentUser = null;
}

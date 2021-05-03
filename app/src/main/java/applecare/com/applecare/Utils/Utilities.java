package applecare.com.applecare.Utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ummer on 8/6/18.
 */

public   class Utilities {

    public static int getDrawableId(Context context, String idName) throws Exception {
        // throw new ArithmeticException("/ by zero");
        // String id = idName + "_" + siteId;
        int resID = context.getResources().getIdentifier(idName, "drawable", context.getPackageName());
        return resID;
    }

    public  static int getStringId(Context context, String titleName, String category) throws  Exception{
        // throw new ArithmeticException("/ by zero");
        String id =titleName+"_"+category;
        int resID = context.getResources().getIdentifier(id ,  "string", context.getPackageName());
        return resID;
    }
    public static Drawable getImageFromDrawable( Context context, String imageName) {
        String uri = "@drawable/" + imageName;
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
        Drawable drawable = context.getResources().getDrawable(imageResource);
        return drawable;
    }
    public static String getJsonFromAssets(Context context, String fileName) {
        String jsonString;
        try {
            InputStream is = context.getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return jsonString;
    }
}

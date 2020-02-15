package applecare.com.applecare.Fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import applecare.com.applecare.Model.FAQItem;
import applecare.com.applecare.R;
import applecare.com.applecare.Utils.Utilities;

/**
 * Created by ummer on 27/5/18.
 */

public class DataFragment extends Fragment {
    public static final String ARG_OBJECT = "Symptoms";
    HtmlTextView bodyView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.content_detail, container, false);

        Bundle args = getArguments();
        final FAQItem selectedItem = (FAQItem)args.getSerializable("selectedItem");
        String tabSelected= args.getString("tabSelected");
        ((TextView) rootView.findViewById(R.id.item_tile_view)).setText(
                Integer.toString(args.getInt(ARG_OBJECT)));
        bodyView = (HtmlTextView) rootView.findViewById(R.id.body_view);
        try {
            String dataString= getString(Utilities.getStringId(getActivity(),selectedItem.getLocalName(),tabSelected));
            bodyView.setHtml(dataString);
        } catch (Exception e) {
            e.printStackTrace();
            bodyView.setHtml("No content available");

        }
        return rootView;
    }
}
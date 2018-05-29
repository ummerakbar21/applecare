package applecare.com.applecare.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import applecare.com.applecare.R;

/**
 * Created by ummer on 27/5/18.
 */

public class DataFragment extends Fragment {
    public static final String ARG_OBJECT = "Symptoms";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.content_detail, container, false);

        Bundle args = getArguments();
        ((TextView) rootView.findViewById(R.id.item_tile_view)).setText(
                Integer.toString(args.getInt(ARG_OBJECT)));
        return rootView;
    }
}
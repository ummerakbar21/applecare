package applecare.com.applecare.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import applecare.com.applecare.Fragment.DataFragment;
import applecare.com.applecare.Model.FAQItem;

/**
 * Created by ummer on 27/5/18.
 */

public class FragmentPagerAdapter extends FragmentStatePagerAdapter {
    FAQItem selectedItem;
    public FragmentPagerAdapter(FragmentManager fm, FAQItem selectedItem) {
        super(fm);
        this.selectedItem= selectedItem;

    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new DataFragment();
        Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt(DataFragment.ARG_OBJECT,4);
        args.putSerializable("selectedItem",selectedItem);
        args.putString("tabSelected",tabSelected(i));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String tabName="Symptoms";
        switch (position){
            case 1:
                tabName ="Recommendation";
                break;
            case 2:
                tabName= "Gallery";
                break;
        }
        return tabName;
    }
    private String tabSelected( int position){
        String tabName="symptoms";
        switch (position){
            case 1:
                tabName ="recommendation";
                break;
            case 2:
                tabName= "gallery";
                break;
        }
        return tabName;
    }


}

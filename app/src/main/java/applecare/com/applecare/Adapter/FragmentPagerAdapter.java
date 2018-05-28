package applecare.com.applecare.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import applecare.com.applecare.Fragment.DemoFragment;

/**
 * Created by ummer on 27/5/18.
 */

public class FragmentPagerAdapter extends FragmentStatePagerAdapter {
    public FragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new DemoFragment();
        Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt(DemoFragment.ARG_OBJECT,4);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }
}

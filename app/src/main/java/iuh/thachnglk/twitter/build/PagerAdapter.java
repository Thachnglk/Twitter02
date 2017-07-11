package iuh.thachnglk.twitter.build;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import iuh.thachnglk.twitter.view.HomeFragment;
import iuh.thachnglk.twitter.view.MentionFragment;


public class PagerAdapter extends FragmentStatePagerAdapter {

    HomeFragment fragment;
    MentionFragment fragment_other;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public Fragment getHomeFragment(){
        return fragment;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                fragment=new HomeFragment();
                break;
            case 1:
                fragment_other=new MentionFragment();
                return fragment_other;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title=" ";
        switch (position){
            case 0:
                title="Home";
                break;
            case 1:
                title="Mentions";
                break;
        }

        return title;
    }

}

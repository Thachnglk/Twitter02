package iuh.thachnglk.twitter.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import iuh.thachnglk.twitter.R;
import iuh.thachnglk.twitter.TwitterModel;
import iuh.thachnglk.twitter.build.PagerAdapter;

public class MainActivity extends AppCompatActivity implements PostDialogFragment.MyListener  {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind(R.id.img_collapsing)
    ImageView imgCollapsing;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        combineTabLayoutWithViewPager();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                PostDialogFragment fragment = PostDialogFragment.newInstance("");
                fragment.show(fm, "fragment_post");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void combineTabLayoutWithViewPager() {
        FragmentManager manager=getSupportFragmentManager();
        adapter=new PagerAdapter(manager);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapter);

        tabLayout.getTabAt(0).setIcon(R.drawable.buildings);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_hearing_white_36dp);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos =tab.getPosition();
                viewPager.setCurrentItem(pos);
                if (pos==0)
                    fab.setVisibility(View.VISIBLE);
                else
                    fab.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_profile) {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onUpdateList(JSONObject object, String status) {
        if (viewPager.getCurrentItem()==0) {
            HomeFragment homeFragment = (HomeFragment) adapter.getHomeFragment();
            homeFragment.updateView(TwitterModel.getTwitterModel(object, status));
        }
    }
}

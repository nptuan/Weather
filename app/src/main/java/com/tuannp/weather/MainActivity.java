package com.tuannp.weather;

import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.tuannp.weather.adapter.PagerAdapter;
import com.tuannp.weather.databinding.ActivityMainBinding;
import com.tuannp.weather.service.RetrofitInterface;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding bindings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindings = DataBindingUtil.setContentView(this, R.layout.activity_main);

    setSupportActionBar(bindings.toolbar);
    getSupportActionBar().show();

    bindings.tabLayout.addTab(bindings.tabLayout.newTab().setText("Tab 1"));
    bindings.tabLayout.addTab(bindings.tabLayout.newTab().setText("Tab 2"));
    bindings.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

    final PagerAdapter adapter = new PagerAdapter
            (getSupportFragmentManager(), bindings.tabLayout.getTabCount());
        bindings.viewPager.setAdapter(adapter);
        bindings.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(bindings.tabLayout));
        bindings.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            bindings.viewPager.setCurrentItem(tab.getPosition());



        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    });
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

}

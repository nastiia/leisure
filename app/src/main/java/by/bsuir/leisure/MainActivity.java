package by.bsuir.leisure;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VKSdk.login(this, VKScope.EMAIL, VKScope.NOHTTPS);
    }

    public void init() {
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Категории");
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                drawerLayout.closeDrawers();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (menuItem.getItemId()) {
                    case R.id.categories:
                        CategoriesFragment fragment = new CategoriesFragment();
                        fragmentTransaction.replace(R.id.frame, fragment);
                        fragmentTransaction.commit();
                        return true;
                    case R.id.map:
                        MapFragment mapFragment = new MapFragment();
                        fragmentTransaction.replace(R.id.frame, mapFragment);
                        fragmentTransaction.commit();
                        return true;
                    case R.id.search:
//                        MapFragment mapFragment = new MapFragment();
//                        fragmentTransaction.replace(R.id.frame, mapFragment);
//                        fragmentTransaction.commit();
                        return true;
                    case R.id.profile:
//                        MapFragment mapFragment = new MapFragment();
//                        fragmentTransaction.replace(R.id.frame, mapFragment);
//                        fragmentTransaction.commit();
                        return true;

                    default:
                        Toast.makeText(getApplicationContext(), "Something's wrong", Toast.LENGTH_SHORT).show();
                        return true;
                }
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(final VKAccessToken res) {
                VKParameters params = new VKParameters();
                setContentView(R.layout.drawer_header);
                params.put(VKApiConst.FIELDS, "photo_max_orig");

                VKRequest request = new VKRequest("users.get", params);
                request.executeWithListener(new VKRequest.VKRequestListener() {

                    @Override
                    public void onComplete(VKResponse response) {
                        super.onComplete(response);
                        JSONArray resp = null;
                        try {
                            resp = response.json.getJSONArray("response");
                            JSONObject user = null;
                            user = resp.getJSONObject(0);
                            String avatar = user.getString("photo_max_orig");
                            CircleImageView imgView = (CircleImageView) findViewById(R.id.avatar);
                            Picasso.with(getApplicationContext()).load(avatar)
                                    .placeholder(R.drawable.ic_launcher).error(R.drawable.ic_launcher)
                                    .into(imgView);
                            String name = user.getString("first_name") + " " + user.getString("last_name");
                            TextView textView = (TextView) findViewById(R.id.username);
                            textView.setText(name);
                            TextView textView2 = (TextView) findViewById(R.id.email);
                            textView2.setText(res.email);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(VKError error) {
                        super.onError(error);
                    }
                });
                init();
//                makeRequest(res.userId);
            }

            @Override
            public void onError(VKError error) {
                finish();
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
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
//        return super.onOptionsItemSelected(item);
//    }
}

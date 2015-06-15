package hk.edu.cityu.appslab.caladvancedweatherapp;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends AppCompatActivity implements DrawerFragment.NavigationDrawerCallbacks {

    // for example
    TextView xml;


    // Data Source
    List<Weather> data;

    // Adapter
    WeatherAdapter weatherAdapter;

    private ListView weatherList;
    private DrawerLayout drawerLayout;
    private DrawerFragment drawerFragment;
    private String[] woeidArray;
    private WeatherFragment weatherFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        woeidArray = getResources().getStringArray(R.array.woeid);


        woeidArray = new String[]{"2165352", "2151330", "2306179", "44418", "615702", "2459115"};

        drawerFragment = (DrawerFragment)
                getFragmentManager().findFragmentById(R.id.drawer_fragment);

        onNavigationDrawerItemSelected(0);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement
        int id = item.getItemId();
        if (id == R.id.action_refresh){

            weatherFragment.startDownloadWeather();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void setUpDrawer() {
        View layout = findViewById(R.id.drawer_layout);

        drawerLayout = (DrawerLayout) layout;

        drawerFragment.setUp(
                R.id.drawer_fragment,
                drawerLayout);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        YWeatherAPI.WOEID = woeidArray[position];
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
		
        weatherFragment = new WeatherFragment();
        fragmentTransaction.replace(R.id.weather_fragment_container, weatherFragment);
        fragmentTransaction.commit();
    }

}

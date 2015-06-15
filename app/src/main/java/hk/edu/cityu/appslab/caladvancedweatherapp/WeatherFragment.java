package hk.edu.cityu.appslab.caladvancedweatherapp;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

public class WeatherFragment extends Fragment {

    // Data Source
    List<Weather> data;

    // Adapter
    WeatherAdapter weatherAdapter;

    private ListView weatherList;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather_list, container, false);
        weatherList = (ListView) rootView.findViewById(R.id.weather_list);
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        weatherList.setEmptyView(rootView.findViewById(R.id.empty_view));
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);

        ViewCompat.setElevation(toolbar, getResources().getDimension(R.dimen.elevation));
        startDownloadWeather();

        ((MainActivity) getActivity()).setUpDrawer();

        swipeRefreshLayout.setColorSchemeResources(R.color.accent_dark, R.color.accent, R.color.accent_light);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                startDownloadWeather();
            }
        });

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });


        return rootView;
    }

    public void startDownloadWeather() {
        swipeRefreshLayout.setRefreshing(true);
        new WeatherQueryTask().execute();
    }


    private class WeatherQueryTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            swipeRefreshLayout.setRefreshing(true);
            weatherList.setAdapter(null);
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            // 1. Populate the Data Source
            String xml = YWeatherAPI.getForecastXml();

            // 1.1 Convert the xml to List of Weather Object
            try {
                WeatherParser parser = new WeatherParser(xml);
                data = parser.getWeatherForecastList();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // 2. Fill the adapter with data
            weatherAdapter = new WeatherAdapter(data);

            // 3. Setup the ListView
            weatherList.setAdapter(weatherAdapter);
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}

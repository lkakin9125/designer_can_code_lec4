package hk.edu.cityu.appslab.caladvancedweatherapp;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class WeatherAdapter extends BaseAdapter {

    private List<Weather> weatherList;

    public WeatherAdapter(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return weatherList.size();
    }

    @Override
    public Weather getItem(int position) {
        // TODO Auto-generated method stub
        return weatherList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        //1. create a new ViewHolder
        WeatherHolder holder = new WeatherHolder();
        //2. init ViewHolder
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            v = inflater.inflate(R.layout.item_weather, parent, false);

            holder.date = (TextView) v.findViewById(R.id.date);
            holder.day = (TextView) v.findViewById(R.id.day);
            holder.high = (TextView) v.findViewById(R.id.high);
            holder.text = (TextView) v.findViewById(R.id.text);
            holder.icon = (ImageView) v.findViewById(R.id.icon);

            v.setTag(holder);

        } else {
            holder = (WeatherHolder) v.getTag();
        }
        //3. update content
        Weather weather = weatherList.get(position);
        holder.date.setText(weather.getDate());
        holder.day.setText(weather.getDay());
        holder.high.setText(String.valueOf(weather.getHigh()) + (char) 0x00B0);
        holder.text.setText(weather.getText());
        if (weather.getIcon() == R.drawable.sunny) {
            AnimationDrawable drawable = (AnimationDrawable) parent.getResources().getDrawable(R.drawable.sunny_anim_drawable);
            holder.icon.setImageDrawable(drawable);
            drawable.start();
        } else
            holder.icon.setImageResource(weather.getIcon());

        //4. return the view
        return v;
    }

    static class WeatherHolder {

        TextView day;
        TextView date;
        TextView high;
        TextView text;
        ImageView icon;

    }

}

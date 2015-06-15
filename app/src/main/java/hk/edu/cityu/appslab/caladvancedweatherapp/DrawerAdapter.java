package hk.edu.cityu.appslab.caladvancedweatherapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerAdapter extends BaseAdapter {
    String[] drawerText;
    int[] fragId;
    DrawerFragment drawerFragment;

    public DrawerAdapter(String[] drawerText, DrawerFragment drawerFragment) {
        this.drawerText = drawerText;


        this.fragId = new int[]{R.drawable.hong_kong_round_icon_256, R.drawable.china_round_icon_256, R.drawable.republic_of_china_round_icon_256,
                R.drawable.england_round_icon_256, R.drawable.france_round_icon_256, R.drawable.united_states_of_america_round_icon_256};
        this.drawerFragment = drawerFragment;
    }

    @Override
    public int getCount() {
        return drawerText.length;
    }

    @Override
    public Object getItem(int position) {
        return fragId[position];
    }

    @Override
    public long getItemId(int position) {
        return fragId[position];
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drawer_list, parent, false);
            holder.drawerTextView = (TextView) convertView.findViewById(R.id.drawer_text);
            holder.drawerImageView = (ImageView) convertView.findViewById(R.id.flag_img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.drawerImageView.setImageResource(fragId[position]);
        holder.drawerTextView.setText(drawerText[position]);
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerFragment.selectItem(position);
//            }
//        });
        return convertView;
    }

    private class ViewHolder {
        TextView drawerTextView;
        ImageView drawerImageView;
    }
}

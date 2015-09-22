package com.example.glamantech.samplemap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Naresh on 9/17/2015.
 */
public class ATMListAdapter extends BaseAdapter {

    List<ATMLocationDetails> list;
    ATMListActivity activity;

    public ATMListAdapter(ATMListActivity activity, List<ATMLocationDetails> list ){

        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater mInflater;
        if(convertView == null) {
            mInflater = LayoutInflater.from(activity);
            convertView = mInflater.inflate(R.layout.listrow, null);
            holder = new ViewHolder();
            holder.locationtype = (TextView)convertView.findViewById(R.id.locationType);
            holder.name = (TextView)convertView.findViewById(R.id.name);
            holder.distance = (TextView)convertView.findViewById(R.id.distance);
            holder.address = (TextView)convertView.findViewById(R.id.address);

            convertView.setTag(holder);
        }else {

            holder = (ViewHolder)convertView.getTag();
        }

        holder.address.setText(list.get(position).getAddress());
        holder.name.setText(list.get(position).getName());
        holder.locationtype.setText(list.get(position).getLocationType());
        holder.distance.setText(""+list.get(position).getDistance()+" mi");
        return convertView;
    }

    class ViewHolder {

        TextView locationtype;
        TextView distance;
        TextView name;
        TextView address;

    }

}

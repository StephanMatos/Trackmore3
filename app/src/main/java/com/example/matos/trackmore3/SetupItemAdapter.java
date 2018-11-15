package com.example.matos.trackmore3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SetupItemAdapter extends BaseAdapter {


    private LayoutInflater mInflater;
    private ArrayList<JSONObject> JSONS;
    private Context c;

    public SetupItemAdapter(Context c, ArrayList<JSONObject> JSONS ) {

        this.c = c;
        this.JSONS = JSONS;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        return JSONS.size();
    }

    @Override
    public Object getItem(int position) {

        return JSONS.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        JSONObject json = JSONS.get(position);

        View v = mInflater.inflate(R.layout.setup_device_view, null);

        TextView name = v.findViewById(R.id.name);
        TextView code = v.findViewById(R.id.code);
        TextView pincode = v.findViewById(R.id.pincode);
        ImageView markercolor = v.findViewById(R.id.markercolor);
        ImageView edit = v.findViewById(R.id.edit);
        ImageView delete = v.findViewById(R.id.delete);

        try {
            name.setText(json.getString("name"));
            pincode.setText(json.getString("pincode"));
            code.setText(json.getString("code"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return v;
    }
}

package com.example.matos.trackmore3;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SetupItemAdapter extends BaseAdapter {


    private LayoutInflater mInflater;
    private ArrayList<JSONObject> JSONS;
    private Context c;
    Dialog editdialog;
    private View lastEdited;
    private String lastMarkerColor, lastName, verificationCode, markerColorString;


    public SetupItemAdapter(Context c, ArrayList<JSONObject> JSONS ) {

        this.c = c;
        this.JSONS = JSONS;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        editdialog = new Dialog(c);
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        JSONObject json = JSONS.get(position);

        View v = mInflater.inflate(R.layout.setup_device_view, null);

        final TextView name = v.findViewById(R.id.name);
        TextView code = v.findViewById(R.id.code);
        TextView pincode = v.findViewById(R.id.pincode);
        ImageView markercolor = v.findViewById(R.id.markercolor);
        ImageView edit = v.findViewById(R.id.edit);
        ImageView delete = v.findViewById(R.id.delete);

        try {
            name.setText(json.getString("name"));
            pincode.setText(json.getString("pincode"));
            code.setText(json.getString("code"));
            markerColorString = json.getString("markercolor");
            verificationCode = code.getText().toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("MARKER COLOR " + markerColorString);
        if (markerColorString.equals("red") ) {
            System.out.println("INSIDE IF");
            markercolor.setImageResource(R.drawable.red_marker);
        }else if (markerColorString.equals("yellow")){
            markercolor.setImageResource(R.drawable.yellow_marker);
        }else if (markerColorString.equals("green")){
            markercolor.setImageResource(R.drawable.green_marker);
        }else if (markerColorString.equals("blue")){
            markercolor.setImageResource(R.drawable.blue_marker);
        }else {
            markercolor.setImageResource(R.drawable.purple_marker);
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edit_name = name.getText().toString();
                EditPopup(edit_name);
                lastEdited = v;
            }
        });


        return v;
    }

    public void EditPopup (final String name) {
        editdialog.setContentView(R.layout.edit_popup_setup);
        editdialog.show();

        final TextView edit_name = editdialog.findViewById(R.id.editName);
        final ImageButton red_marker_edit = editdialog.findViewById(R.id.red_marker_edit);
        final ImageButton yellow_marker_edit = editdialog.findViewById(R.id.yellow_marker_edit);
        final ImageButton green_marker_edit = editdialog.findViewById(R.id.green_marker_edit);
        final ImageButton blue_marker_edit = editdialog.findViewById(R.id.blue_marker_edit);
        final ImageButton purple_marker_edit = editdialog.findViewById(R.id.purple_marker_edit);
        final Button apply_edit = editdialog.findViewById(R.id.applybutton);

        edit_name.setText(name);


       red_marker_edit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               red_marker_edit.setBackgroundColor(Color.parseColor("#808080"));
               yellow_marker_edit.setBackgroundColor(Color.parseColor("#80FFFFFF"));
               green_marker_edit.setBackgroundColor(Color.parseColor("#80FFFFFF"));
               blue_marker_edit.setBackgroundColor(Color.parseColor("#80FFFFFF"));
               purple_marker_edit.setBackgroundColor(Color.parseColor("#80FFFFFF"));
               lastMarkerColor = "red";
           }

       });

        yellow_marker_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                red_marker_edit.setBackgroundColor(Color.parseColor("#80FFFFFF"));
                yellow_marker_edit.setBackgroundColor(Color.parseColor("#808080"));
                green_marker_edit.setBackgroundColor(Color.parseColor("#80FFFFFF"));
                blue_marker_edit.setBackgroundColor(Color.parseColor("#80FFFFFF"));
                purple_marker_edit.setBackgroundColor(Color.parseColor("#80FFFFFF"));
                lastMarkerColor = "yellow";
            }
        });

        green_marker_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                red_marker_edit.setBackgroundColor(Color.parseColor("#80FFFFFF"));
                yellow_marker_edit.setBackgroundColor(Color.parseColor("#80FFFFFF"));
                green_marker_edit.setBackgroundColor(Color.parseColor("#808080"));
                blue_marker_edit.setBackgroundColor(Color.parseColor("#80FFFFFF"));
                purple_marker_edit.setBackgroundColor(Color.parseColor("#80FFFFFF"));
                lastMarkerColor = "green";
            }
        });

        blue_marker_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                red_marker_edit.setBackgroundColor(Color.parseColor("#80FFFFFF"));
                yellow_marker_edit.setBackgroundColor(Color.parseColor("#80FFFFFF"));
                green_marker_edit.setBackgroundColor(Color.parseColor("#80FFFFFF"));
                blue_marker_edit.setBackgroundColor(Color.parseColor("#808080"));
                purple_marker_edit.setBackgroundColor(Color.parseColor("#80FFFFFF"));
                lastMarkerColor = "blue";
            }
        });

        purple_marker_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                red_marker_edit.setBackgroundColor(Color.parseColor("#80FFFFFF"));
                yellow_marker_edit.setBackgroundColor(Color.parseColor("#80FFFFFF"));
                green_marker_edit.setBackgroundColor(Color.parseColor("#80FFFFFF"));
                blue_marker_edit.setBackgroundColor(Color.parseColor("#80FFFFFF"));
                purple_marker_edit.setBackgroundColor(Color.parseColor("#808080"));
                lastMarkerColor = "purple";
            }
        });


        apply_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lastName = edit_name.getText().toString();
                SaveEdit();
                editdialog.dismiss();
                Intent refresh = new Intent(c, SetupDevices.class);
                c.startActivity(refresh);
            }
        });


    }


    public void SaveEdit() {

        JSONObject json = new JSONObject();
        try {
            json.put("name", lastName);
            json.put("code", verificationCode);
            json.put("markercolor", lastMarkerColor);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Services.override(c, json);


    }




}

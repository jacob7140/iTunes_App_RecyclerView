package com.example.itunes_app_cardview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AppListAdapter extends ArrayAdapter<DataServices.App> {
    public AppListAdapter(@NonNull Context context, int resource, @NonNull List<DataServices.App> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_row_item, parent, false);
        }

        DataServices.App app = getItem(position);

        TextView appName = convertView.findViewById(R.id.appName);
        TextView artistName = convertView.findViewById(R.id.artistName);
        TextView releaseDate = convertView.findViewById(R.id.releaseDate);

        appName.setText(app.name);
        artistName.setText(app.artistName);
        releaseDate.setText(app.releaseDate);

        return convertView;
    }
}



package com.example.moody;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class TimelineFragment extends Fragment {

    LineGraphSeries<DataPoint> series;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timeline_fragment, container, false);

        double y, x;
        x = 0;
        GraphView graph = (GraphView) view.findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();
        for(int i = 0; i<50; i++){
            x =  x+ 0.1;
            y = Math.sin(x);
            series.appendData(new DataPoint(x, y), true, 50);
        }
        graph.addSeries(series);

        series.setColor(Color.GREEN);
        series.setDrawBackground(true);
        series.setBackgroundColor(Color.rgb(201,230, 225));
        series.setDrawDataPoints(true);
        return view;
    }
}
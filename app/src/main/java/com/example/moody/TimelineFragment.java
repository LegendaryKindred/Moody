
package com.example.moody;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TimelineFragment extends Fragment {

    LineGraphSeries<DataPoint> series;
    TextView message;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timeline_fragment, container, false);
        message = view.findViewById(R.id.timeline_text);


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String Uid = user.getUid();

        ref.child(Uid).child("mood").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String mood = String.valueOf(task.getResult().getValue());
                if(mood.equals("1")){
                    message.setText("You can go to a psychological help for your problem.");
                }
                if(mood.equals("2")){
                    message.setText("Don't be so sad, try to reach out to the advisor about your issue.");
                }
                if(mood.equals("3")){
                    message.setText("Feeling neutral? Try to talk to your friends/families about your day.");
                }
                if(mood.equals("4")){
                    message.setText("You are getting there! Try to do some light exercises, it will help reduce your stress.");
                }
                if(mood.equals("5")){
                    message.setText("Happy to hear that! Keeps up with the mood!");
                }
            }
        });
        List<Report> el = new ArrayList<>();
        ref.child(Uid).child("emotion").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot dataSnapshot1 = task.getResult();
                for(DataSnapshot data : dataSnapshot1.getChildren()){
                    Report report = data.getValue(Report.class);
                    el.add(report);
                }

                double x = 0;
                GraphView graph = (GraphView) view.findViewById(R.id.graph);
                series = new LineGraphSeries<DataPoint>();
                for (Report r: el) {
                    x += 1;
                    double y = r.getMood();
                    series.appendData(new DataPoint(x, y), true, 50);
                }

                graph.addSeries(series);
                graph.getViewport().setXAxisBoundsManual(true);
                graph.getViewport().setMinX(1);
                graph.getViewport().setMaxX(el.size() + 1);

                graph.getViewport().setYAxisBoundsManual(true);
                graph.getViewport().setMinY(1);
                graph.getViewport().setMaxY(5);

                graph.getViewport().setScalable(true);

                series.setDrawDataPoints(true);
                series.setDataPointsRadius(20);
                series.setThickness(14);
                series.setColor(Color.rgb(153,51,255));
                series.setDrawBackground(true);
                series.setBackgroundColor(Color.rgb(201,230, 225));
                series.setDrawDataPoints(true);

            }
        });


        return view;
    }
}
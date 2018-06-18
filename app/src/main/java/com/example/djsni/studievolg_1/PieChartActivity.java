package com.example.djsni.studievolg_1;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;
import java.util.Map;



public class PieChartActivity extends AppCompatActivity {

    private PieChart mChart;
    public static final int MAX_ECTS = 60;
    public static int currentEcts = 0;
    final MyDBHandler myDBHandler = new MyDBHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        mChart = (PieChart) findViewById(R.id.chart);
        mChart.setDescription("");
        mChart.setTouchEnabled(false);
        mChart.setDrawSliceText(true);
        mChart.getLegend().setEnabled(true);
        mChart.setTransparentCircleColor(Color.rgb(130, 130, 130));
        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        setData(0);
        Bundle b = getIntent().getExtras();
        int studiepunt =  myDBHandler.telEC();
        if (currentEcts < MAX_ECTS) {
            setData(currentEcts += studiepunt );
           System.out.println("geertwilders" );
        }
        if (currentEcts > MAX_ECTS){
            setDataBehaald(currentEcts += studiepunt );
            System.out.println( );
        }

    }

    private void setData(int aantal) {
        currentEcts = aantal;
        ArrayList<Entry> yValues = new ArrayList<>();
        ArrayList<String> xValues = new ArrayList<>();

        yValues.add(new Entry(aantal, 0));
        xValues.add("Behaalde ECTS");

        yValues.add(new Entry(60 - currentEcts, 1));
        xValues.add("Resterende ECTS");

        //  http://www.materialui.co/colors
        ArrayList<Integer> colors = new ArrayList<>();
        if (currentEcts <10) {
            colors.add(Color.rgb(244,81,30));
        } else if (currentEcts < 40){
            colors.add(Color.rgb(235,0,0));
        } else if  (currentEcts < 50) {
            colors.add(Color.rgb(253, 216, 53));
        } else {
            colors.add(Color.rgb(67,160,71));
        }
        colors.add(Color.rgb(255,0,0));

        PieDataSet dataSet = new PieDataSet(yValues, "");
        dataSet.setColors(colors);

        PieData data = new PieData(xValues, dataSet);
        mChart.setData(data); // bind dataset aan chart.
        mChart.invalidate();  // Aanroepen van een redraw
        Log.d("aantal2 =", ""+currentEcts);
    }

    private void setDataBehaald(int aantal) {
        currentEcts = aantal;
        ArrayList<Entry> yValues = new ArrayList<>();
        ArrayList<String> xValues = new ArrayList<>();

        yValues.add(new Entry(aantal, 0));
        xValues.add("EXTRA ECTS");

//        yValues.add(new Entry(60 - currentEcts, 1));
        xValues.add("");

        //  http://www.materialui.co/colors
        ArrayList<Integer> colors = new ArrayList<>();
            colors.add(Color.rgb(0,255,0));



        PieDataSet dataSet = new PieDataSet(yValues, "");
        dataSet.setColors(colors);

        PieData data = new PieData(xValues, dataSet);
        mChart.setData(data); // bind dataset aan chart.
        mChart.invalidate();  // Aanroepen van een redraw
        Log.d("aantal =", ""+currentEcts);
    }

    public void onClick(View view) {
        Intent intent = new Intent(PieChartActivity.this , OverzichtActivity.class);
        startActivity(intent);

    }
}




package com.example.administrator.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.PolylineOptions;
import com.example.administrator.R;
import com.example.administrator.adapter.ReviewerAdapter;
import com.example.administrator.connect.ConnTool;
import com.example.administrator.model.CommentDetails;
import com.example.administrator.model.DotStrategy;
import com.example.administrator.model.Point;
import com.example.administrator.model.Strategy;
import com.example.administrator.util.AMapUtil;
import com.example.administrator.util.FileSaveUtils;
import com.example.administrator.util.PictureUtil;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StrategyActivity extends AppCompatActivity implements
        AMap.OnMapClickListener, AMap.OnMarkerClickListener{


    private List<CommentDetails> commentDetailsList =new ArrayList<>();
    private View view;
    private MapView mapView;
    private AMap aMap;
    private View markerView;
    private CircleImageView icon;
    private static String TAG = "StrategyActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strategy);
        mapView = (MapView)findViewById(R.id.strategy_map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写

        if (aMap == null) {
            aMap = mapView.getMap();

        }

        setup();


        initpinglunzhe();
        ReviewerAdapter adapter=new ReviewerAdapter(StrategyActivity.this,R.layout.comment_item, commentDetailsList);
        ListView listView=(ListView)findViewById(R.id.guestlist);
        listView.setAdapter(adapter);

        StrategyActivity.ReadFileThread sendMessage = new StrategyActivity.ReadFileThread();

        sendMessage.start();
        try {
            sendMessage.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendMessage.interrupt();
    }

    private void setup(){
        UiSettings uiSettings = aMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);// 设置缩放按钮是否显示
        uiSettings.setScaleControlsEnabled(true);// 设置比例尺是否显示
        uiSettings.setRotateGesturesEnabled(true);// 设置地图旋转是否可用
        uiSettings.setTiltGesturesEnabled(true);// 设置地图倾斜是否可用
        uiSettings.setMyLocationButtonEnabled(false);// 设置默认定位按钮是否显示

        MyLocationStyle myLocationStyle = new MyLocationStyle();
        aMap.setOnMarkerClickListener(this);
        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(25));
        aMap.setMapTextZIndex(2);
    }



    private void initpinglunzhe(){
        CommentDetails guest1=new CommentDetails("小明","这地方真不错",R.drawable.touxiang1);
        commentDetailsList.add(guest1);
        CommentDetails guest2=new CommentDetails("小王","我觉得不行",R.drawable.touxiang2);
        commentDetailsList.add(guest2);
    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();

    }


    private class ReadFileThread extends Thread{


        @Override
        public void run() {
            super.run();

            Gson gson = new Gson();
            String s = (String)getIntent().getSerializableExtra("otherStrategies");
            Log.i(TAG, "文件名: "+s);

            Strategy strategy = gson.fromJson(FileSaveUtils.readFile(FileSaveUtils.getRealPath()+"otherStrategies/"+s+".txt"),Strategy.class);

            TextView place = findViewById(R.id.otherStrategy_place);
            place.setText(strategy.getTitle());

            TextView label = findViewById(R.id.otherStrategy_label);
            label.setText(strategy.getLabel());

            TextView distance = findViewById(R.id.otherStrategy_distance);
            distance.setText((strategy.getRoute().getTotal_distance()/1000)+"");

            List<DotStrategy>dotStrategies = strategy.getDotStrategy();

            for(DotStrategy dotStrategy:dotStrategies){

//                markerView = LayoutInflater.from(StrategyActivity.this).inflate(R.layout.marker_strategy,null);
//                icon = (CircleImageView) findViewById(R.id.marker_item_icon);
//                Bitmap bitmap = PictureUtil.getBitmap(dotStrategy.getPicture().getBitmapBytes());
//                icon.setImageBitmap(bitmap);
//                BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory
//                        .fromBitmap(PictureUtil.convertViewToBitmap(markerView));


                Marker marker = aMap.addMarker(new MarkerOptions()
                        .anchor(0.5f, 0.5f)
                        .icon(BitmapDescriptorFactory
                                .fromBitmap(BitmapFactory.decodeResource(
                                        getResources(), R.mipmap.review_marker)))
                        .position(new LatLng(dotStrategy.getLatitude(), dotStrategy.getLongitude())));
            }

            List<Point>points = strategy.getRoute().getPoints();

            drawLines(points);
            aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(points.get(0).getLatitude(),points.get(0).getLongitude())));





        }

        /**
         * 绘制运动路线
         *
         * @param points
         */
        public void drawLines(List<Point>points) {

            PolylineOptions options = new PolylineOptions();
            for(int i =0;i<points.size()-1;i++){

                //上一个点的经纬度
                options.add(new LatLng(points.get(i).getLatitude(), points.get(i).getLongitude()));
                //当前的经纬度
                options.add(new LatLng(points.get(i+1).getLatitude(), points.get(i+1).getLongitude()));
                options.width(35);
                options.setCustomTexture(BitmapDescriptorFactory.fromResource(R.mipmap.map_alr3));
                options.aboveMaskLayer(true);
                aMap.addPolyline(options);
            }


        }
    }



}

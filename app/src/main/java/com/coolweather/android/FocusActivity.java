package com.coolweather.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.coolweather.android.db.County;
import com.coolweather.android.db.Focus;

import org.litepal.crud.DataSupport;
import org.litepal.util.Const;

import java.util.ArrayList;
import java.util.List;

public class FocusActivity extends AppCompatActivity {

    private ListView listView;
    private List<Focus> focusList=new ArrayList<>();
    private List<County> countyList=new ArrayList<>();
    private List<String> dataList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus);
        listView=(ListView)findViewById(R.id.focus_listview);
        initFocus();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(FocusActivity.this,android.R.layout.simple_list_item_1,dataList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Focus focus=focusList.get(i);
                Intent intent=new Intent(FocusActivity.this,WeatherActivity.class);
                intent.putExtra("weather_id",focus.getWeatherId());
                //selectedCity=cityList.get(i);
                //System.out.println(selectedCity.getCity_name());
                startActivity(intent);
            }
        });
    }
    private void initFocus(){
        dataList.clear();
        focusList= DataSupport.findAll(Focus.class);
        countyList=DataSupport.findAll(County.class);
        for(Focus focus:focusList){
            for(County county:countyList){
                if(focus.getWeatherId().equals(county.getWeatherId())){
                    dataList.add(county.getCountyName());
                }
            }
        }
    }
}

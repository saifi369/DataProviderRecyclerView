package com.saifi369.dataprovider.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.saifi369.dataprovider.R;
import com.saifi369.dataprovider.adapters.MyListAdapter;
import com.saifi369.dataprovider.model.CityDataItem;
import com.saifi369.dataprovider.sample.SampleDataProvider;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<CityDataItem> mDataList;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView=findViewById(R.id.list_city);
        mDataList= SampleDataProvider.cityDataItemList;

        MyListAdapter adapter=new MyListAdapter(this,mDataList);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cityId=mDataList.get(position).getCityId();
                Toast.makeText(MainActivity.this, cityId, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,DetailsActivity.class);
                intent.putExtra(MyListAdapter.CITY_KEY,cityId);
                startActivity(intent);
            }
        });

        mListView.setAdapter(adapter);

    }

}

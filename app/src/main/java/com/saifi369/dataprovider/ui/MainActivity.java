package com.saifi369.dataprovider.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.saifi369.dataprovider.R;
import com.saifi369.dataprovider.adapters.MyDataAdapter;
import com.saifi369.dataprovider.database.CityDataSource;
import com.saifi369.dataprovider.model.CityDataItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<CityDataItem> mDataList;
    private RecyclerView mRecyclerView;
    private CityDataSource mDataSource;

    MyDataAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mDataList= SampleDataProvider.cityDataItemList;
        mRecyclerView=findViewById(R.id.list_city);

        mDataSource=new CityDataSource(this);
        mDataSource.open();
        mDataSource.seedDatabase();

        LinearLayoutManager manager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);

        getDatabaseList(null);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.show_all:
            {
                getDatabaseList(null);
                break;
            }
            case R.id.show_balochistan:
            {
                getDatabaseList("Balochistan");
                break;
            }
            case R.id.show_kpk:
            {
                getDatabaseList("KPK");
                break;
            }
            case R.id.show_punjab:
            {
                getDatabaseList("Punjab");
                break;
            }
            case R.id.show_sindh:
            {
                getDatabaseList("Sindh");
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    private void getDatabaseList(String prov) {
        mDataList=mDataSource.getAllItems(prov);
        adapter=new MyDataAdapter(mDataList,this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDataSource.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDataSource.close();
    }
}

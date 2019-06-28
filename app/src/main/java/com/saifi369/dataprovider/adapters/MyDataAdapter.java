package com.saifi369.dataprovider.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.saifi369.dataprovider.R;
import com.saifi369.dataprovider.model.CityDataItem;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyDataAdapter extends RecyclerView.Adapter<MyDataAdapter.MyViewHolder> {

    private List<CityDataItem> mDataList;
    private Context context;

    public MyDataAdapter(List<CityDataItem> mDataList, Context context) {
        this.mDataList = mDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.list_item_layout,parent,false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CityDataItem cityDataItem=mDataList.get(position);

        holder.textView.setText(cityDataItem.getCityName());

        InputStream inputStream=null;
        try{
            inputStream = context.getAssets().open(cityDataItem.getImage());
            Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
            holder.imageView.setImageBitmap(bitmap);
            Log.d("meratag", "getView: Image Downloaded: "+cityDataItem.getImage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (inputStream == null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public  static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.textView);
            imageView=itemView.findViewById(R.id.imageView);
        }
    }
}

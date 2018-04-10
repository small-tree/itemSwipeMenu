package com.example.swipeitem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

public class RecyclerviewActivity extends AppCompatActivity {

    public static void startSelf(Context context){
        Intent intent = new Intent(context, RecyclerviewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        RecyclerView rv_recyclerview = findViewById(R.id.rv_recyclerview);
        rv_recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        RecyclerViewAcAdapter adapter = new RecyclerViewAcAdapter();
        adapter.setItemClickListener(new RecyclerViewAcAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(RecyclerviewActivity.this, position  + "item click", Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setItemMenuClickListener(new RecyclerViewAcAdapter.ItemMenuClickListener() {
            @Override
            public void onItemMenuClick(int position) {
                Toast.makeText(RecyclerviewActivity.this, position + "item menu click", Toast.LENGTH_SHORT).show();
            }
        });

        rv_recyclerview.setAdapter(adapter);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}

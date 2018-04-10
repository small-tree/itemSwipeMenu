package com.example.swipeitem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final XCSwipMenuLayout viewById = findViewById(R.id.myviewgroup);
        viewById.setMenuWidth(200);
        viewById.setXCSwipMenuLayoutListener(new XCSwipMenuLayoutListener() {
            @Override
            public void onSliding(int distence) {
                Log.i(TAG, "onSliding: " + distence);
            }

            @Override
            public void onClickMenu() {
                Log.i(TAG, "onClickMenu: ");
                Toast.makeText(MainActivity.this, "onclickmenu", Toast.LENGTH_SHORT).show();
                viewById.closeMenu();
            }

            @Override
            public void onClick() {
                Log.i(TAG, "onClick: ");
                Toast.makeText(MainActivity.this, "onclick", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void onClick(View view) {
        RecyclerviewActivity.startSelf(this);
    }

}
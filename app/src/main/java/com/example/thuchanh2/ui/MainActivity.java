package com.example.thuchanh2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.thuchanh2.R;
import com.example.thuchanh2.data.model.Item;
import com.example.thuchanh2.ui.FirstFragment;
import com.example.thuchanh2.ui.SecondFragment;
import com.example.thuchanh2.ui.ThirdFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity  {

    BottomNavigationView bottomNavigationView;
    FloatingActionButton btAdd;
    TabLayout tabLayout;
    FirstFragment firstFragment = new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();
    ThirdFragment thirdFragment = new ThirdFragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        btAdd = findViewById(R.id.bt_add);

        initListener();

    }

    private void initListener() {
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu1:
                        setCurrentFragment(firstFragment);
                        break;
                    case R.id.menu2:
                        setCurrentFragment(secondFragment);
                        break;
                    case R.id.menu3:
                        setCurrentFragment(thirdFragment);
                        break;
                }
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.menu1);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(i, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            Item item = (Item) data.getSerializableExtra("item");
            firstFragment.onItemAdded(item);
        }
    }

    private void setCurrentFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment)
                .commit();
    }
}
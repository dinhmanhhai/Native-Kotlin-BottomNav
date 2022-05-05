package com.example.thuchanh2.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thuchanh2.R;
import com.example.thuchanh2.data.db.SQLiteHelper;
import com.example.thuchanh2.data.model.Item;

public class AddActivity extends AppCompatActivity {

    private final String[] statusList = {
            "Chưa hoàn thành", "Đang làm", "Đã hoàn thành"
    };
    EditText etTitle, etCategory, etPrice, etDate;
    Button btAdd, btCancel;
    CheckBox cbCoop;
    Spinner spStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etTitle = findViewById(R.id.et_title);
        etCategory = findViewById(R.id.et_detail);
        etDate = findViewById(R.id.et_date);
        btAdd = findViewById(R.id.bt_update);
        btCancel = findViewById(R.id.bt_cancel);
        cbCoop = findViewById(R.id.cb_coop);
        spStatus = findViewById(R.id.sp_status);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                statusList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStatus.setAdapter(adapter);

        initListener();
    }

    private void initListener() {
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etTitle.getText().toString();
                String detail = etCategory.getText().toString();
                String status = spStatus.getSelectedItem().toString();
                String date = etDate.getText().toString();
                boolean isCoop = cbCoop.isChecked();

                if (name.isEmpty() || detail.isEmpty() || status.isEmpty() || date.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                } else {
                    SQLiteHelper sqLiteHelper = new SQLiteHelper(getBaseContext());
                    Item item = new Item(0, name, detail, status, date, isCoop);
                    sqLiteHelper.addItem(item);
                    Toast.makeText(getBaseContext(), "Done", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent();
                    i.putExtra("item", item);
                    setResult(RESULT_OK, i);
                    finish();
                }
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedYear = 2022;
                int selectedMonth = 5;
                int selectedDayOfMonth = 4;

                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etDate.setText(dayOfMonth + "/" + (month+1 )+ "/" +year );
                    }

                };

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddActivity.this,
                        dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);

                datePickerDialog.show();
            }
        });
    }
}
package com.example.thuchanh2.ui;

import androidx.appcompat.app.AppCompatActivity;

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

import com.example.thuchanh2.R;
import com.example.thuchanh2.data.db.SQLiteHelper;
import com.example.thuchanh2.data.model.Item;

public class EditActivity extends AppCompatActivity {

    Item item;
    EditText etTitle, etCategory, etPrice, etDate;
    Button btUpdate, btDelete, btCancel;
    Spinner spStatus;
    CheckBox cbCoop;

    private final String[] statusList = {
            "Chưa hoàn thành", "Đang làm", "Đã hoàn thành"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        item = (Item) getIntent().getSerializableExtra("item");
        etTitle = findViewById(R.id.et_title);
        etCategory = findViewById(R.id.et_detail);
        etDate = findViewById(R.id.et_date);
        btUpdate = findViewById(R.id.bt_update);
        btDelete = findViewById(R.id.bt_delete);
        spStatus = findViewById(R.id.sp_status);
        cbCoop = findViewById(R.id.cb_coop);
        btCancel = findViewById(R.id.bt_cancel);

        initView();
        initListener();

    }

    private void initView() {
        etTitle.setText(item.getName());
        etCategory.setText(item.getDetail());
        etDate.setText(item.getDate());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                statusList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStatus.setAdapter(adapter);

        for (int i =0; i <statusList.length; i++){
            if(item.getStatus().equals(statusList[i])){
                spStatus.setSelection(i);
                break;
            }
        }

    }

    private void initListener() {
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etTitle.getText().toString();
                String detail = etCategory.getText().toString();
                String status = spStatus.getSelectedItem().toString();;
                String date = etDate.getText().toString();
                boolean isCoop = cbCoop.isChecked();

                if(name.isEmpty() || detail.isEmpty() || status.isEmpty() || date.isEmpty()){
                    Toast.makeText(getBaseContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                }
                else{
                    item.setName(name);
                    item.setDetail(detail);
                    item.setStatus(status);
                    item.setDate(date);
                    item.setCoop(isCoop);
                    SQLiteHelper sqLiteHelper = new SQLiteHelper(getBaseContext());
                    sqLiteHelper.updateItem(item);
                    Toast.makeText(getBaseContext(), "Sửa thành công!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteHelper sqLiteHelper = new SQLiteHelper(getBaseContext());
                sqLiteHelper.deleteItem(item.getId());
                Toast.makeText(getBaseContext(), "Xóa thành công!", Toast.LENGTH_SHORT).show();
                finish();
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(EditActivity.this,
                        dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);

                datePickerDialog.show();
            }
        });
    }

}
package com.bmcong2k.luyentapsql2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bmcong2k.luyentapsql2.dal.SQliteHelper;
import com.bmcong2k.luyentapsql2.model.BaiHat;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{

    private Spinner spAlbum, spTheLoai;
    private EditText eTenBaiHat, eTenCaSi;
    private Button btnUpdate, btnCancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initView();

        btnUpdate.setOnClickListener(this);
        btnCancle.setOnClickListener(this);
//        eNXB.setOnClickListener(this);

    }

    private void initView() {

        spAlbum = findViewById(R.id.spAlbum);
        spTheLoai = findViewById(R.id.spTheLoai);
        eTenBaiHat = findViewById(R.id.eTenBaiHat);
        eTenCaSi = findViewById(R.id.eTenCaSi);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancle = findViewById(R.id.btnCancle);
        spAlbum.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spriner,
                getResources().getStringArray(R.array.album)));
        spTheLoai.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spriner,
                getResources().getStringArray(R.array.theloai)));

    }

    @Override
    public void onClick(View v) {

        if (v == btnCancle){
            finish();
        }

        if (v == btnUpdate){
            String  tenBH = eTenBaiHat.getText().toString();
            String  tenCasi = eTenCaSi.getText().toString();
            String  album = spAlbum.getSelectedItem().toString();
            String  theLoai = spTheLoai.getSelectedItem().toString();


            if(!tenBH.isEmpty() && !tenCasi.isEmpty()){
                BaiHat i = new BaiHat(tenBH, tenCasi, album, theLoai);
                SQliteHelper db = new SQliteHelper(this);
                db.addItem(i);
                finish();
            }
        }

    }
}
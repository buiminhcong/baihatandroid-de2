package com.bmcong2k.luyentapsql2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.bmcong2k.luyentapsql2.dal.SQliteHelper;
import com.bmcong2k.luyentapsql2.model.BaiHat;

import java.util.Calendar;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener{

    private Spinner spAlbum, spTheLoai;
    private EditText eTenCaSi, eTenBaiHat;
    private Button btnUpdate, btnBack, btnRemove;
    private BaiHat baiHat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        initView();

        btnUpdate.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnRemove.setOnClickListener(this);


        Intent intent = getIntent();
        baiHat = (BaiHat) intent.getSerializableExtra("baiHat");
        eTenBaiHat.setText(baiHat.getTenBH());
        eTenCaSi.setText(baiHat.getTenCasi());
        int p = 0, g = 0;
        for(int i=0; i<spAlbum.getCount(); i++){
            if(spAlbum.getItemAtPosition(i).toString().equalsIgnoreCase(baiHat.getAlbum())){
                p=i;
                break;
            }
        }
        spAlbum.setSelection(p);

        for(int i=0; i<spTheLoai.getCount(); i++){
            if(spTheLoai.getItemAtPosition(i).toString().equalsIgnoreCase(baiHat.getTheLoai())){
                g=i;
                break;
            }
        }
        spTheLoai.setSelection(g);

    }

    private void initView() {

        spAlbum  = findViewById(R.id.spAblum);
        spTheLoai = findViewById(R.id.spTheLoai);
        eTenBaiHat = findViewById(R.id.tvTenBaiHat);
        eTenCaSi = findViewById(R.id.tvTenCaSi);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnBack = findViewById(R.id.btnBack);
        btnRemove = findViewById(R.id.btnDelete);

        spAlbum.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spriner,
                getResources().getStringArray(R.array.album)));
        spTheLoai.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spriner,
                getResources().getStringArray(R.array.theloai)));

    }

    @Override
    public void onClick(View v) {
        SQliteHelper db = new SQliteHelper(this);


        if(v == btnBack){
            finish();
        }

        if(v == btnUpdate){
            String  tenBH = eTenBaiHat.getText().toString();
            String  tenCasi = eTenCaSi.getText().toString();
            String  album = spAlbum.getSelectedItem().toString();
            String  theLoai = spTheLoai.getSelectedItem().toString();


            if(!tenBH.isEmpty() && !tenCasi.isEmpty()){
                int id = baiHat.getId();
                BaiHat i = new BaiHat(id,tenBH, tenCasi, album, theLoai);
                SQliteHelper db1 = new SQliteHelper(this);
                db1.update(i);
                finish();
            }
        }

        if(v == btnRemove){
            int id = baiHat.getId();
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Thong Bao Xoa");
            builder.setMessage("Ban Co Chac Muon Xoa " + baiHat.getTenBH() + " khong?");
            builder.setIcon(R.drawable.remove);
            builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    db.delete(id);
                    finish();
                }
            });
            builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}
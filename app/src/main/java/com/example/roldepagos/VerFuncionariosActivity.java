package com.example.roldepagos;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.roldepagos.R;
import com.example.roldepagos.bdd.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class VerFuncionariosActivity extends AppCompatActivity {

    private ListView funcionariosListView;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_funcionarios);

        dbHelper = new DBHelper(this);
        funcionariosListView = findViewById(R.id.funcionariosListView);

        mostrarFuncionarios();
    }

    private void mostrarFuncionarios() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM funcionarios", null);

        List<String> funcionariosList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                @SuppressLint("Range") String cargo = cursor.getString(cursor.getColumnIndex("cargo"));
                funcionariosList.add(nombre + " - " + cargo);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, funcionariosList);
        funcionariosListView.setAdapter(adapter);
    }
}

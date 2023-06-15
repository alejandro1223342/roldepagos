package com.example.roldepagos;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.roldepagos.bdd.DBHelper;

public class ActualizarFuncionarioActivity extends AppCompatActivity {

    private EditText nombreEditText;
    private EditText numHijosEditText;
    private Button actualizarButton;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_funcionario);

        dbHelper = new DBHelper(this);
        nombreEditText = findViewById(R.id.nombreEditText);
        numHijosEditText = findViewById(R.id.numHijosEditText);
        actualizarButton = findViewById(R.id.actualizarButton);

        actualizarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarFuncionario();
            }
        });
    }

    private void actualizarFuncionario() {
        String nombre = nombreEditText.getText().toString();
        int numHijos = Integer.parseInt(numHijosEditText.getText().toString());

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("num_hijos", numHijos);

        int rowsAffected = db.update("funcionarios", values, "nombre=?", new String[]{nombre});

        if (rowsAffected > 0) {
            Toast.makeText(this, "Funcionario actualizado correctamente", Toast.LENGTH_SHORT).show();
            nombreEditText.setText("");
            numHijosEditText.setText("");
        } else {
            Toast.makeText(this, "No se encontró ningún funcionario con ese nombre", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }
}

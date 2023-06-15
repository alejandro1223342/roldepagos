package com.example.roldepagos;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.roldepagos.bdd.DBHelper;

public class EliminarFuncionarioActivity extends AppCompatActivity {

    private EditText nombreEditText;
    private Button eliminarButton;
    private DBHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_funcionario);

        dbHelper = new DBHelper(this);
        nombreEditText = findViewById(R.id.nombreEditText);
        eliminarButton = findViewById(R.id.eliminarButton);

        eliminarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmarEliminacion();
            }
        });
    }

    private void confirmarEliminacion() {
        String nombre = nombreEditText.getText().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmar Eliminación");
        builder.setMessage("¿Estás seguro/a de eliminar el funcionario? Esta acción no se puede deshacer.");
        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                eliminarFuncionario(nombre);
            }
        });
        builder.setNegativeButton("Cancelar", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void eliminarFuncionario(String nombre) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int rowsAffected = db.delete("funcionarios", "nombre=?", new String[]{nombre});

        if (rowsAffected > 0) {
            Toast.makeText(this, "Funcionario eliminado correctamente", Toast.LENGTH_SHORT).show();
            nombreEditText.setText("");
        } else {
            Toast.makeText(this, "No se encontró ningún funcionario con ese nombre", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }
}
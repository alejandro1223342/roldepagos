package com.example.roldepagos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.roldepagos.bdd.DBHelper;

public class MainActivity extends AppCompatActivity {

    private EditText nombreEditText;
    private EditText numHijosEditText;
    private Button guardarButton;

    private Spinner cargoSpinner;
    private Spinner areaSpinner;
    private Spinner estadoCivilSpinner;

    private ArrayAdapter<String> cargoAdapter;
    private ArrayAdapter<String> areaAdapter;
    private ArrayAdapter<String> estadoCivilAdapter;

    private String[] cargos = {"Docente", "Administrativo"};
    private String[] areas = {"Área 1", "Área 2", "Área 3"};
    private String[] estadosCiviles = {"Soltero/a", "Casado/a", "Divorciado/a"};

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        cargoSpinner = findViewById(R.id.cargoSpinner);
        areaSpinner = findViewById(R.id.areaSpinner);
        estadoCivilSpinner = findViewById(R.id.estadoCivilSpinner);
        nombreEditText = findViewById(R.id.nombreEditText);
        numHijosEditText = findViewById(R.id.numHijosEditText);
        guardarButton = findViewById(R.id.guardarButton);

        cargoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cargos);
        cargoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cargoSpinner.setAdapter(cargoAdapter);

        areaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, areas);
        areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        areaSpinner.setAdapter(areaAdapter);

        estadoCivilAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, estadosCiviles);
        estadoCivilAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estadoCivilSpinner.setAdapter(estadoCivilAdapter);

        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarFuncionario();
            }
        });
    }

    private void guardarFuncionario() {

        String cargo = cargoSpinner.getSelectedItem().toString();
        String area = areaSpinner.getSelectedItem().toString();
        String estadoCivil = estadoCivilSpinner.getSelectedItem().toString();

        String nombre = nombreEditText.getText().toString();
        int numHijos = Integer.parseInt(numHijosEditText.getText().toString());
        int subsidio = calcularSubsidio(numHijos);
        double sueldoRecibir = calcularSueldoRecibir(cargo, subsidio);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("cargo", cargo);
        values.put("area", area);
        values.put("num_hijos", numHijos);
        values.put("estado_civil", estadoCivil);
        values.put("subsidio", subsidio);
        values.put("sueldo_recibir", sueldoRecibir);

        db.insert("funcionarios", null, values);

        // Limpia los campos de entrada después de guardar los datos
        nombreEditText.setText("");
        numHijosEditText.setText("");

        // Muestra el sueldo a pagar en un TextView después de guardar los datos
        TextView sueldoRecibirTextView = findViewById(R.id.sueldoRecibirTextView);
        sueldoRecibirTextView.setText(String.valueOf(sueldoRecibir));

        db.close();
    }

    private int calcularSubsidio(int numHijos) {
        return numHijos * 100;
    }

    private double calcularSueldoRecibir(String cargo, int subsidio) {
        if (cargo.equalsIgnoreCase("Docente")) {
            return 1000 + subsidio;
        } else if (cargo.equalsIgnoreCase("Administrativo")) {
            return 880 + subsidio;
        } else {
            return 0;
        }
    }
}

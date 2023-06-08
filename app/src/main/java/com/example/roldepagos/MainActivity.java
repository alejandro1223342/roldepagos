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

import com.example.roldepagos.bdd.DBHelper;

public class MainActivity extends AppCompatActivity {

    private EditText nombreEditText;
    private EditText numHijosEditText;
    private EditText subsidioEditText;
    private EditText descuentoAtrasosEditText;
    private EditText horasExtrasEditText;
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
        subsidioEditText = findViewById(R.id.subsidioEditText);
        descuentoAtrasosEditText = findViewById(R.id.descuentoAtrasosEditText);
        horasExtrasEditText = findViewById(R.id.horasExtrasEditText);
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
        double descuentoAtrasos = Double.parseDouble(descuentoAtrasosEditText.getText().toString());
        int horasExtras = Integer.parseInt(horasExtrasEditText.getText().toString());
        double sueldoRecibir = calcularSueldoRecibir(cargo, descuentoAtrasos, horasExtras);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("cargo", cargo);
        values.put("area", area);
        values.put("num_hijos", numHijos);
        values.put("estado_civil", estadoCivil);
        values.put("subsidio", subsidio);
        values.put("descuentro_atrasos", descuentoAtrasos);
        values.put("horas_extras", horasExtras);
        values.put("sueldo_recibir", sueldoRecibir);

        db.insert("funcionarios", null, values);

        // Limpia los campos de entrada después de guardar los datos
        nombreEditText.setText("");
        numHijosEditText.setText("");
        subsidioEditText.setText("");
        descuentoAtrasosEditText.setText("");
        horasExtrasEditText.setText("");

        db.close();
    }

    private int calcularSubsidio(int numHijos) {

        return numHijos * 100;
    }

    private double calcularSueldoRecibir(String cargo, double descuentoAtrasos, int horasExtras) {

        if (cargo.equalsIgnoreCase("docente")) {
            return 1000 - (1000 * descuentoAtrasos / 100) + (horasExtras * 12);
        } else if (cargo.equalsIgnoreCase("administrativo")) {
            return 880 - (880 * descuentoAtrasos / 100) + (horasExtras * 12);
        } else {
            return 0;
        }
    }
}
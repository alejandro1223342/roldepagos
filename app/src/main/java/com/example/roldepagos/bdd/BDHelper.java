package com.example.roldepagos.bdd;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "funcionarios.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE funcionarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," +
                "cargo TEXT," +
                "area TEXT," +
                "num_hijos INTEGER," +
                "estado_civil TEXT," +
                "subsidio INTEGER," +
                "descuentro_atrasos REAL," +
                "horas_extras INTEGER," +
                "sueldo_recibir REAL)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Aquí puedes implementar la lógica para actualizar la base de datos si es necesario en futuras versiones.
    }
}

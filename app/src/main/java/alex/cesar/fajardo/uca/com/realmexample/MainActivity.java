package alex.cesar.fajardo.uca.com.realmexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import alex.cesar.fajardo.uca.com.realmexample.models.Persona;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    EditText nombre,apellido,edad,telefono;
    Button guardar,leer;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm = Realm.getDefaultInstance();

        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        edad = findViewById(R.id.edad);
        telefono = findViewById(R.id.telefono);
        guardar = findViewById(R.id.guardar);
        leer = findViewById(R.id.leer);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Persona p = realm.createObject(Persona.class);
                        p.setNombre(nombre.getText().toString());
                        p.setApellido(apellido.getText().toString());
                        p.setEdad(Integer.valueOf(edad.getText().toString()));
                        p.setTelefono(Integer.valueOf(telefono.getText().toString()));
                    }
                });

                Toast.makeText(MainActivity.this, "Persona guardada correctamente",
                        Toast.LENGTH_SHORT).show();

            }
        });

        leer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmResults<Persona> personas = realm.where(Persona.class).findAll();
                StringBuilder sb = new StringBuilder();
                for(Persona p: personas){
                    sb.append(" \n " );
                    sb.append(" \n Nombre: " + p.getNombre());
                    sb.append(" \n Apellido: " + p.getApellido());
                    sb.append(" \n Edad: " + p.getEdad());
                    sb.append(" \n Telefono: " + p.getTelefono());
                    sb.append(" \n " );
                }

                Log.e("TAG", sb.toString());

                Toast.makeText(MainActivity.this, "Los resultados estan en la consola",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }
}

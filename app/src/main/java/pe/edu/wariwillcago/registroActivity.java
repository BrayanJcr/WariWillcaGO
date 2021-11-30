package pe.edu.wariwillcago;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import pe.edu.wariwillcago.R;

public class registroActivity extends AppCompatActivity implements View.OnClickListener, Response.ErrorListener, Response.Listener<JSONObject> {

    EditText edtRegNom, edtRegApp, edtRegTel, edtRegCorreo, edtRegPass;
    Button btnRegReg, btnCancelarReg;
    private String nombre, apellidos, telefono, correoReg, password;

    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edtRegNom = findViewById(R.id.edtRegNom);
        edtRegApp = findViewById(R.id.edtRegApp);
        edtRegTel = findViewById(R.id.edtRegTel);
        edtRegCorreo = findViewById(R.id.edtRegCorreo);
        edtRegPass = findViewById(R.id.edtRegPass);
        btnRegReg = findViewById(R.id.btnRegReg);
        btnCancelarReg = findViewById(R.id.btnCancelarReg);

        btnRegReg.setOnClickListener(this);
        btnCancelarReg.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRegReg:{

                //Cuando se registra;
                nombre = edtRegNom.getText().toString();
                apellidos = edtRegApp.getText().toString();
                telefono = edtRegTel.getText().toString();
                correoReg = edtRegCorreo.getText().toString();
                password = edtRegPass.getText().toString();


                if(nombre.isEmpty() || apellidos.isEmpty() || telefono.isEmpty() || correoReg.isEmpty() || password.isEmpty()){
                    Toast.makeText(this, "Ingrese Todos los datos solicitados", Toast.LENGTH_SHORT).show();


                }
                else{
                    if (correoReg.contains("@")){
                        llamarWebService();
                    }else{
                        Toast.makeText(this, "Ingrese un correo electrónico valido", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            }
            case R.id.btnCancelarReg:{
                //cuando cancela el registro
                edtRegNom.setText("");
                edtRegApp.setText("");
                edtRegTel.setText("");
                edtRegCorreo.setText("");
                edtRegPass.setText("");
                Intent i = new Intent(registroActivity.this, loginActivity.class);
                startActivity(i);
                finish();
                break;
            }
        }
    }

    public void llamarWebService(){

        progreso = new ProgressDialog(this);
        progreso.setMessage("Consultado");
        progreso.show();
        String url = "http://152.70.136.7/wari/registroUsuario.php?nomUsuario="+nombre+"&appUsuario="+apellidos +"&emailUsuario="+correoReg+"&passUsuario="+ password +"&telefonoUsuario="+ telefono;
        url = url.replace(" ", "%20");
        request = Volley.newRequestQueue(this);
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
        System.out.println(url);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(this, "Error de registro", Toast.LENGTH_SHORT).show();
        Log.i("Error", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this, "Registro de Usuario Existoso", Toast.LENGTH_SHORT).show();
        progreso.hide();

        edtRegNom.setText("");
        edtRegApp.setText("");
        edtRegTel.setText("");
        edtRegCorreo.setText("");
        edtRegPass.setText("");

        Intent i = new Intent(registroActivity.this, loginActivity.class);
        startActivity(i);
    }
}
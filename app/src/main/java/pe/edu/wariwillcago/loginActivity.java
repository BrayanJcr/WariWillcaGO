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
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class loginActivity extends AppCompatActivity implements View.OnClickListener, Response.ErrorListener, Response.Listener<JSONObject> {

    EditText edtCorreo, edtPass;
    Button btnRegistro, btnIngreso;
    private String correo, password;

    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtCorreo = findViewById(R.id.edtCorreo);
        edtPass = findViewById(R.id.edtPass);
        btnIngreso = findViewById(R.id.btnIngreso);
        btnRegistro = findViewById(R.id.btnRegistro);
        btnIngreso.setOnClickListener(this);
        btnRegistro.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnIngreso:{

                correo = edtCorreo.getText().toString();
                password = edtPass.getText().toString();


                if(correo.isEmpty() || password.isEmpty()){
                    Toast.makeText(this,"Por favor ingrese un usuario y/o contraseña", Toast.LENGTH_SHORT).show();
                }else{
                    llamarWebService();
                }
                break;
            }
            case R.id.btnRegistro:{

                System.out.println("intentRegistro");
                Intent intent = new Intent(loginActivity.this, registroActivity.class);
                startActivity(intent);
                break;
            }
        }

    }

    public void llamarWebService(){

        progreso = new ProgressDialog(this);
        progreso.setMessage("Consultado");
        progreso.show();
        String url = "http://152.70.136.7/wari/consultarLogin.php?emailUsuario="+correo+"&passUsuario="+password;
        url = url.replace(" ", "%20");
        request = Volley.newRequestQueue(this);
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
        System.out.println(url);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(this, "Error al momento de iniciar sesión", Toast.LENGTH_SHORT).show();
        Log.i("Error", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this, "Ingreso Existoso", Toast.LENGTH_SHORT).show();
        progreso.hide();
        edtCorreo.setText("");
        edtPass.setText("");
        String correoTraido="", passTraido="";

        JSONArray json = response.optJSONArray("tblUsuario");
        JSONObject jsonObject = null;

        try{
            jsonObject = json.getJSONObject(0);
            correoTraido = jsonObject.optString("emailUsuario");
            passTraido = jsonObject.optString("passUsuario");
        }catch(JSONException e){
            e.printStackTrace();
        }

        System.out.println(correoTraido + " " + passTraido);

        if(correoTraido.equals("no registrado"))
        {
            Toast.makeText(this, "usuario no registrado", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent = new Intent(loginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}


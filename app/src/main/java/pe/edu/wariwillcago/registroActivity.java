package pe.edu.wariwillcago;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pe.edu.wariwillcago.R;

public class registroActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtRegNom, edtRegApp, edtRegTel, edtRegCorreo, edtRegPass;
    Button btnRegReg, btnCancelarReg;

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
                String nombre = edtRegNom.getText().toString();
                String apellidos = edtRegApp.getText().toString();
                String telefono = edtRegTel.getText().toString();
                String correoReg = edtRegCorreo.getText().toString();
                String password = edtRegPass.getText().toString();

                if(nombre.isEmpty() || apellidos.isEmpty() || telefono.isEmpty() || correoReg.isEmpty() || password.isEmpty()){
                    Toast.makeText(this, "Ingrese Todos los datos solicitados", Toast.LENGTH_SHORT).show();
                }
                else{
                    //validacion de datos
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
}
package pe.edu.wariwillcago;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

public class loginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtCorreo, edtPass;
    Button btnRegistro, btnIngreso;

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

                String correo = edtCorreo.getText().toString();
                String password = edtPass.getText().toString();
                System.out.println(correo+";"+password);

                if(correo.isEmpty() || password.isEmpty()){
                    Toast.makeText(this,"Por favor ingrese un usuario y/o contraseña", Toast.LENGTH_SHORT).show();
                }else{
                    System.out.println("tratando de enviar intent");
                    Intent intent = new Intent(loginActivity.this, MainActivity.class);
                    startActivity(intent);

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
}
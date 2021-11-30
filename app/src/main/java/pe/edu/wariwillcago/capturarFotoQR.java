package pe.edu.wariwillcago;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import pe.edu.wariwillcago.ui.capturaqr.capturarqr;

public class capturarFotoQR extends AppCompatActivity {

    ProgressDialog dialog;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capturar_foto_qr);
        new IntentIntegrator(this).initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String datos = result.getContents();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        System.out.println(datos);
        Bundle bundle = new Bundle();

        bundle.putString("datoQR", datos);
        capturarqr fragment = new capturarqr();
        fragment.setArguments(bundle);
        fragmentTransaction.add(R.id.nav_capturaqr, fragment);
        fragmentTransaction.commit();
    }

    private void llamarWebservice() {
        dialog =new ProgressDialog(this);
        dialog.setMessage("Consultando Personajes");
        dialog.show();
        //String url="http://152.70.136.7/wari/consultarlista.php?imgQR="+datos;
        //jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, null,this,this);
        request.add(jsonObjectRequest);
    }
}
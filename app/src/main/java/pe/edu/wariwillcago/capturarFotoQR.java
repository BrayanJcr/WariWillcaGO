package pe.edu.wariwillcago;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class capturarFotoQR extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{

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

        System.out.println(datos);
        llamarWebservice(datos);
        Toast.makeText(this, "QR: "+datos, Toast.LENGTH_SHORT).show();
    }

    private void llamarWebservice(String QR) {
        dialog =new ProgressDialog(this);
        dialog.setMessage("Consultando QR");
        dialog.show();
        String url="http://152.70.136.7/wari/consultarQR.php?datoQR="+QR;
        url=url.replace(" ","%20");
        request = Volley.newRequestQueue(this);
        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, null,this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        dialog.hide();
        Toast.makeText(this,"No existe ese QR",Toast.LENGTH_SHORT).show();
        Log.i("Error",error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray json=response.optJSONArray("tblartesania");
        try {
            for(int i=0; i<json.length();i++) {
                JSONObject jsonObject=null;
                String id;
                jsonObject=json.getJSONObject(i);
                id = jsonObject.optString("codArt");
                System.out.println(id);
                Toast.makeText(this, id , Toast.LENGTH_SHORT).show();
                Intent inten = new Intent(capturarFotoQR.this,detalle_artesania.class);
                inten.putExtra("IDArte",id);
                startActivity(inten);
            }
            dialog.hide();
        }
        catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(this, "No hay conexion con el servidor", Toast.LENGTH_SHORT).show();
            dialog.hide();
        }
    }
}
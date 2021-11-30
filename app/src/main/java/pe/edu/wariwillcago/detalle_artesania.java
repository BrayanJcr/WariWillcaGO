package pe.edu.wariwillcago;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pe.edu.wariwillcago.ui.Entidad.Artesania;

public class detalle_artesania extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{

    ProgressDialog dialog;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    TextView txtTitu,txtDesc;
    ImageView imgArte,imgUbi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_artesania);
        txtDesc=findViewById(R.id.txtDescripcion);
        txtTitu = findViewById(R.id.txtTitulo);
        imgArte = findViewById(R.id.imgArtesania);
        imgUbi = findViewById(R.id.imgUbica);
        recibirDatos();
    }

    private void recibirDatos() {
        Bundle extras = getIntent().getExtras();
        String ID = extras.getString("IDArte");
        txtTitu.setText(ID);
        llamarWebservice(ID);
    }

    private void llamarWebservice(String ID) {
        dialog =new ProgressDialog(this);
        dialog.setMessage("Consultando QR");
        dialog.show();
        String url="http://152.70.136.7/wari/consultarArtesa.php?ID="+ID;
        url=url.replace(" ","%20");
        request = Volley.newRequestQueue(this);
        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, null,this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        dialog.hide();
        Toast.makeText(this,"No hay conexion",Toast.LENGTH_SHORT).show();
        Log.i("Error",error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        Artesania artesania=null;
        JSONArray json=response.optJSONArray("tblartesania");
        try {
            for(int i=0; i<json.length();i++) {
                artesania=new Artesania();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);
                artesania.setNombre(jsonObject.optString("tituloArt"));
                artesania.setDescripcion(jsonObject.optString("descArt"));
                artesania.setDato(jsonObject.optString("imgArt"));
                artesania.setDatoImUbi(jsonObject.optString("imgUbicacion"));

                txtTitu.setText(artesania.getNombre());
                txtDesc.setText(artesania.getDescripcion());
                if(artesania.getImagen() != null) {
                    imgArte.setImageBitmap(artesania.getImagen());
                }else{
                    imgArte.setImageResource(R.drawable.imgnodispo);
                }
                if(artesania.getImagenUbic() != null) {
                    imgUbi.setImageBitmap(artesania.getImagenUbic());
                }else{
                    imgUbi.setImageResource(R.drawable.imgnodispo);
                }
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
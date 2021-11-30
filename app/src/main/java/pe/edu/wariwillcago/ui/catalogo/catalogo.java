package pe.edu.wariwillcago.ui.catalogo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pe.edu.wariwillcago.R;
import pe.edu.wariwillcago.ui.Entidad.Artesania;
import pe.edu.wariwillcago.ui.adapter.ArtesaniaAdapter;


public class catalogo extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {

    RecyclerView recyclerViewPersonajes;
    ArrayList<Artesania> listaPersonaje;
    ProgressDialog dialog;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista =inflater.inflate(R.layout.fragment_catalogo, container, false);
        listaPersonaje= new ArrayList<>();
        recyclerViewPersonajes=(RecyclerView) vista.findViewById(R.id.idRecyclerListaArtesania);
        recyclerViewPersonajes.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerViewPersonajes.setHasFixedSize(true);
        request = Volley.newRequestQueue(getContext());

        //llamarWebservice
        llamarWebservice();

        return vista;
    }

    private void llamarWebservice() {
        dialog =new ProgressDialog(getContext());
        dialog.setMessage("Consultando Artesanias");
        dialog.show();
        String url="http://152.70.136.7/wari/ConsultarlistaImagenes.php";
        jsonObjectRequest= new JsonObjectRequest (Request.Method.GET, url, null,this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {


    }

    @Override
    public void onResponse(JSONObject response) {

        Artesania artesania=null;
        JSONArray json=response.optJSONArray("artesania");
        try {
            for(int i=0; i<json.length();i++) {
                artesania=new Artesania();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);
                artesania.setId(jsonObject.optInt("codArt"));
                artesania.setNombre(jsonObject.optString("tituloArt"));
                artesania.setDato(jsonObject.optString("imgArt"));
                listaPersonaje.add(artesania);
            }
            dialog.hide();
            ArtesaniaAdapter adapter=new ArtesaniaAdapter(listaPersonaje);
            recyclerViewPersonajes.setAdapter(adapter);
        }
        catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(getContext(), "No hay conexion con el servidor", Toast.LENGTH_SHORT).show();
            dialog.hide();
        }


    }
}
package pe.edu.wariwillcago.ui.capturaqr;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;

import org.json.JSONObject;

import pe.edu.wariwillcago.R;
import pe.edu.wariwillcago.capturarFotoQR;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link capturarqr#newInstance} factory method to
 * create an instance of this fragment.
 */
public class capturarqr extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener,View.OnClickListener{

    Button btnCaptureCode;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "datoQR";

    // TODO: Rename and change types of parameters
    private String datoQR;

    public capturarqr() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param datoQR Parameter 1.
     * @return A new instance of fragment catalogo.
     */

    // TODO: Rename and change types and number of parameters
    public static capturarqr newInstance(String datoQR) {
        capturarqr fragment = new capturarqr();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, datoQR);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            datoQR = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_capturarqr, container, false);
        btnCaptureCode = vista.findViewById(R.id.btnCapturarQR);
        btnCaptureCode.setOnClickListener(this);
        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println (datoQR);

        ((TextView)view.findViewById(R.id.textQR)).setText(datoQR);
    }

    /**public void llamarWebService(){
        progreso=new ProgressDialog(getContext());
        progreso.setMessage("Consultando");
        progreso.show();
        String url="http://192.168.100.201:80/anim/registroanim.php?id="
                +edtId.getText().toString()+"&nombre="+edtNombre.getText().toString()+
                "&caracteristicas="+edtCaracteristicas.getText().toString();
        //String url="http://152.67.54.242/anim/registroanim.php?id="
        //+edtId.getText().toString()+"&nombre="+edtNombre.getText().toString()+
        //"&caracteristicas="+edtCaracteristicas.getText().toString()+"&idserie="+1;

        //Espacio en blanco convertido en estandar %20 espacio en blanco
        url=url.replace(" ","%20");

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,this,this);
        request.add(jsonObjectRequest);
    }*/

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getContext(),"Error de registro",Toast.LENGTH_SHORT).show();
        Log.i("Error",error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(),"Registro exitoso",Toast.LENGTH_SHORT).show();
        progreso.hide();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCapturarQR:
                Intent intent = new Intent(capturarqr.this.getContext(),capturarFotoQR.class);
                startActivity(intent);
                break;
        }
    }
}
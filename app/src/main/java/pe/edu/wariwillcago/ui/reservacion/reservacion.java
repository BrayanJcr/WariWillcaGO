package pe.edu.wariwillcago.ui.reservacion;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import pe.edu.wariwillcago.R;
import pe.edu.wariwillcago.capturarFotoQR;
import pe.edu.wariwillcago.detalle_artesania;
import pe.edu.wariwillcago.ui.Entidad.Artesania;
import pe.edu.wariwillcago.ui.adapter.ArtesaniaAdapter;

public class reservacion extends Fragment implements View.OnClickListener, Response.Listener<JSONObject>,Response.ErrorListener {
    private AdView mAdView;
    EditText edtFecha, edtHora;
    Spinner edtCantNi,edtCantAdul;
    Button btnReserva;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;


    @SuppressLint("MissingPermission")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_reservacion, container, false);

        edtHora = vista.findViewById(R.id.edthora);
        edtCantNi = vista.findViewById(R.id.edtdninos);
        edtCantAdul = vista.findViewById(R.id.edtdadultos);
        edtFecha = vista.findViewById(R.id.etPlannedDate);
        btnReserva = vista.findViewById(R.id.btnReserva);
        btnReserva.setOnClickListener(this);
        edtFecha.setOnClickListener(this);


        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = vista.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        return vista;
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnReserva:
                llamarWebService();

                break;
            case R.id.etPlannedDate:
                showDatePickerDialog();
                break;
        }
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                edtFecha.setText(selectedDate);
            }
        });

        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    public void llamarWebService(){
     progreso=new ProgressDialog(getContext());
     progreso.setMessage("Consultando");
     progreso.show();

     String fecha = edtFecha.getText().toString();
    fecha=fecha.replace(" ","%20");

    Date currDate = new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    fecha = dateFormat.format(currDate);

     String url="http://152.70.136.7/wari/registroReservacion.php?"+
             "codUsuario="+1+"&fecha="+fecha+"&hora="+edtHora.getText().toString()+
        "&cantNino="+edtCantNi.getSelectedItem().toString()+
             "&cantAdultos="+edtCantAdul.getSelectedItem().toString();

     //Cod Usuario es 3 por que hay en la base de datos y ahi se asigna la sesion

     //Espacio en blanco convertido en estandar %20 espacio en blanco
     url=url.replace(" ","%20");
     System.out.println(url);
     request = Volley.newRequestQueue(getContext());
     jsonObjectRequest= new JsonObjectRequest (Request.Method.GET, url, null, this,this);
     request.add(jsonObjectRequest);
     }

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
}
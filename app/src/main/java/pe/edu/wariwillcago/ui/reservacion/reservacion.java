package pe.edu.wariwillcago.ui.reservacion;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import pe.edu.wariwillcago.R;
import pe.edu.wariwillcago.capturarFotoQR;
import pe.edu.wariwillcago.ui.capturaqr.capturarqr;


public class reservacion extends Fragment implements View.OnClickListener{
    private AdView mAdView;
    EditText edtNom, edtDni, edtFecha, edtHora;
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

        edtNom = vista.findViewById(R.id.edtnombre);
        edtDni = vista.findViewById(R.id.edtdni);
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
     //String url="http://152.70.136.7:80/anim/registroanim.php?id="
     //+edtId.getText().toString()+"&nombre="+edtNombre.getText().toString()+
     //"&caracteristicas="+edtCaracteristicas.getText().toString();

     //Espacio en blanco convertido en estandar %20 espacio en blanco
     //url=url.replace(" ","%20");

     //jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,this,this);
     //request.add(jsonObjectRequest);
     }
}
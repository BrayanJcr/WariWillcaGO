package pe.edu.wariwillcago.ui.recorrido;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import co.gofynd.gravityview.GravityView;
import pe.edu.wariwillcago.R;

public class recorrido extends Fragment {

    ImageView img360;
    GravityView gravityView;
    private boolean esSoportado=false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista=inflater.inflate(R.layout.fragment_recorrido, container, false);
        img360 = vista.findViewById(R.id.img360);
        gravityView = GravityView.getInstance(getActivity());
        if (!gravityView.deviceSupported()) {
            // show the error

        } else {
            // if device is supported then we will add the image
            // in imageview and will show the image using graviety view
            gravityView.setImage(img360, R.drawable.wari).center();
        }


        return vista;
    }

    @Override
    public void onStop() {
        super.onStop();
        gravityView.unRegisterListener();
    }

    @Override
    public void onResume() {
        super.onResume();
        gravityView.registerListener();
    }
}
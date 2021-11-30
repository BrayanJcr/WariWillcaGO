package pe.edu.wariwillcago.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pe.edu.wariwillcago.R;
import pe.edu.wariwillcago.ui.Entidad.Artesania;

public class ArtesaniaAdapter extends RecyclerView.Adapter<ArtesaniaAdapter.ArtesaniaHolder> {

    List <Artesania> listaArtesania;

    public ArtesaniaAdapter(List<Artesania> listaArtesania) {
        this.listaArtesania = listaArtesania;
    }

    @NonNull
    @Override
    public ArtesaniaHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.artesania_imagen_lista,parent,false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        return new ArtesaniaHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtesaniaHolder holder, int position) {
//enlazar con los datos del ITEM
        holder.txtId.setText(String.valueOf(listaArtesania.get(position).getId()));
        holder.txtNombre.setText(String.valueOf(listaArtesania.get(position).getNombre()));
        if (listaArtesania.get(position).getImagen() !=null)
            holder.imagen.setImageBitmap(listaArtesania.get(position).getImagen());
        else
            holder.imagen.setImageResource(R.drawable.ic_launcher_background);

    }

    @Override
    public int getItemCount() {
       return listaArtesania.size();
    }

    public class ArtesaniaHolder extends RecyclerView.ViewHolder {
        TextView txtId, txtNombre,txtDescripcion;
        ImageView imagen;
        public ArtesaniaHolder(@NonNull View itemView) {
            super(itemView);
            txtId=itemView.findViewById(R.id.pli_id);
            txtNombre=itemView.findViewById(R.id.pli_nombre);
            txtDescripcion=itemView.findViewById(R.id.pli_descripcion);
            imagen=itemView.findViewById(R.id.pli_imagen);
        }
    }
}

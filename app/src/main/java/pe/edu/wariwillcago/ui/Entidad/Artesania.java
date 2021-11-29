package pe.edu.wariwillcago.ui.Entidad;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Artesania {

    private int Id;
    private String nombre;
    private String descripcion;
    private String dato;
    private Bitmap imagen;

    public String getDato() {
        //se recibe en formato binarizado la imagen - UNICODE
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
        try {
            byte[] bytecode= Base64.decode(dato,Base64.DEFAULT);
            int alto=100;
            int ancho=100;
            Bitmap imagenO= BitmapFactory.decodeByteArray(bytecode,0,bytecode.length);
            imagen=Bitmap.createScaledBitmap(imagenO,alto,ancho,true);
        }
        catch (Exception e){
            e.printStackTrace();

        }
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public Artesania(int id, String nombre, String caracteristicas) {
        Id = id;
        this.nombre = nombre;
        this.descripcion = caracteristicas;
    }
    public Artesania() {
        Id = 0;
        this.nombre = "";
        this.descripcion = "";
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCaracteristicas() {
        return descripcion;
    }

    public void setCaracteristicas(String descripcion) {
        this.descripcion = descripcion;
    }
}

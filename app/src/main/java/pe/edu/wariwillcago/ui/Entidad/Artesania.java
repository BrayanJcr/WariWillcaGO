package pe.edu.wariwillcago.ui.Entidad;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;

public class Artesania {

    private int Id;
    private String nombre;
    private String dato;
    private Bitmap imagen;
    private String datoImUbi;
    private Bitmap imagenUbic;
    private String descripcion;


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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDatoImUbi() {
        return datoImUbi;
    }

    public void setDatoImUbi(String datoImUbi) {
        this.datoImUbi = datoImUbi;
        try {
            byte[] bytecode= Base64.decode(datoImUbi,Base64.DEFAULT);
            int alto=100;
            int ancho=100;
            Bitmap imagenO= BitmapFactory.decodeByteArray(bytecode,0,bytecode.length);
            imagenUbic=Bitmap.createScaledBitmap(imagenO,alto,ancho,true);
        }
        catch (Exception e){
            e.printStackTrace();

        }
    }

    public Bitmap getImagenUbic() {
        return imagenUbic;
    }

    public void setImagenUbic(Bitmap imagenUbic) {
        this.imagenUbic = imagenUbic;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public Artesania(int id, String nombre) {
        Id = id;
        this.nombre = nombre;
    }
    public Artesania() {
        Id = 0;
        this.nombre = "";
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

}

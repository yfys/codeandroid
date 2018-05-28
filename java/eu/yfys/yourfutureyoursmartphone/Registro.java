package eu.yfys.yourfutureyoursmartphone;


/**
 * Created by yfys on 02/12/2018.
 */

public class Registro {


    String Nombre;
    String Email;
    int Valor;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getValor() {
        return Valor;
    }

    public Registro() {
        Nombre = "";
        Email="";
        this.Valor=0;
    }

    public void setValor(int valor) {

        Valor = valor;
    }

    public Registro(String nombre, String email, int valor) {
        Nombre = nombre;
        Email = email;
        Valor = valor;
    }
}

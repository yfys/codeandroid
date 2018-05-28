package eu.yfys.yourfutureyoursmartphone;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;


/**
 * Created by yfys on 02/12/2018.
 */

public class AdaptadorBD {


    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_REGISTRO = "regisro";

    private static final String TAG = "AdaptadorBD";

    private static final String DATABASE_NAME = "gestionRegistro";
    private static final String DATABASE_TABLE = "InfoRegistro";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
            "create table "+DATABASE_TABLE+
                    "("+KEY_EMAIL+" VARCHAR(200)  primary key, "
                    +KEY_NAME+" VARCHAR(30) not null, "
                    +KEY_REGISTRO+" INT(1) not null "+");";

    private final Context context;


    private BaseDatosHelper BDHelper;
    private SQLiteDatabase bsSql;
    private String[] todasColumnas =new String[] {KEY_EMAIL,KEY_NAME,KEY_REGISTRO};

    public AdaptadorBD(Context ctx) {
        this.context = ctx;
        BDHelper = new BaseDatosHelper(context);
    }

    public AdaptadorBD open() throws SQLException{
        bsSql = BDHelper.getWritableDatabase();
        return this;
    }


    //---closes the database---
    public void close(){
        BDHelper.close();
    }

    public long insertarRegistro(String nombre, String email, int  registro){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_EMAIL, email);
        initialValues.put(KEY_NAME, nombre);
        initialValues.put(KEY_REGISTRO, registro);
        return bsSql.insert(DATABASE_TABLE, null, initialValues);
    }





    public boolean borrarRegistro(String email){
        return bsSql.delete(DATABASE_TABLE, KEY_EMAIL + "=" + email, null) > 0;
    }

    public boolean borrarTodosRegistros(){
        return bsSql.delete(DATABASE_TABLE, null, null) > 0;
    }



    public Cursor getTodosRegistros() {

        return bsSql.query(DATABASE_TABLE, todasColumnas,null,null,null,null,null);
    }


    public Cursor getRegistro(String email) throws SQLException{

        Cursor mCursor = bsSql.query(true, DATABASE_TABLE, todasColumnas,
                KEY_EMAIL + "=" + email,null,null,null,null,null);

        if (mCursor != null)  mCursor.moveToFirst();

        return mCursor;
    }




    public boolean actualizarRegistro(String email, int valor){
        ContentValues args = new ContentValues();
        args.put(KEY_EMAIL, email);
        args.put(KEY_REGISTRO, valor);

        return bsSql.update(DATABASE_TABLE, args,KEY_EMAIL + "=" + email, null) > 0;
    }


    public String MostrarRegistro(String email){
        String cadena=null;

        Cursor c = getRegistro(email);

        if (c.moveToFirst()){

            cadena=
                    "EMAIL: " + c.getString(0) + "\n" +
                            "NOMBRE: " + c.getString(1) + "\n" +
                            "VALOR: " + c.getInt(2) ;
        }

        return cadena;
    }

    public String MostrarRegistrocursor(Cursor c){
        String cadena=null;

        cadena=
                "EMAIL: " + c.getString(0) + "\n" +
                        "NOMBRE: " + c.getString(1) + "\n" +
                        "VALOR: " + c.getInt(2) ;

        return cadena;
    }

    public List<Registro> getAllRegistro() {

        List<Registro> listaRegistros = new ArrayList<Registro>();
        Cursor cursor = this.getTodosRegistros();

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Registro comment = cursorToRegistro(cursor);
            listaRegistros.add(comment);
            cursor.moveToNext();
        }
        cursor.close();
        return listaRegistros;

    }

    private Registro cursorToRegistro(Cursor cursor) {
        Registro registro = new Registro();
        registro.setEmail(cursor.getString(0));
        registro.setNombre(cursor.getString(1));
        registro.setValor(cursor.getInt(2));


        return registro;
    }


//**** CLASE PRIVADA ***/

    private static class BaseDatosHelper extends SQLiteOpenHelper{
        BaseDatosHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db)	{
            db.execSQL(DATABASE_CREATE);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion){
            Log.w(TAG, "Actualizando base de datos de la versi�n " + oldVersion
                    + " a "
                    + newVersion + ", borraremos todos los datos");
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }


}
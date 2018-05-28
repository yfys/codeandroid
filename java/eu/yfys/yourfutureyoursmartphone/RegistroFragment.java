package eu.yfys.yourfutureyoursmartphone;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

import static android.content.Context.INPUT_METHOD_SERVICE;


/**
 * Created by yfys on 02/12/2018.
 */



public class RegistroFragment extends Fragment {


    private AreaResponse responseObj;
    private KTypeResponse kResponseObj;
    private String SERVER_URL_MAIL = " ";
    public String SERVER_URL_AREA = " ";
    private String SERVER_URL_TIPO_PROYECTO = " ";
    private String SERVER_URL_TIPO_TIPO = " ";
    private String SERVER_URL_REGISTER=" ";
    private String SERVER_URL_CONDICIONES = " ";
    private Gson gsonTipodeCentro, gsonArea, gsonTipoProyecto, gsonRegistro;
    private AsyncHttpClient clientTipodeCentro, clientArea, clientTipoProyecto, clientInserta, clientPersona, clientInsertPersona, clientInsertProyecto;
    private InstitutionResponse institucionResponse;
    private RegisterResponse registroResponse;
    private PersonContactResponse personaResponse;
    private ProjectResponse proyectoResponse;
    private TypeResponse typeResponseObj;
    private ArrayAdapter arrayAdapterArea, arrayAdapterTipoProyecto, arrayAdapterTipoCentro;

    private String nombreUsuario, apellidosUsuario, pass, repetirPass, emailUsuario, emailInstitucion, nombreProyecto;
    private String nombreInstitucion, calleInstitucion, ciudadInstitucion, paisInstitucion, PIC;
    private int proyecto, area, tipodeCentro, paisarray;

    private EditText etNombreUsuario, etApellidosUsuario, etPass, etRepetirPass, etEmailUsuario, etEmailInstitucion, etRegistroNombreProyecto;
    private EditText etNombreInstitucion, etCalleInstitucion, etCiudadInstitucion, etPIC;
    private Spinner spinnerTipoArea, spinnerProyecto, spinnerTipoCentro, spinnerPais;
    private Button botonRegistrar, botonConfirmar;
    private Button informacionPIC;

    private CheckBox chkacepta;
    private boolean error;

    private AlertDialog.Builder builder;
    private ProgressDialog progressDialog;



    View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.layout_registro, container, false);


        etNombreUsuario = (EditText) view.findViewById(R.id.etRegistroNombre);
        etApellidosUsuario = (EditText) view.findViewById(R.id.etRegistroApellidos);
        etPass = (EditText) view.findViewById(R.id.etRegistroPass);
        etRepetirPass = (EditText) view.findViewById(R.id.etRegistroPassRepeat);
        etEmailUsuario = (EditText) view.findViewById(R.id.etRegistroEmail);
        etEmailInstitucion = (EditText) view.findViewById(R.id.etRegistroInstitucionEmail);
        etRegistroNombreProyecto = (EditText) view.findViewById(R.id.etRegistroProyectoNombre);

        spinnerTipoArea = (Spinner) view.findViewById(R.id.spinnerRegistroArea);
        spinnerProyecto = (Spinner) view.findViewById(R.id.spinnerRegistroTipoProyecto);
        spinnerTipoCentro = (Spinner) view.findViewById(R.id.spinnerRegistroTipoCentro);
        spinnerPais = (Spinner) view.findViewById(R.id.spinnerRegistroPais);

        etNombreInstitucion = (EditText) view.findViewById(R.id.etRegistroInstitucionNombre);
        etCalleInstitucion= (EditText) view.findViewById(R.id.etRegistroInstitucionCalle);
        etCiudadInstitucion = (EditText) view.findViewById(R.id.etRegistroInstitucionCiudad);


        botonRegistrar = (Button) view.findViewById(R.id.buttonRegistro);
        botonConfirmar = (Button) view.findViewById(R.id.botoncondicion);



        chkacepta = (CheckBox) view.findViewById(R.id.checkcondiciones);


        new Miclase(progressDialog,getActivity()).execute();



        builder = new AlertDialog.Builder(getActivity());

         clientArea = new AsyncHttpClient();

        clientArea.get(getActivity(), SERVER_URL_AREA, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String newresponse = new String(responseBody);

                Gson gsonArea = new Gson();

                 responseObj = gsonArea.fromJson(newresponse, AreaResponse.class);

                cargarSpinnerArea(responseObj.getYf_AREA());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });

        clientTipoProyecto = new AsyncHttpClient();

        clientTipoProyecto.get(getActivity().getBaseContext(), SERVER_URL_TIPO_PROYECTO, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String newresponse = new String(responseBody);

                gsonTipoProyecto = new Gson();

                kResponseObj = gsonTipoProyecto.fromJson(newresponse, KTypeResponse.class);

                cargarSpinnerTipoProyecto(kResponseObj.getYf_KTYPE());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });


        clientTipodeCentro = new AsyncHttpClient();

        clientTipodeCentro.get(getActivity().getBaseContext(), SERVER_URL_TIPO_TIPO, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String newresponse = new String(responseBody);

                gsonTipodeCentro = new Gson();

                typeResponseObj = gsonTipodeCentro.fromJson(newresponse, TypeResponse.class);

                cargarSpinnerTipoCentro(typeResponseObj.getYf_TYPE());

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });



        botonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String codLenguaje= Locale.getDefault().getLanguage();
                String page = SERVER_URL_CONDICIONES+codLenguaje+"/condiciones"+codLenguaje;
                Uri webpage = Uri.parse(page);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }

            }
        });

        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputMethodManager imm =
                        (InputMethodManager) getActivity().getBaseContext().getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
               comprobar_campos();


                area = spinnerTipoArea.getSelectedItemPosition();
                area++;

                proyecto = spinnerProyecto.getSelectedItemPosition();
                proyecto++;

                tipodeCentro=spinnerTipoCentro.getSelectedItemPosition();
                tipodeCentro++;


                nombreProyecto = etRegistroNombreProyecto.getText().toString();


                paisarray =  spinnerPais.getSelectedItemPosition();

                String[] paises = getResources().getStringArray(R.array.array_paises);

              paisInstitucion = paises[paisarray];


               if (error) {
                   insertaenbasededatos();


               }



            }
        });


        return view;

    }



    private void insertaenbasededatos(){



        String codLenguaje= Locale.getDefault().getLanguage();
        clientInserta = new AsyncHttpClient();
        RequestParams rp = new RequestParams();


        rp.put("NAME", nombreUsuario);
        rp.put("LASTNAME", apellidosUsuario);
        rp.put("AREA", ""+area);
        rp.put("EMAIL", emailUsuario);
        rp.put("PASSWORD", pass);

        rp.put("NAMEINST", nombreInstitucion);
        rp.put("STREET", calleInstitucion);
        rp.put("CITY", ciudadInstitucion);
        rp.put("COUNTRY", paisInstitucion);
        rp.put("EMAILINST", emailInstitucion);
        rp.put("TYPE", ""+tipodeCentro);
        rp.put("PIC", "000");
        rp.put("NAMEPROJECT", nombreProyecto);
        rp.put("KTYPE", ""+proyecto);
        rp.put("LANG",codLenguaje);





        clientInserta.post(SERVER_URL_REGISTER,rp, new JsonHttpResponseHandler()
        {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                String respuesta = new String(response.toString());

                gsonRegistro = new Gson();

                registroResponse = gsonRegistro.fromJson(respuesta, RegisterResponse.class);

                String mensaje = registroResponse.getMessage();
                int res = registroResponse.getSuccess();

                if (res==1){



                    muestraDialogoOK();




                }else{


                  Toast.makeText(getActivity(),getString(R.string.errorinserccion), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


              Toast.makeText(getActivity(),getString(R.string.errorinserccion), Toast.LENGTH_SHORT).show();

            }
        });


    }


    private void comprobar_campos(){
        nombreUsuario = etNombreUsuario.getText().toString();
        apellidosUsuario = etApellidosUsuario.getText().toString();
        pass = etPass.getText().toString();
        repetirPass = etRepetirPass.getText().toString();
        emailUsuario = etEmailUsuario.getText().toString();

        nombreInstitucion=etNombreInstitucion.getText().toString();
        calleInstitucion=etCalleInstitucion.getText().toString();
        ciudadInstitucion=etCiudadInstitucion.getText().toString();



        emailInstitucion = etEmailInstitucion.getText().toString();

        error =true;

        if (nombreUsuario.isEmpty()) {
            etNombreUsuario.setError(getString(R.string.errordatosvacios));
            etNombreUsuario.requestFocus();
            error =false;
            return;
        }

        if (apellidosUsuario.isEmpty()) {
            etApellidosUsuario.setError(getString(R.string.errordatosvacios));
            etApellidosUsuario.requestFocus();
            error =false;
            return;
        }

        if (pass.isEmpty()) {
            etPass.setError(getString(R.string.errordatosvacios));
            etPass.requestFocus();
            error =false;
            return;
        }

        if (repetirPass.isEmpty()) {
            etRepetirPass.setError(getString(R.string.errordatosvacios));
            etRepetirPass.requestFocus();
            error =false;
            return;
        }

        if(!pass.equals(repetirPass)) {
            etRepetirPass.setError(getString(R.string.errorpassword));
            etRepetirPass.requestFocus();
            error =false;
            return;
        }


        if(!isEmailValid(emailUsuario) || emailUsuario.isEmpty()){
            etEmailUsuario.setError(getString(R.string.errordatosemail));
            etEmailUsuario.requestFocus();
            error =false;
            return;
        }

        if (nombreInstitucion.isEmpty()) {
            etNombreInstitucion.setError(getString(R.string.errordatosvacios));
            etNombreInstitucion.requestFocus();
            error =false;
            return;
        }
        if (calleInstitucion.isEmpty()) {
            etCalleInstitucion.setError(getString(R.string.errordatosvacios));
            etCalleInstitucion.requestFocus();
            error =false;
            return;
        }
        if (ciudadInstitucion.isEmpty()) {
            etCiudadInstitucion.setError(getString(R.string.errordatosvacios));
            etCiudadInstitucion.requestFocus();
            error =false;
            return;
        }

        if(!isEmailValid(emailInstitucion) || emailInstitucion.isEmpty()){
            etEmailInstitucion.setError(getString(R.string.errordatosemail));
            etEmailInstitucion.requestFocus();
            error =false;
            return;
        }

        if (!chkacepta.isChecked()){
            chkacepta.setError(getString(R.string.erroracepta));
            chkacepta.requestFocus();
            error =false;
            return;
        }


        error=true;




    }

    private void muestraDialogoOK(){


    AlertDialog.Builder dialogo = new AlertDialog.Builder(getActivity());

    dialogo.setTitle(getString(R.string.titulodialogo));
    dialogo.setMessage(getString(R.string.textodialogo));
    dialogo.setNeutralButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {


            AcercaFragment newFragment = new AcercaFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.attach(newFragment);
            transaction.commit();


        }
    });
    dialogo.show();

    }

    public static boolean isEmailValid(String email) {
        return !(email == null || TextUtils.isEmpty(email)) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void cargarSpinnerArea(List<AreaResponse.YfAREABean> listaAreas) {
        int i;

        ArrayList<String> lista = new ArrayList<>();

        for (i = 0; i < listaAreas.size(); i++) {
            lista.add(listaAreas.get(i).getNAME());
        }

        this.arrayAdapterArea = new ArrayAdapter(getActivity().getBaseContext(), android.R.layout.simple_spinner_dropdown_item, lista);
        this.arrayAdapterArea.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        this.spinnerTipoArea.setAdapter(this.arrayAdapterArea);
    }



    private void cargarSpinnerTipoCentro(List<TypeResponse.YfTYPEBean> listaTipoCentro) {
        int i;

        ArrayList<String> lista = new ArrayList<>();

        for (i = 0; i < listaTipoCentro.size(); i++) {
            lista.add(listaTipoCentro.get(i).getNAME());
        }

        this.arrayAdapterTipoCentro = new ArrayAdapter(getActivity().getBaseContext(), android.R.layout.simple_spinner_dropdown_item, lista);
        this.arrayAdapterTipoCentro.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        this.spinnerTipoCentro.setAdapter(this.arrayAdapterTipoCentro);
    }

    private void cargarSpinnerTipoProyecto(List<KTypeResponse.YfKTYPEBean> listaTipoProyecto) {
        int i;

        ArrayList<String> lista = new ArrayList<>();

        for (i = 0; i < listaTipoProyecto.size(); i++) {
            lista.add(listaTipoProyecto.get(i).getKTYPENAME());
        }

        this.arrayAdapterTipoProyecto = new ArrayAdapter(getActivity().getBaseContext(), android.R.layout.simple_spinner_dropdown_item, lista);
        this.arrayAdapterTipoProyecto.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        this.spinnerProyecto.setAdapter(this.arrayAdapterTipoProyecto);
    }







}



 class Miclase extends AsyncTask<Void, Void, Void> {

    ProgressDialog progress;
    Context context;

    public Miclase(ProgressDialog progress, Context context) {
        this.progress = progress;
        this.context = context;
    }

    public void onPreExecute() {


        progress = new ProgressDialog(context);
        progress.setCancelable(false);
        progress.setMessage("");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();

 //aquí se puede colocar código a ejecutarse previo
//a la operación
    }

    public void onPostExecute(Void unused) {
//aquí se puede colocar código que
//se ejecutará tras finalizar
        progress.dismiss();
    }

    protected Void doInBackground(Void... params) {




        try {
            Thread.sleep(4000);
        } catch(InterruptedException e) {}
//realizar la operación aquí

        return null;
    }

}

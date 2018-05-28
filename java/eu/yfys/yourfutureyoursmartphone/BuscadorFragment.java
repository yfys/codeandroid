package eu.yfys.yourfutureyoursmartphone;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by yfys on 02/12/2018.
 */



public class BuscadorFragment extends Fragment {

    View view;

    Spinner buscadorPais, buscadorArea, buscadorTipoCentro, buscadorTipoProyecto;

    private AreaResponse responseObj;
    private KTypeResponse kResponseObj;
    private TypeResponse tipoCentroResponseObj;
    private InstitutionResponse centroResponseObj;
    private BusquedaResponse responseBuscar;
    private String SERVER_URL_AREA = "";
    private String SERVER_URL_TIPO_PROYECTO = "";
    private String SERVER_URL_TIPO_CENTRO = "";
    private String SERVER_URL_CENTRO = "";
    private Gson gsonArea, gsonTipoProyecto, gsonPais, getGsonTipoCentro, gsonBuscar;
    private AsyncHttpClient clientArea, clientTipoProyecto, clientPais, clienteTipoCentro;
    private ArrayAdapter arrayAdapterArea, arrayAdapterTipoProyecto, arrayAdapterPais, arrayAdapterInstitucion;
    ProgressDialog progressDialog;
    private Button botonBuscar;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_buscador, container, false);


        buscadorPais = (Spinner) view.findViewById(R.id.spinnerBuscadorPais);
        buscadorArea = (Spinner) view.findViewById(R.id.spinnerBuscadorArea);
        buscadorTipoCentro = (Spinner) view.findViewById(R.id.spinnerBuscadorTipoCentro);
        buscadorTipoProyecto = (Spinner) view.findViewById(R.id.spinnerBuscadorTipoProyecto);

        botonBuscar = (Button) view.findViewById(R.id.buttonBuscar);



        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestParams requestParams = new RequestParams();

                String pais = buscadorPais.getSelectedItem().toString();
                String tipoProyecto = buscadorTipoProyecto.getSelectedItem().toString();
                String tipoInstitucion = buscadorTipoCentro.getSelectedItem().toString();
                String area = buscadorArea.getSelectedItem().toString();


                Intent i = new Intent(getActivity().getApplication(), ActivityBusquedaResultado.class);
                i.putExtra("tipoInstitucion", tipoInstitucion);
                i.putExtra("tipoProyecto", tipoProyecto);
                i.putExtra("area", area);
                i.putExtra("pais", pais);
                startActivity(i);


            }
        });


        new Miclase2(progressDialog,getActivity()).execute();

        clientPais = new AsyncHttpClient();

        clientPais.get(getActivity().getBaseContext(), SERVER_URL_CENTRO, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String newresponse = new String(responseBody);

                gsonPais = new Gson();

                centroResponseObj = gsonPais.fromJson(newresponse, InstitutionResponse.class);

                cargarSpinnerPais(centroResponseObj.getYf_INSTITUTION());

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });

        clienteTipoCentro = new AsyncHttpClient();

        clienteTipoCentro.get(getActivity().getBaseContext(), SERVER_URL_TIPO_CENTRO, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String newresponse = new String(responseBody);

                getGsonTipoCentro = new Gson();

                tipoCentroResponseObj = getGsonTipoCentro.fromJson(newresponse, TypeResponse.class);

                cargarSpinnerTipoCentro(tipoCentroResponseObj.getYf_TYPE());

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

        clientArea = new AsyncHttpClient();

        clientArea.get(getActivity().getBaseContext(), SERVER_URL_AREA, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String newresponse = new String(responseBody);

                gsonArea = new Gson();

                responseObj = gsonArea.fromJson(newresponse, AreaResponse.class);

                cargarSpinnerArea(responseObj.getYf_AREA());

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });

        return view;
    }

    private void cargarSpinnerArea(List<AreaResponse.YfAREABean> listaAreas) {
        int i;

        ArrayList<String> lista = new ArrayList<>();
        lista.add("ALL");
        for (i = 0; i < listaAreas.size(); i++) {
            lista.add(listaAreas.get(i).getNAME());
        }

        this.arrayAdapterArea = new ArrayAdapter(getActivity().getBaseContext(), android.R.layout.simple_spinner_dropdown_item, lista);
        this.arrayAdapterArea.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        this.buscadorArea.setAdapter(this.arrayAdapterArea);
    }

    private void cargarSpinnerTipoProyecto(List<KTypeResponse.YfKTYPEBean> listaTipoProyecto) {
        int i;

        ArrayList<String> lista = new ArrayList<>();
        lista.add("ALL");
        for (i = 0; i < listaTipoProyecto.size(); i++) {
            lista.add(listaTipoProyecto.get(i).getKTYPENAME());
        }

        this.arrayAdapterTipoProyecto = new ArrayAdapter(getActivity().getBaseContext(), android.R.layout.simple_spinner_dropdown_item, lista);
        this.arrayAdapterTipoProyecto.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        this.buscadorTipoProyecto.setAdapter(this.arrayAdapterTipoProyecto);
    }

    private void cargarSpinnerPais(List<InstitutionResponse.YfINSTITUTIONBean> listaCentros) {
        int i;

        ArrayList<String> lista = new ArrayList<>();
        lista.add("ALL");
        for (i = 0; i < listaCentros.size(); i++) {
            lista.add(listaCentros.get(i).getCOUNTRY());
        }

        this.arrayAdapterPais = new ArrayAdapter(getActivity().getBaseContext(), android.R.layout.simple_spinner_dropdown_item, lista);
        this.arrayAdapterPais.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        this.buscadorPais.setAdapter(this.arrayAdapterPais);
    }

    private void cargarSpinnerTipoCentro(List<TypeResponse.YfTYPEBean> listaTiposCentro) {
        int i;

        ArrayList<String> lista = new ArrayList<>();
        lista.add("ALL");
        for (i = 0; i < listaTiposCentro.size(); i++) {
            lista.add(listaTiposCentro.get(i).getNAME());
        }

        this.arrayAdapterInstitucion = new ArrayAdapter(getActivity().getBaseContext(), android.R.layout.simple_spinner_dropdown_item, lista);
        this.arrayAdapterInstitucion.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        this.buscadorTipoCentro.setAdapter(this.arrayAdapterInstitucion);
    }




}



class Miclase2 extends AsyncTask<Void, Void, Void> {

    ProgressDialog progress;
    Context context;

    public Miclase2(ProgressDialog progress, Context context) {
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

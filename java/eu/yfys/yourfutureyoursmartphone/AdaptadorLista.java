package eu.yfys.yourfutureyoursmartphone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by yfys on 02/12/2018.
 */

public class AdaptadorLista extends BaseAdapter {

    private List<BusquedaResponse.YfRESULTADOBean> listaConsulta;
    private Context contexto;

    public AdaptadorLista(Context contexto, List<BusquedaResponse.YfRESULTADOBean> listaResultado) {
        this.contexto = contexto;
        this.listaConsulta = listaResultado;
    }

    @Override
    public int getCount() {
        return this.listaConsulta.size();
    }

    @Override
    public Object getItem(int i) {
        return this.listaConsulta.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflador = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rootView =   inflador.inflate(R.layout.elemento_listar, null);

        BusquedaResponse.YfRESULTADOBean item = (BusquedaResponse.YfRESULTADOBean) getItem(i);

        ImageView imagen = (ImageView) rootView.findViewById(R.id.imageView7);
        TextView texto1 = (TextView) rootView.findViewById(R.id.tvElementoLista1);
        TextView texto2 = (TextView) rootView.findViewById(R.id.tvElementoLista2);
        TextView texto3 = (TextView) rootView.findViewById(R.id.tvElementoLista3);
        TextView texto4 = (TextView) rootView.findViewById(R.id.tvElementoLista4);
        TextView texto5 = (TextView) rootView.findViewById(R.id.tvElementoLista5);
        TextView texto6 = (TextView) rootView.findViewById(R.id.tvElementoLista6);


        texto1.setText(item.getCOUNTRY());
        texto2.setText(item.getNAMECENTER());
        texto3.setText(item.getTYPE());
        texto4.setText(item.getAREA());
        texto5.setText(item.getKTYPE());
        texto6.setText(item.getNOMBRE());


        imagen.setImageResource(R.drawable.sendmail);

        return rootView ;
    }



}

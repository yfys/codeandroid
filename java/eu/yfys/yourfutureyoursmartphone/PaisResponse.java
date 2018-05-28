package eu.yfys.yourfutureyoursmartphone;

import java.util.List;


/**
 * Created by yfys on 02/12/2018.
 */

public class PaisResponse {




    private int success;
    private List<PAISESBean> PAISES;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<PAISESBean> getPAISES() {
        return PAISES;
    }

    public void setPAISES(List<PAISESBean> PAISES) {
        this.PAISES = PAISES;
    }

    public static class PAISESBean {

        private String PAIS;

        public String getPAIS() {
            return PAIS;
        }

        public void setPAIS(String PAIS) {
            this.PAIS = PAIS;
        }
    }
}

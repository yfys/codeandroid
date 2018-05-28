package eu.yfys.yourfutureyoursmartphone;

import java.util.List;


/**
 * Created by yfys on 02/12/2018.
 */

public class BusquedaResponse {



    private int success;
    private List<YfRESULTADOBean> yf_RESULTADO;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<YfRESULTADOBean> getYf_RESULTADO() {
        return yf_RESULTADO;
    }

    public void setYf_RESULTADO(List<YfRESULTADOBean> yf_RESULTADO) {
        this.yf_RESULTADO = yf_RESULTADO;
    }

    public static class YfRESULTADOBean {


        private String COUNTRY;
        private String NAMECENTER;
        private String NOMBRE;
        private String TYPE;
        private String AREA;
        private String KTYPE;
        private String IDPERSON;

        public String getCOUNTRY() {
            return COUNTRY;
        }

        public void setCOUNTRY(String COUNTRY) {
            this.COUNTRY = COUNTRY;
        }

        public String getNAMECENTER() {
            return NAMECENTER;
        }

        public void setNAMECENTER(String NAMECENTER) {
            this.NAMECENTER = NAMECENTER;
        }

        public String getNOMBRE() {
            return NOMBRE;
        }

        public void setNOMBRE(String NOMBRE) {
            this.NOMBRE = NOMBRE;
        }

        public String getTYPE() {
            return TYPE;
        }

        public void setTYPE(String TYPE) {
            this.TYPE = TYPE;
        }

        public String getAREA() {
            return AREA;
        }

        public void setAREA(String AREA) {
            this.AREA = AREA;
        }

        public String getKTYPE() {
            return KTYPE;
        }

        public void setKTYPE(String KTYPE) {
            this.KTYPE = KTYPE;
        }

        public String getIDPERSON() {
            return IDPERSON;
        }

        public void setIDPERSON(String IDPERSON) {
            this.IDPERSON = IDPERSON;
        }
    }
}

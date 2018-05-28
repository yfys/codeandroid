package eu.yfys.yourfutureyoursmartphone;

import java.util.List;


/**
 * Created by yfys on 02/12/2018.
 */


public class TypeResponse {


    private int success;
    private List<YfTYPEBean> yf_TYPE;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<YfTYPEBean> getYf_TYPE() {
        return yf_TYPE;
    }

    public void setYf_TYPE(List<YfTYPEBean> yf_TYPE) {
        this.yf_TYPE = yf_TYPE;
    }

    public static class YfTYPEBean {


        private int ID;
        private String NAME;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }
    }
}

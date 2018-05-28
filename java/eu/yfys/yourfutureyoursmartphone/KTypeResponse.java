package eu.yfys.yourfutureyoursmartphone;

import java.util.List;


/**
 * Created by yfys on 02/12/2018.
 */


public class KTypeResponse {



    private int success;
    private List<YfKTYPEBean> yf_KTYPE;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<YfKTYPEBean> getYf_KTYPE() {
        return yf_KTYPE;
    }

    public void setYf_KTYPE(List<YfKTYPEBean> yf_KTYPE) {
        this.yf_KTYPE = yf_KTYPE;
    }

    public static class YfKTYPEBean {

        private int ID;
        private String KTYPENAME;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getKTYPENAME() {
            return KTYPENAME;
        }

        public void setKTYPENAME(String KTYPENAME) {
            this.KTYPENAME = KTYPENAME;
        }
    }
}

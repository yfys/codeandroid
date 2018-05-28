package eu.yfys.yourfutureyoursmartphone;

import java.util.List;


/**
 * Created by yfys on 02/12/2018.
 */

public class ProjectResponse {



    private int success;
    private List<YfPROJECTBean> yf_PROJECT;



    private String message;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<YfPROJECTBean> getYf_PROJECT() {
        return yf_PROJECT;
    }

    public void setYf_PROJECT(List<YfPROJECTBean> yf_PROJECT) {
        this.yf_PROJECT = yf_PROJECT;
    }

    public static class YfPROJECTBean {

        private int ID;
        private String NAME;
        private String DESCRIPTION;
        private int IDCONTACT;
        private int KTYPE;

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

        public String getDESCRIPTION() {
            return DESCRIPTION;
        }

        public void setDESCRIPTION(String DESCRIPTION) {
            this.DESCRIPTION = DESCRIPTION;
        }

        public int getIDCONTACT() {
            return IDCONTACT;
        }

        public void setIDCONTACT(int IDCONTACT) {
            this.IDCONTACT = IDCONTACT;
        }

        public int getKTYPE() {
            return KTYPE;
        }

        public void setKTYPE(int KTYPE) {
            this.KTYPE = KTYPE;
        }
    }
}

package eu.yfys.yourfutureyoursmartphone;

import java.util.List;


/**
 * Created by yfys on 02/12/2018.
 */

public class PersonContactResponse {



    private int success;
    private List<YfCONTACTPERSONBean> yf_CONTACTPERSON;



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

    public List<YfCONTACTPERSONBean> getYf_CONTACTPERSON() {
        return yf_CONTACTPERSON;
    }

    public void setYf_CONTACTPERSON(List<YfCONTACTPERSONBean> yf_CONTACTPERSON) {
        this.yf_CONTACTPERSON = yf_CONTACTPERSON;
    }

    public static class YfCONTACTPERSONBean {


        private int ID;
        private String EMAIL;
        private String NAME;
        private String LASTNAME;
        private int CONFIRMEMAIL;
        private int CONFIRMEMAILINST;
        private int AREA;
        private String PASSWORD;
        private int IDINST;


        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getEMAIL() {
            return EMAIL;
        }

        public void setEMAIL(String EMAIL) {
            this.EMAIL = EMAIL;
        }

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public String getLASTNAME() {
            return LASTNAME;
        }

        public void setLASTNAME(String LASTNAME) {
            this.LASTNAME = LASTNAME;
        }

        public int getCONFIRMEMAIL() {
            return CONFIRMEMAIL;
        }

        public void setCONFIRMEMAIL(int CONFIRMEMAIL) {
            this.CONFIRMEMAIL = CONFIRMEMAIL;
        }

        public int getCONFIRMEMAILINST() {
            return CONFIRMEMAILINST;
        }

        public void setCONFIRMEMAILINST(int CONFIRMEMAILINST) {
            this.CONFIRMEMAILINST = CONFIRMEMAILINST;
        }

        public int getAREA() {
            return AREA;
        }

        public void setAREA(int AREA) {
            this.AREA = AREA;
        }

        public String getPASSWORD() {
            return PASSWORD;
        }

        public void setPASSWORD(String PASSWORD) {
            this.PASSWORD = PASSWORD;
        }

        public int getIDINST() {
            return IDINST;
        }

        public void setIDINST(int IDINST) {
            this.IDINST = IDINST;
        }


    }
}

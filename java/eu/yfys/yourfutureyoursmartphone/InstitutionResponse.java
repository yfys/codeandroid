package eu.yfys.yourfutureyoursmartphone;

import java.util.List;

/**
 * Created by yfys on 02/12/2018.
 */

public class InstitutionResponse {



    private int success;
    private List<YfINSTITUTIONBean> yf_INSTITUTION;

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

    public List<YfINSTITUTIONBean> getYf_INSTITUTION() {
        return yf_INSTITUTION;
    }

    public void setYf_INSTITUTION(List<YfINSTITUTIONBean> yf_INSTITUTION) {
        this.yf_INSTITUTION = yf_INSTITUTION;
    }

    public static class YfINSTITUTIONBean {


        private int ID;
        private String EMAIL;
        private String NAME;
        private String STREET;
        private String CITY;
        private String COUNTRY;
        private int TYPE;

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

        public String getSTREET() {
            return STREET;
        }

        public void setSTREET(String STREET) {
            this.STREET = STREET;
        }

        public String getCITY() {
            return CITY;
        }

        public void setCITY(String CITY) {
            this.CITY = CITY;
        }

        public String getCOUNTRY() {
            return COUNTRY;
        }

        public void setCOUNTRY(String COUNTRY) {
            this.COUNTRY = COUNTRY;
        }

        public int getTYPE() {
            return TYPE;
        }

        public void setTYPE(int TYPE) {
            this.TYPE = TYPE;
        }
    }
}

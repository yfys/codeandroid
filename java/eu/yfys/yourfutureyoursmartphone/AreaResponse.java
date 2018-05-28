package eu.yfys.yourfutureyoursmartphone;

import java.util.List;


/**
 * Created by yfys on 02/12/2018.
 */

public class AreaResponse {



    private int success;
    private List<YfAREABean> yf_AREA;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<YfAREABean> getYf_AREA() {
        return yf_AREA;
    }

    public void setYf_AREA(List<YfAREABean> yf_AREA) {
        this.yf_AREA = yf_AREA;
    }

    public class YfAREABean {


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

package eu.yfys.yourfutureyoursmartphone;

public class BajaResponse {

    /**
     * success : 0
     * message : Required field(s) is missing
     */

    private int success;
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
}

package Utils;

import java.io.Serializable;

public class Response implements Serializable {

    private Object payload;
    private String message;
    private ResponseCode responseCode;

    public Response(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }
}

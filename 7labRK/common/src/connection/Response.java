package connection;

import java.io.Serializable;

public class Response implements Serializable {
    private String message;
    private String user;
    private String pswd;

    //new arguments user and user_password

    public Response(String message, String user, String pswd){
        this.message = message;
        this.user = user;
        this.pswd = pswd;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }
}

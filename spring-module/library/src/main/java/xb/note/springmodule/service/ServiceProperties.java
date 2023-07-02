package xb.note.springmodule.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("service")
public class ServiceProperties {

    private String message;

    public String getMessage(){
        return this.message;
    }

    public void setMessage(String message){
        this.message = message;
    }

}

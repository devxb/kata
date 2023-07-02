package xb.note.controller.user.request;

import javax.validation.constraints.NotEmpty;

public class UserSaveRequest {

    @NotEmpty
    private String name;

    public UserSaveRequest(){}

    public UserSaveRequest(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

}

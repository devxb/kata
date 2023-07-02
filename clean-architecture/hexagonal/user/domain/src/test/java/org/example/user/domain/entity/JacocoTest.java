package org.example.user.domain.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JacocoTest{

    @Test
    @DisplayName("Jacoco reports가 만들어지나?")
    public void JACOCO_REPORTS_MAKE_TEST(){

        User user = new User(1, "Jacoco");
    }

    @Test
    public void MULTI_BRANCH_IS_OK(){
        User user = new User(1, "MULTI");
    }
    
    @Test
    public void 왜_안됨(){
        User user = new User(1, "돼라..");
    }
    
    @Test
    public void 안됨(){
        User user = new User(1, "안되냐왜");
    }

}

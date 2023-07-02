package org.example.persistence;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JacocoOnRepositoryTest{

    @Test
    @DisplayName("멀티 모듈 Jacoco 테스트")
    void TEST_JACOCO_ON_MULTI_MODULE_TEST(){
        User user = new User("hello");
    }

}

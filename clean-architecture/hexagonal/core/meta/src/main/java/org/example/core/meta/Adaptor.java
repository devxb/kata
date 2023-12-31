package org.example.core.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.stereotype.Service;

@Service
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Adaptor{
}

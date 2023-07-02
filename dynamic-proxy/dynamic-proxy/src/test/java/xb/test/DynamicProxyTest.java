package xb.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class DynamicProxyTest{

    @Test
    void DYNAMIC_PROXY_TEST(){
        // given
        DynamicFoo dynamicFoo = (DynamicFoo) Proxy.newProxyInstance(DynamicProxyTest.class.getClassLoader(),
                new Class[] {DynamicFoo.class},
                new InvocationHandlerImpl<>(new DynamicFooImpl()));

        // when
        String result = dynamicFoo.hello("is proxy?");

        // then
        System.out.println(result);
    }

    static class InvocationHandlerImpl<T> implements InvocationHandler{

        private final Map<String, Method> methodMap;
        private final T target;

        private InvocationHandlerImpl(T target){
            methodMap = new HashMap<>();
            Arrays.stream(target.getClass().getMethods())
                    .forEach(m -> methodMap.put(m.getName(), m));
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
            try{
                String ans = (String) method.invoke(target, args);
                return "i'm proxy " + ans;
            } catch (Exception e){
                throw new UnsupportedOperationException("Can not invoke method \"" + method.getName() + "\" cause " + e.getMessage());
            }
        }

    }

}



package xb.note.protos;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ProtoTest {

    @Test
    public void buildProtoFileTest(){
        // given
        Person xb = buildXb();

        // then
        Assertions.assertTrue(isWho(buildXb(), xb));
    }

    @Test
    public void buildProtoFileFailTest(){
        // given
        Person xb = buildXb();

        // then
        Assertions.assertFalse(isWho(buildDev(), xb));
    }

    @Test
    public void serializeDiffNoneSerializeTest(){
        long diff = 2;
        for(int i = 0; i < 100; i++){
            long noneSeriallizedCount = countNoneSerializedPerson();
            long serializedCount = countSerializedPerson();
            Assertions.assertTrue(serializedCount > noneSeriallizedCount + diff);
            System.out.println("\n\n" + diff + "\n\n");
            diff *= 2;
        }
    }

    private boolean isWho(Person expected, Person actual){
        return expected.equals(actual);
    }

    private int countNoneSerializedPerson(){
        int count = 0;
        List<Person> personList = new ArrayList<>(); // gc 방지 용
        try{
            while(true){
                Person xb = buildXb();
                personList.add(xb);
                count++;
            }
        }catch(OutOfMemoryError OOME){return count;}
    }

    private int countSerializedPerson(){
        int count = 0;
        List<byte[]> serializedPersonList = new ArrayList<>(); // gc 방지용
        try{
            while(true) {
                Person xb = buildXb();
                serializedPersonList.add(xb.toByteArray());
                count++;
            }
        }catch(OutOfMemoryError OOME){ return count; }
    }

    private Person buildXb(){
        return Person.newBuilder()
                .setName("xb")
                .setId(1)
                .setEmail("develxb@gmail.com")
                .addPhones(Person.PhoneNumber.newBuilder()
                        .setNumber("1234-5678")
                        .setType(Person.PhoneType.MOBILE))
                .build();
    }

    private Person buildDev(){
        return Person.newBuilder()
                .setName("dev")
                .setId(2)
                .setEmail("dev@gmail.com")
                .addPhones(Person.PhoneNumber.newBuilder()
                        .setNumber("8765-4321")
                        .setType(Person.PhoneType.WORK))
                .build();
    }

}

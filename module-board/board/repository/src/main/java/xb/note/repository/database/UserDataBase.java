package xb.note.repository.database;

import xb.note.domain.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class UserDataBase {

    public static final Map<String, User> users;
    public static AtomicInteger autoIncrementUserId;
    static {
        users = new HashMap<>();
        autoIncrementUserId = new AtomicInteger(1);
    }

}

package secexample.db.enumerations;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public enum Role {

    EMPLOYEE("EMPLOYEE",1),
    HR("HR",1),
    ADMINISTRATOR("ADMINISTRATOR",0),
    AUDITOR("AUDITOR",1);

    public final String code;

    @Getter
    private final int group;

    Role(String code, int group) {
        this.code = code;
        this.group = group;
    }
    private static final Map<String, Role> BY_CODE = new HashMap<>();

    static {
        for (Role e: values()) {
            BY_CODE.put(e.code, e);
        }
    }

    public static Role roleByCode(String code) {
        return BY_CODE.get(code);
    }

}

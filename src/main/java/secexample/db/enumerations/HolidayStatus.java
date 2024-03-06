package secexample.db.enumerations;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public enum HolidayStatus {

    REQUESTED("REQUESTED","Requested"),
    APPROVED("APPROVED","Approved"),
    REJECTED("REJECTED","Rejected"),
    EXPIRED("EXPIRED","Expired"),
    ;


    public final String code;

    @Getter
    private final String label;


    HolidayStatus(String code, String label) {
        this.code = code;
        this.label = label;
    }


    private static final Map<String, HolidayStatus> BY_CODE = new HashMap<>();

    static {
        for (HolidayStatus e: values()) {
            BY_CODE.put(e.code, e);
        }
    }

    public static HolidayStatus holidayStatusByCode(String code) {
        return BY_CODE.get(code);
    }
}

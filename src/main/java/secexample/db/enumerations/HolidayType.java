package secexample.db.enumerations;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public enum HolidayType {

    HOLIDAYS("HOLIDAYS","Holidays", true, true),
    HEALTH_CHECK("HEALTH_CHECK","Health Check", true, false),
    PAID_LEAVE("PAID_LEAVE","Paid Leave", true, false),
    UNPAID_LEAVE("UNPAID_LEAVE","Unpaid Leave", false, true),
    SICK_LEAVE("SICK_LEAVE","Sick Leave", false, true),
    PARENTAL_LEAVE("PARENTAL_LEAVE","Parental Leave", false, false);


    public final String code;

    @Getter
    private final String label;

    @Getter
    private final boolean deductible;

    @Getter
    private final boolean multipleDays;

    HolidayType(String code, String label, boolean deductible, boolean multipleDays) {
        this.code = code;
        this.label = label;
        this.deductible = deductible;
        this.multipleDays = multipleDays;
    }


    private static final Map<String, HolidayType> BY_CODE = new HashMap<>();

    static {
        for (HolidayType e: values()) {
            BY_CODE.put(e.code, e);
        }
    }

    public static HolidayType holidayTypeByCode(String code) {
        return BY_CODE.get(code);
    }
}

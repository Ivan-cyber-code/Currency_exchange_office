package model;

public class Currency {
    private int id;
    private String code;
    private String fullName;
    private String sign;

    public Currency(int id, String code, String fullName, String sign) {
        this.id = id;
        this.code = code;
        this.fullName = fullName;
        this.sign = sign;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSign() {
        return sign;
    }

    @Override
    public String toString() {
        return String.format("""
                {
                "id" : %s,
                "name" : "%s",
                "code" : "%s",
                sign : "%s"
                }""", getId(), getFullName(), getCode(), getSign());
    }
}


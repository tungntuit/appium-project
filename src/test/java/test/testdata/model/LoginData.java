package test.testdata.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginData {
    public String sdt;
    public String mpass;
    public String expectedResult;
    public String errorMessage;
}

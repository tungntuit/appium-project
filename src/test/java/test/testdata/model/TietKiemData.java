package test.testdata.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TietKiemData {
    public String source;
    public String amount;
    public String term;
    public String renewalType;
}

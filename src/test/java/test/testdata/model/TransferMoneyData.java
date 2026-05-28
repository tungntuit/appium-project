package test.testdata.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferMoneyData {
    public String source;
    public String amount;
    public String description;
    public String receiver;
    public String bank;
}

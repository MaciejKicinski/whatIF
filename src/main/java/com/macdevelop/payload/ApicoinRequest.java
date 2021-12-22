package com.macdevelop.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ApicoinRequest {

    @JsonProperty("time")
    String time;

    @JsonProperty("asset_id_base")
    String assetIdBase;

    @JsonProperty("asset_id_quote")
    String assetIdQuote;

    @JsonProperty("rate")
    double rate;
}

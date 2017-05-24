package com.juharin.adaptedapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.*;

public class AdaptedRequest {
    @JsonProperty("datetime")
    public ZonedDateTime datetime;
    @JsonProperty("api_key")
    public String apiKey;
    @JsonProperty("request_id")
    public String requestId;
    @JsonProperty("method")
    public String method;
    @JsonProperty("value")
    public String value;
}

package com.juharin.adaptedapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.*;

public class AdaptedResponse {
    @JsonProperty("datetime")
    public ZonedDateTime datetime;
    @JsonProperty("status")
    public String status;
    @JsonProperty("request_id")
    public String requestId;
}

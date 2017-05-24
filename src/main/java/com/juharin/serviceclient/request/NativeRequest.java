package com.juharin.serviceclient.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.*;

public class NativeRequest {
    @JsonProperty("timestamp")
    public ZonedDateTime timestamp;
    @JsonProperty("user_id")
    public String userId;
    @JsonProperty("message_id")
    public String messageId;
    @JsonProperty("operation")
    public String operation;
    @JsonProperty("value")
    public String value;
}

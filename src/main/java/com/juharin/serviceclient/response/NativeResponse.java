package com.juharin.serviceclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.*;

public class NativeResponse {
    @JsonProperty("timestamp")
    public ZonedDateTime timestamp;
    @JsonProperty("message_id")
    public String messageId;
    @JsonProperty("success")
    public Boolean success;
    @JsonProperty("status_code")
    public String statusCode;
    @JsonProperty("status_message")
    public String statusMessage;
}

package api;

import lombok.Data;

@Data
public class ResponseMessage {
    String message;
    int id;
    int errorCode;
}

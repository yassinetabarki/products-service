package micro.service.products.Responses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class ResponseHandler {

    public static ResponseEntity<Object> responseHandler(String message, HttpStatus httpCode, Object data) {
        HashMap<String, Object> response = getStringObjectHashMap(message, httpCode, data);
        return new ResponseEntity<>(response, httpCode);
    }

    public static ResponseEntity<Object> responseHandler(String message, HttpStatus httpCode) {
        HashMap<String, Object> response = getStringObjectHashMap(message, httpCode, null);
        return new ResponseEntity<>(response, httpCode);
    }

    public static ResponseEntity<Object> responseHandler(HttpStatus httpCode,Object data) {
        HashMap<String, Object> response = getStringObjectHashMap(null, httpCode, data);
        return new ResponseEntity<>(response, httpCode);
    }

    private static HashMap<String, Object> getStringObjectHashMap(String message, HttpStatus httpCode, Object data) {
        HashMap<String, Object> response = new HashMap<>();
        if (message != null) {
            response.put("message", message);
        }
        response.put("status", httpCode.value());
        if (data != null) {
            response.put("data", data);
        }
        return response;
    }
}

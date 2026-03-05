package api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataTransferWrapper<T> {
    private int statusCode;
    private T dto;

    public DataTransferWrapper(int statusCode, T dto) {
        this.statusCode = statusCode;
        this.dto = dto;
    }
}

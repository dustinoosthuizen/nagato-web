package com.webward.web.rest.dto;

/**
 * Created by dustinosthzn on 2014/09/26.
 */
public class BooleanDTO {
    public boolean value;
    public BooleanDTO(boolean value)
    {
        this.value = value;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}

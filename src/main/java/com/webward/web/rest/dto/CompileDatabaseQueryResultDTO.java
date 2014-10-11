package com.webward.web.rest.dto;

import java.util.List;

/**
 * Created by dustinosthzn on 2014/09/28.
 */
public class CompileDatabaseQueryResultDTO {



    public List<String> attributes;
    public boolean success;
    public boolean message;

    public List<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isMessage() {
        return message;
    }

    public void setMessage(boolean message) {
        this.message = message;
    }
}

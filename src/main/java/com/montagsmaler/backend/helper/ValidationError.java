package com.montagsmaler.backend.helper;

import java.util.ArrayList;
import java.util.List;

public class ValidationError {
    private List<String> errorFields;

    public ValidationError(List<String> errorFields) {
        this.errorFields = errorFields;
    }

    public List<String> getErrorFields() {
        return errorFields;
    }

    public void setErrorFields(List<String> errorFields) {
        this.errorFields = errorFields;
    }
}


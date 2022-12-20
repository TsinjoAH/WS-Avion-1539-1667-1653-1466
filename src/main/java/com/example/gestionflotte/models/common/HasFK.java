package com.example.gestionflotte.models.common;

import com.example.gestionflotte.exception.CustomValidation;

public interface HasFK<FK> extends HasID{
    void setFK(FK fk) throws CustomValidation;

}

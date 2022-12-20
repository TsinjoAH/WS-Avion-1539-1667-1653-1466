package com.example.gestionflotte.models.receiver;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.binary.Base64;

@Getter
@Setter
public class ImgReceiver {
    private String img;

    public byte[] getByte() {
        return img.getBytes();
    }

}

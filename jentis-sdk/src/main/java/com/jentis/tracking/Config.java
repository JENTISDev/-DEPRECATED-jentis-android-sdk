package com.jentis.tracking;

import androidx.annotation.RestrictTo;

import com.jentis.tracking.model.enums.Action;
import com.jentis.tracking.model.enums.DocumentType;

@RestrictTo(RestrictTo.Scope.LIBRARY)
public class Config {
    static String keyPrefix = "at.allaboutapps";
    static Action ACTION;
    static DocumentType DOC_TYPE;
}

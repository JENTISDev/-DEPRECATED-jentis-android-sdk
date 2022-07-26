package com.jentis.tracking.sdk.model.interfaces;

import com.jentis.tracking.sdk.model.JentisException;

public interface ResultHandler<T> {
    void onSuccess(T data);
    void onFailure(JentisException e);
}

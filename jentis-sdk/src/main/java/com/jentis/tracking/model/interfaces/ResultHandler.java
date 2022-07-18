package com.jentis.tracking.model.interfaces;

import com.jentis.tracking.model.JentisException;

public interface ResultHandler<T> {
    void onSuccess(T data);
    void onFailure(JentisException e);
}

package com.jentis.analytics.model.interfaces;

import com.jentis.analytics.model.JentisException;

public interface ResultHandler<T> {
    void onSuccess(T data);
    void onFailure(JentisException e);
}

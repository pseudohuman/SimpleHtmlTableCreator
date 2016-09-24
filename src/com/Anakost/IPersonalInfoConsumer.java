package com.Anakost;

/**
 * Created by Анатолій on 24.09.2016.
 */
public interface IPersonalInfoConsumer {
    void append(PersonalInfo info);
    void setHeaders(String[] headers);
}

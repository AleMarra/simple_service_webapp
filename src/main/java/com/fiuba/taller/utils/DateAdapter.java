package com.fiuba.taller.utils;

import javax.ws.rs.WebApplicationException;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter extends XmlAdapter<String, Date> {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public String marshal(Date v) {
        return dateFormat.format(v);
    }

    @Override
    public Date unmarshal(String v) {
        try {
            return dateFormat.parse(v);
        } catch (ParseException e) {
            throw new WebApplicationException();
        }
    }
}

package com.fin.bancs.BP;

import java.io.Serializable;
import java.util.Objects;

public class Cust_Address_detailsPk implements Serializable {
    protected int PIN_CODE;
    protected int ADDRESS_ID;

    public int getPIN_CODE() {
        return PIN_CODE;
    }

    public void setPIN_CODE(int PIN_CODE) {
        this.PIN_CODE = PIN_CODE;
    }

    public int getADDRESS_ID() {
        return ADDRESS_ID;
    }

    public void setADDRESS_ID(int ADDRESS_ID) {
        this.ADDRESS_ID = ADDRESS_ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cust_Address_detailsPk that = (Cust_Address_detailsPk) o;
        return PIN_CODE == that.PIN_CODE && ADDRESS_ID == that.ADDRESS_ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(PIN_CODE, ADDRESS_ID);
    }
}

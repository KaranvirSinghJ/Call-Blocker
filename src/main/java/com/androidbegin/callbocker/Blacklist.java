package com.androidbegin.callbocker;

public class Blacklist {

    public long id;
    public String phoneNumber;

    public Blacklist() {

    }

    public Blacklist(final String phoneMumber) {
        this.phoneNumber = phoneMumber;
    }

    @Override
    public boolean equals(final Object obj) {

        if(obj.getClass().isInstance(new Blacklist()))
        {

            final Blacklist bl = (Blacklist) obj;

            if(bl.phoneNumber.equalsIgnoreCase(this.phoneNumber))
                return true;
        }
        return false;
    }
}
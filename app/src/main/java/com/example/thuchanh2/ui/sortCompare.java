package com.example.thuchanh2.ui;

import java.util.Comparator;
import java.util.Date;

class sortCompare extends Date implements Comparator<Date>
{
    // Method of this class
    @Override
    public int compare(Date a, Date b)
    {
        /* Returns sorted data in ascending order */
        return a.compareTo(b);
    }

    @Override
    public Comparator<Date> reversed() {
        return null;
    }
}
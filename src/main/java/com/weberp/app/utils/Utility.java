package com.weberp.app.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by claudioruiz on 6/14/17.
 */
public class Utility {

    //check duplicated value
    public static boolean checkDuplicated_withSet(Long[] sValueTemp)
    {
        Set<Long> sValueSet = new HashSet<Long>();
        for(Long tempValueSet : sValueTemp)
        {
            if (sValueSet.contains(tempValueSet))
                return true;
            else
            if(!tempValueSet.equals(""))
                sValueSet.add(tempValueSet);
        }
        return false;
    }

}

package com.randomappsinc.instafood.constants;

import android.support.annotation.StringDef;

@StringDef({
        DistanceUnit.MILES,
        DistanceUnit.KILOMETERS,
})
public @interface DistanceUnit {
    String MILES = "miles";
    String KILOMETERS = "kilometers";
}

package com.example.work_out_.sensors;

import android.location.Location;

public class CLocation extends Location {

    private boolean bUseMetricUnits = false;

    public CLocation(Location location){
        this(location,true);
    }

    public CLocation(Location location, boolean bUseMetricsUnits){
        super(location);
        this.bUseMetricUnits = bUseMetricsUnits;
    }

    public boolean getUseMetricsUnits(){
        return this.bUseMetricUnits;
    }

    public CLocation(String provider){
        super(provider);
    }
}

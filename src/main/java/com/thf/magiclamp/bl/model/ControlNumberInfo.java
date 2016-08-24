package com.thf.magiclamp.bl.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ControlNumberInfo extends ControlInfo {
    private int minValue;
    private int maxValue;

    public ControlNumberInfo(String name, int minValue, int maxValue) {
        super("number", name);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

}

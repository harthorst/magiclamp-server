package com.thf.magiclamp.bl.impl;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.thf.magiclamp.bl.IEffectConfig;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RainbowEffectConfig implements IEffectConfig {
    public enum Color {
        RED, GREEN, BLUE
    }

    @Min(0)
    @Max(10)
    private int speed = 0;

    @Min(0)
    @Max(1)
    private int active;

    private Color color = Color.GREEN;
}

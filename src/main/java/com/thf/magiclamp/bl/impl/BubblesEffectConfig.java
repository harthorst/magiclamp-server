package com.thf.magiclamp.bl.impl;

import com.thf.magiclamp.bl.IEffectConfig;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BubblesEffectConfig implements IEffectConfig {
    public enum Direction {
        UP, DOWN
    }

    private Direction direction;
}

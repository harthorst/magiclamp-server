package com.thf.magiclamp.bl.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class ControlInfo {
    private String type;
    private String name;
}

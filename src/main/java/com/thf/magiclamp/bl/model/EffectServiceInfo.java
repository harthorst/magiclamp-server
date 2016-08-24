package com.thf.magiclamp.bl.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EffectServiceInfo {
    private String name;
    private List<ControlInfo> controls;
}

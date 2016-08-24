package com.thf.magiclamp.bl.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ControlChoiceInfo extends ControlInfo {
    private List<String> values;

    public ControlChoiceInfo(String name, List<String> values) {
        super("choice", name);
        this.values = values;
    }

}

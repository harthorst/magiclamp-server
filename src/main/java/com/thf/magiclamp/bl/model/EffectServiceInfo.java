package com.thf.magiclamp.bl.model;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EffectServiceInfo extends ResourceSupport {
    private String name;
    private List<ControlInfo> controls;
}

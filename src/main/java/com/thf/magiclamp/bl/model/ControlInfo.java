package com.thf.magiclamp.bl.model;

import org.springframework.hateoas.ResourceSupport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class ControlInfo extends ResourceSupport {
    private String type;
    private String name;
}

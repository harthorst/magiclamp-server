package com.thf.magiclamp.bl.model;

import org.springframework.hateoas.ResourceSupport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EffectService extends ResourceSupport {
    private String name;
}

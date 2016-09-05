package com.thf.magiclamp.bl.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.thf.magiclamp.bl.IEffectConfig;
import com.thf.magiclamp.bl.model.ControlChoiceInfo;
import com.thf.magiclamp.bl.model.ControlInfo;
import com.thf.magiclamp.bl.model.ControlNumberInfo;
import com.thf.magiclamp.bl.model.EffectServiceInfo;

@Component
public class EffectServiceInfoBuilder {

    public EffectServiceInfo getEffectServiceInfo(String name, IEffectConfig config) {
        List<ControlInfo> controls = new ArrayList<>();

        ReflectionUtils.doWithFields(config.getClass(), (Field f) -> {
            controls.add(getControlInfo(f));
        });

        EffectServiceInfo result = new EffectServiceInfo(name, controls);

        return result;
    }

    private ControlInfo getControlInfo(Field f) {

        String name = f.getName();
        if (f.getType().isEnum()) {
            List<String> values = new ArrayList<>();
            Arrays.asList(f.getType().getEnumConstants()).forEach((Object o) -> values.add(o.toString()));

            return new ControlChoiceInfo(name, values);
        } else if (f.getType().isPrimitive()) {
            Annotation aMin = f.getAnnotation(Min.class);
            Annotation aMax = f.getAnnotation(Max.class);

            Long min = (Long) AnnotationUtils.getValue(aMin);
            Long max = (Long) AnnotationUtils.getValue(aMax);

            return new ControlNumberInfo(name, min.intValue(), max.intValue());
        } else {
            throw new IllegalArgumentException("can not create ControlInfo for field " + name);
        }
    }

}

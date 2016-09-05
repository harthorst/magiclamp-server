package com.thf.magiclamp.bl.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thf.magiclamp.bl.IEffectService;

@Component
public class RainbowEffectService extends AbstractEffectService<RainbowEffectConfig> implements IEffectService {

    @Autowired
    public RainbowEffectService(EffectServiceInfoBuilder effectServiceInfoBuilder) {
        super(new RainbowEffectConfig());

        info = effectServiceInfoBuilder.getEffectServiceInfo("rainbow", this.getConfig());
    }

}

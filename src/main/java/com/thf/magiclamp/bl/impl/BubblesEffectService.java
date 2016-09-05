package com.thf.magiclamp.bl.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thf.magiclamp.bl.IEffectService;

@Component
public class BubblesEffectService extends AbstractEffectService<BubblesEffectConfig> implements IEffectService {

    @Autowired
    public BubblesEffectService(EffectServiceInfoBuilder effectServiceInfoBuilder) {
        super(new BubblesEffectConfig());
        info = effectServiceInfoBuilder.getEffectServiceInfo("bubbles", this.getConfig());
    }

}

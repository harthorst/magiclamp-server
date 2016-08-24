package com.thf.magiclamp.bl.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.thf.magiclamp.bl.IEffectService;
import com.thf.magiclamp.bl.model.ControlChoiceInfo;
import com.thf.magiclamp.bl.model.ControlInfo;
import com.thf.magiclamp.bl.model.ControlNumberInfo;
import com.thf.magiclamp.bl.model.EffectServiceInfo;

@Component
public class RainbowEffectService extends AbstractEffectService<RainbowEffectConfig> implements IEffectService {
    private EffectServiceInfo info;

    public RainbowEffectService() {
        super(new RainbowEffectConfig());

        List<ControlInfo> controls = new ArrayList<ControlInfo>();
        controls.add(new ControlNumberInfo("active", 0, 1));

        controls.add(new ControlChoiceInfo("color", Arrays.asList(new String[] {"green", "blue", "red"})));

        info = new EffectServiceInfo("bubble", controls);

    }

    @Override
    public EffectServiceInfo getInfo() {
        return info;
    }

}

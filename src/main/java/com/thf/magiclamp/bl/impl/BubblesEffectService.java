package com.thf.magiclamp.bl.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.thf.magiclamp.bl.IEffectConfig;
import com.thf.magiclamp.bl.IEffectService;
import com.thf.magiclamp.bl.impl.BubblesEffectConfig.Direction;
import com.thf.magiclamp.bl.model.ControlChoiceInfo;
import com.thf.magiclamp.bl.model.ControlInfo;
import com.thf.magiclamp.bl.model.ControlNumberInfo;
import com.thf.magiclamp.bl.model.EffectServiceInfo;

@Component
public class BubblesEffectService extends AbstractEffectService<BubblesEffectConfig> implements IEffectService {
    private EffectServiceInfo info;

    public BubblesEffectService() {
        super(new BubblesEffectConfig());
        List<ControlInfo> controls = new ArrayList<ControlInfo>();
        controls.add(new ControlNumberInfo("brightness", 0, 20));
        List<String> dirValues = new ArrayList<>();
        Arrays.asList(Direction.values()).forEach((x) -> dirValues.add(x.toString()));
        controls.add(new ControlChoiceInfo("direction", dirValues));

        info = new EffectServiceInfo("bubbles", controls);
    }

    @Override
    public EffectServiceInfo getInfo() {
        return info;
    }

    @Override
    public IEffectConfig getConfig() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setConfig(IEffectConfig config) {
        // TODO Auto-generated method stub

    }

}

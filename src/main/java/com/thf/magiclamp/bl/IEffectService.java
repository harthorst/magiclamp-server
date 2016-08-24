package com.thf.magiclamp.bl;

import com.thf.magiclamp.bl.model.EffectServiceInfo;

public interface IEffectService {
    public EffectServiceInfo getInfo();

    public IEffectConfig getConfig();

    public void setConfig(IEffectConfig config);

    public byte[] getIcon() throws ResourceNotFoundException;
}

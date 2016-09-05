package com.thf.magiclamp.bl.impl;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

import com.thf.magiclamp.bl.IEffectConfig;
import com.thf.magiclamp.bl.IEffectService;
import com.thf.magiclamp.bl.ResourceNotFoundException;
import com.thf.magiclamp.bl.model.EffectServiceInfo;

public abstract class AbstractEffectService<T extends IEffectConfig> implements IEffectService {
    T config;
    protected EffectServiceInfo info;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public EffectServiceInfo getInfo() {
        return info;
    }

    @Override
    public byte[] getIcon() throws ResourceNotFoundException {

        try {
            InputStream in =
                    resourceLoader.getResource("classpath:icons/" + this.getInfo().getName() + ".jpg").getInputStream();
            return IOUtils.toByteArray(in);
        } catch (Exception e) {
            throw new ResourceNotFoundException("icon resource not found");
        }
    }

    public AbstractEffectService(T config) {
        this.config = config;
    }

    public IEffectConfig getConfig() {
        return config;
    }

    public void setConfig(IEffectConfig config) {
        this.config = (T) config;
    }

}

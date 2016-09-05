package com.thf.magiclamp.sl;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.thf.magiclamp.bl.IEffectConfig;
import com.thf.magiclamp.bl.IEffectService;
import com.thf.magiclamp.bl.ResourceNotFoundException;
import com.thf.magiclamp.bl.model.ControlInfo;
import com.thf.magiclamp.bl.model.EffectService;
import com.thf.magiclamp.bl.model.EffectServiceInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("effects")
public class EffectMgmtEndpoint {

    @Autowired
    private List<IEffectService> effectServices;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<List<EffectService>> getEffects() {
        ArrayList<EffectService> result = new ArrayList<>();

        effectServices.forEach(e -> {
            EffectService effect = new EffectService(e.getInfo().getName());
            effect.add(linkTo(methodOn(EffectMgmtEndpoint.class).getServiceInfo(effect.getName())).withRel("info"));
            effect.add(linkTo(methodOn(EffectMgmtEndpoint.class).getServiceIcon(effect.getName())).withRel("icon"));
            result.add(effect);
        });

        return new ResponseEntity<List<EffectService>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}/info", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<EffectServiceInfo> getServiceInfo(@PathVariable("name") String name) {
        EffectServiceInfo serviceInfo = getService(name).getInfo();

        for (ControlInfo controlInfo : serviceInfo.getControls()) {
            controlInfo.add(linkTo(methodOn(EffectMgmtEndpoint.class).getServiceControl(name, controlInfo.getName()))
                    .withRel("link"));
        }

        return new ResponseEntity<EffectServiceInfo>(serviceInfo, HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}/icon"/* , produces = MediaType.IMAGE_JPEG_VALUE */, method = RequestMethod.GET)
    public HttpEntity<byte[]> getServiceIcon(@PathVariable("name") String name) {
        IEffectService service = getService(name);

        return new ResponseEntity<byte[]>(service.getIcon(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}/controls/{property}", method = RequestMethod.GET)
    public Object getServiceControl(@PathVariable("name") String name, @PathVariable("property") String property) {
        IEffectService service = getService(name);
        IEffectConfig config = service.getConfig();
        try {
            return BeanUtils.getPropertyDescriptor(config.getClass(), property).getReadMethod().invoke(config);
        } catch (BeansException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return service.getIcon();
    }

    @RequestMapping(value = "/{name}/controls/{property}", method = RequestMethod.PUT)
    public void setServiceControl(@PathVariable("name") String name, @PathVariable("property") String property,
            @RequestBody String value) {
        IEffectService service = getService(name);
        IEffectConfig config = service.getConfig();
        try {
            Method method = BeanUtils.getPropertyDescriptor(config.getClass(), property).getWriteMethod();
            Class[] parameters = method.getParameterTypes();
            if (parameters.length != 1) {
                throw new IllegalArgumentException("parameters size not 1");
            }

            // TODO
            if (parameters[0].equals(Integer.class) || parameters[0].equals(int.class)) {
                method.invoke(config, Integer.parseInt(value));
            } else if (parameters[0].isEnum()) {
                Method valueOf = parameters[0].getDeclaredMethod("valueOf", String.class);

                method.invoke(config, valueOf.invoke(null, value));
            } else {
                throw new IllegalArgumentException("parameter type not known");
            }

        } catch (BeansException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private IEffectService getService(String name) {
        HashMap<String, IEffectService> map = new HashMap<>();
        effectServices.forEach(e -> map.put(e.getInfo().getName(), e));

        if (map.containsKey(name)) {
            return map.get(name);
        } else {
            throw new ResourceNotFoundException("service not found");
        }
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "not found")
    @ExceptionHandler(ResourceNotFoundException.class)
    public void handleResourceNotFoundException(ResourceNotFoundException e) {
        log.error("returning 404", e);
    }
}

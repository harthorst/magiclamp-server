package com.thf.magiclamp.sl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.thf.magiclamp.bl.IEffectConfig;
import com.thf.magiclamp.bl.IEffectService;
import com.thf.magiclamp.bl.ResourceNotFoundException;
import com.thf.magiclamp.bl.model.EffectServiceInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("effects")
public class EffectMgmtEndpoint {

    @Autowired
    private List<IEffectService> effectServices;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<String> getEffects() {
        ArrayList<String> result = new ArrayList<>();

        effectServices.forEach(e -> result.add(e.getInfo().getName()));

        return result;
    }

    @RequestMapping(value = "/{name}/info", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public EffectServiceInfo getServiceInfo(@PathVariable("name") String name) {
        IEffectService service = getService(name);

        return service.getInfo();
    }

    @RequestMapping(value = "/{name}/icon"/* , produces = MediaType.IMAGE_JPEG_VALUE */, method = RequestMethod.GET)
    public byte[] getServiceIcon(@PathVariable("name") String name) {
        IEffectService service = getService(name);

        return service.getIcon();
    }

    @RequestMapping(value = "/{name}/controls/{name}", method = RequestMethod.GET)
    public byte[] getServiceControl(@PathVariable("name") String name) {
        IEffectService service = getService(name);
        IEffectConfig config = service.getConfig();
        BeanUtils.

        return service.getIcon();
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

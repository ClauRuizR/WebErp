package com.weberp.app.common.view;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.weberp.app.services.Generic.ServiceGeneric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by claudioruiz on 7/21/17.
 */
public abstract class RestControllerGeneric <T, ID extends Serializable>
{
    private Logger logger = LoggerFactory.getLogger(BaseRestController.class);

    private ServiceGeneric<T, ID> serviceGeneric;


    public RestControllerGeneric(ServiceGeneric<T, ID> serviceGeneric) {
        this.serviceGeneric = serviceGeneric;
    }

    @RequestMapping
    public @ResponseBody
    List<T> listAll() {
        List<T> all = this.serviceGeneric.findAll();
        return Lists.newArrayList(all);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    T create(@RequestBody T json) throws ParseException {
        logger.debug("create() with body {} of type {}", json, json.getClass());

        T created = this.serviceGeneric.Add(json);

        return created;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    T get(@PathVariable ID id) {
        return this.serviceGeneric.findOne(id);
    }



    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    Map<String, Object> delete(@PathVariable ID id) {
        this.serviceGeneric.delete(id);
        Map<String, Object> m = Maps.newHashMap();
        m.put("success", true);
        return m;
    }
}

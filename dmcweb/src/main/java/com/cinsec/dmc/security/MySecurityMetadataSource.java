package com.cinsec.dmc.security;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import com.cinsec.dmc.dao.IResourceDao;
import com.cinsec.dmc.entity.Resource;
import com.cinsec.dmc.entity.RoleResource;
import com.cinsec.dmc.exception.SysException;

@Service("mySecurityMetadataSource")
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private Logger logger = Logger.getLogger(MySecurityMetadataSource.class);

    private Map<String, Collection<ConfigAttribute>> requestMap;
    @Autowired
    private IResourceDao resourceDao;

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();
        requestMap = getRequestMap();

        for (Map.Entry<String, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            allAttributes.addAll(entry.getValue());
        }

        return allAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest request = ((FilterInvocation) object).getRequest();
        requestMap = getRequestMap();

        Iterator<String> it = requestMap.keySet().iterator();

        String url = null;
        AntPathRequestMatcher pathMatcher = null;

        while (it.hasNext()) {
            url = it.next();
            pathMatcher = new AntPathRequestMatcher(url);
            if (pathMatcher.matches(request)) {
                return requestMap.get(url);
            }
        }

        return null;
    }

    @PostConstruct
    public Map<String, Collection<ConfigAttribute>> getRequestMap() {
        try {
            requestMap = new LinkedHashMap<String, Collection<ConfigAttribute>>();
            List<Resource> resources = resourceDao.findAll();

            Collection<ConfigAttribute> configAttributes = null;
            for (Resource resource : resources) {
                configAttributes = new LinkedList<ConfigAttribute>();
                for (RoleResource roleResource : resource.getRoleResources()) {
                    configAttributes.add(new SecurityConfig(roleResource.getRole().getCode()));
                }
                if (CollectionUtils.isEmpty(configAttributes)) {
                    configAttributes.add(new SecurityConfig("ROLE_NO_USER"));
                }
                requestMap.put(resource.getUrl(), configAttributes);
            }

            return requestMap;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SysException(e);
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}

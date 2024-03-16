package com.mikayelovich.aspect;

import com.mikayelovich.cache.SoftReferenceCache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
public class CacheAspect {

    private @Autowired SoftReferenceCache cache;

    @Around("execution(* com.mikayelovich.service.TimeService.*(..))")
    public Object cacheTimeServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String key = createCacheKey(joinPoint);
        Optional optionalValue = cache.get(key);
        if (optionalValue.isPresent()) {
            return optionalValue.get();
        }

        Object result = joinPoint.proceed();
        cache.put(key, result);
        return result;
    }

    private String createCacheKey(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String args = String.join(",", Arrays.asList(joinPoint.getArgs()).toString());
        return methodName + "(" + args + ")";
    }
}

package com.kkaopay.money.scatter.service;

import com.kkaopay.money.scatter.error.ErrorCode;
import com.kkaopay.money.scatter.error.exception.NotExistValueException;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private static final int EXPIRE_MINUTES = 10;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> valueOperations;

    public void set(final String key, final Object value) {
        valueOperations.set(key, value, EXPIRE_MINUTES, TimeUnit.MINUTES);
    }

    public void validateExpiredKey(final String key) {
        if (ObjectUtils.isEmpty(valueOperations.get(key))) {
            throw new NotExistValueException(ErrorCode.EXPIRED_SCATTER_MONEY);
        }
    }
}

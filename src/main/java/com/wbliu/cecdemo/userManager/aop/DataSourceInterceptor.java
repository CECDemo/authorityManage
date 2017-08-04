package com.wbliu.cecdemo.userManager.aop;

import com.wbliu.cecdemo.userManager.common.myBatis.DataSourceTypeManager;
import com.wbliu.cecdemo.userManager.common.myBatis.DataSources;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author wbliu
 * @create 2017-03-26 23:51
 **/

/*@Aspect    // for aop
@Component // for auto scan
@Order(0)  // execute before @Transactional*/
public class DataSourceInterceptor {
   /* @Pointcut(value = "execution(public * com.wbliu.cecdemo.userManager.service.PlatformService..*.getUser(..))")
    public void dataSourceSlave(){};

    @Before("dataSourceSlave()")
    public void before(JoinPoint jp) {
        DataSourceTypeManager.set(DataSources.SLAVE);
    }
    // ... ...
*/
}

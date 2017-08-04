package com.wbliu.cecdemo.userManager.common.myBatis;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author wbliu
 * @create 2017-03-26 23:38
 **/


public class ThreadLocalRountingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceTypeManager.get();
    }

}

package com.sky.hrpro.service;

import com.sky.hrpro.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: YanWenjie
 * @Date: 2018/9/27 上午10:31
 */
@Service
public class TestService {
    @Autowired
    private TestDao testDao;

    public void testService(){
        //此处写逻辑，注意不要在接口层去写过多逻辑，尽量放到service层
        testDao.addTest();

    }

}


package cn.unicom.met.service;

import cn.unicom.met.entity.HandleRecord;
import cn.unicom.met.mapper.HandleRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HandleRecordService {
    @Autowired
    public HandleRecordMapper handleRecordMapper;

    //插入处理记录
    public void addhandlerecord(HandleRecord handleRecord){
        handleRecordMapper.addhandlerecord(handleRecord);
    }
    //查询某个会议下的所有处理记录
    public List<HandleRecord> selectByMetuuid(String metuuid){
        return handleRecordMapper.selectByMetuuid(metuuid);
    }

}

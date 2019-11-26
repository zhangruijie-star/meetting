package cn.unicom.met.mapper;

import cn.unicom.met.entity.HandleRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//public interface DepMapper extends BaseMapper<Dep> {
//
//}
public interface HandleRecordMapper {
    //插入处理记录
    void addhandlerecord(@Param("handleRecord") HandleRecord handleRecord);
    //查询某个会议下的所有处理记录
    List<HandleRecord> selectByMetuuid(@Param("metuuid") String metuuid);
}

package com.xp.glasses.mapper.wechat;

import com.xp.glasses.entity.ReceiveAddr;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mrxiong
 */
@Mapper
@Repository
public interface WeChatAddressMapper {


    void resetCurrentAddr(String userId);

    void addReceiveAddr(ReceiveAddr receiveAddr);

    void updateReceiveAddr(ReceiveAddr receiveAddr);

    List<ReceiveAddr> addrs(String userId);

    void removeAddr(String id);
}

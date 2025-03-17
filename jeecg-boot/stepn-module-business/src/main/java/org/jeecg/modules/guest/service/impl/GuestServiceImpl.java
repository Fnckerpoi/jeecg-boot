package org.jeecg.modules.guest.service.impl;

import org.jeecg.modules.guest.entity.Guest;
import org.jeecg.modules.guest.mapper.GuestMapper;
import org.jeecg.modules.guest.service.IGuestService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 租客资料
 * @Author: jeecg-boot
 * @Date:   2025-03-17
 * @Version: V1.0
 */
@Service
public class GuestServiceImpl extends ServiceImpl<GuestMapper, Guest> implements IGuestService {

}

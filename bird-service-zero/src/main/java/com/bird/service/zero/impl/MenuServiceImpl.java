package com.bird.service.zero.impl;

import com.bird.core.Check;
import com.bird.core.service.AbstractServiceImpl;
import com.bird.core.service.TreeDTO;
import com.bird.core.utils.DozerHelper;
import com.bird.service.zero.MenuService;
import com.bird.service.zero.dto.MenuDTO;
import com.bird.service.zero.mapper.MenuMapper;
import com.bird.service.zero.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liuxx on 2017/10/30.
 */
@Service
@CacheConfig(cacheNames = "zero_menu")
@com.alibaba.dubbo.config.annotation.Service
public class MenuServiceImpl extends AbstractServiceImpl<Menu> implements MenuService {
    @Autowired
    private DozerHelper dozerHelper;

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 获取所有菜单 树形数据
     *
     * @return
     */
    @Override
    public List<TreeDTO> getMenuTreeData() {
        return menuMapper.getMenuTreeData();
    }

    /**
     * 根据id获取菜单信息
     *
     * @param menuId 菜单id
     * @return
     */
    @Override
    public MenuDTO getMenu(Long menuId) {
        Check.GreaterThan(menuId,0L,"menuId");
        Menu menu = queryById(menuId);
        return dozerHelper.map(menu,MenuDTO.class);
    }


}

package tech.tengshe789.miaosha.sys.biz.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.tengshe789.miaosha.sys.api.entity.SysRole;
import tech.tengshe789.miaosha.sys.biz.mapper.SysRoleMapper;
import tech.tengshe789.miaosha.sys.biz.service.SysRoleService;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

	/**
	 * 通过用户ID，查询角色信息
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public List findRolesByUserId(Long userId) {
		return baseMapper.listRolesByUserId(userId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean removeRoleById(Integer id) {
		return this.removeById(id);
	}
}

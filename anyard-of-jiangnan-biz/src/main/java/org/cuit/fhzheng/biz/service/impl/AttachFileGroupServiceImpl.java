package org.cuit.fhzheng.biz.service.impl;

import org.cuit.fhzheng.biz.mapper.AttachFileMapper;
import org.cuit.fhzheng.biz.model.AttachFileGroup;
import org.cuit.fhzheng.biz.mapper.AttachFileGroupMapper;
import org.cuit.fhzheng.biz.service.AttachFileGroupService;
import org.cuit.fhzheng.biz.vo.AttachFileGroupVO;
import org.cuit.fhzheng.common.security.AuthUserContext;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 *
 * @author YXF
 * @date 2020-12-04 16:15:02
 */
@Service
public class AttachFileGroupServiceImpl implements AttachFileGroupService {

    @Autowired
    private AttachFileMapper attachFileMapper;
    @Autowired
    private AttachFileGroupMapper attachFileGroupMapper;

    @Override
    public List<AttachFileGroupVO> list() {
        return attachFileGroupMapper.list(AuthUserContext.get().getTenantId());
    }

    @Override
    public AttachFileGroupVO getByAttachFileGroupId(Long attachFileGroupId) {
        return attachFileGroupMapper.getByAttachFileGroupId(attachFileGroupId);
    }

    @Override
    public void save(AttachFileGroup attachFileGroup) {
        attachFileGroup.setShopId(AuthUserContext.get().getTenantId());
        attachFileGroupMapper.save(attachFileGroup);
    }

    @Override
    public void update(AttachFileGroup attachFileGroup) {
        attachFileGroupMapper.update(attachFileGroup);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long attachFileGroupId) {
        attachFileGroupMapper.deleteById(attachFileGroupId);
        attachFileMapper.updateBatchByAttachFileGroupId(attachFileGroupId);
    }
}

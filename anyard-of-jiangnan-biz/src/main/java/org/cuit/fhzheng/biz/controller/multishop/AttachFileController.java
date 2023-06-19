package org.cuit.fhzheng.biz.controller.multishop;


import org.cuit.fhzheng.biz.dto.AttachFileDTO;
import org.cuit.fhzheng.biz.model.AttachFile;
import org.cuit.fhzheng.biz.service.AttachFileService;
import org.cuit.fhzheng.biz.vo.AttachFileVO;
import org.cuit.fhzheng.common.database.dto.PageDTO;
import org.cuit.fhzheng.common.database.vo.PageVO;
import org.cuit.fhzheng.common.exception.anyardOfJiangnanException;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * 上传文件记录表
 *
 * @author YXF
 * @date 2020-11-21 10:21:40
 */
@RestController("multishopAttachFileController")
@RequestMapping("/m/attach_file")
@Tag(name = "上传文件记录表")
public class AttachFileController {

    @Autowired
    private AttachFileService attachFileService;

    @Autowired
    private MapperFacade mapperFacade;

    @GetMapping("/page")
    @Operation(summary = "获取上传文件记录表列表" , description = "分页获取上传文件记录表列表")
    public ServerResponseEntity<PageVO<AttachFileVO>> page(@Valid PageDTO pageDTO, String fileName, Long fileGroupId) {
        if (fileGroupId == 0) {
            fileGroupId = null;
        }
        PageVO<AttachFileVO> attachFilePage = attachFileService.page(pageDTO, fileName, fileGroupId);
        return ServerResponseEntity.success(attachFilePage);
    }

    @PostMapping
    @Operation(summary = "保存上传文件记录" , description = "保存上传文件记录")
    public ServerResponseEntity<Void> save(@RequestBody List<AttachFileDTO> attachFileDtos) {
        List<AttachFile> attachFiles = mapperFacade.mapAsList(attachFileDtos, AttachFile.class);
        attachFileService.save(attachFiles);
        return ServerResponseEntity.success();
    }

    /**
     * 更改文件名或分组
     */
    @PutMapping("/update_file")
    @Operation(summary = "更新文件记录" , description = "更新文件记录")
    public ServerResponseEntity<Boolean> updateFileName(@RequestBody AttachFileDTO attachFileDto) {
        if (Objects.isNull(attachFileDto.getFileName())) {
            // 图片名称不能为空
            throw new anyardOfJiangnanException("图片名称不能为空");
        }
        AttachFile attachFile = mapperFacade.map(attachFileDto, AttachFile.class);
        return ServerResponseEntity.success(attachFileService.updateFileName(attachFile));
    }

    @DeleteMapping
    @Operation(summary = "删除上传文件记录" , description = "根据上传文件记录表id删除上传文件记录")
    public ServerResponseEntity<Void> delete(@RequestParam Long fileId) {
        attachFileService.deleteById(fileId);
        return ServerResponseEntity.success();
    }
}

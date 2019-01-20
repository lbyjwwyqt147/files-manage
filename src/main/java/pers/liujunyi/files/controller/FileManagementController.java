package pers.liujunyi.files.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pers.liujunyi.common.annotation.ApiVersion;
import pers.liujunyi.common.controller.BaseController;
import pers.liujunyi.common.restful.ResultInfo;
import pers.liujunyi.common.restful.ResultUtil;
import pers.liujunyi.common.util.SystemUtils;
import pers.liujunyi.files.service.FileManagementService;


/***
 * 文件名称: FileManagementController.java
 * 文件描述: 文件管理 Controller
 * 公 司:
 * 内容摘要:
 * 其他说明:
 * 完成日期:2019年01月17日
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
@Api(tags = "文件管理相关API")
@RestController
public class FileManagementController extends BaseController {
    @Autowired
    private FileManagementService fileManagementService;

    /**
     * 单条删除数据
     * @param id
     * @return
     */
    @ApiOperation(value = "单条删除数据",notes = "适用于单条删除数据 请求示例：127.0.0.1:18080/api/v1/file/delete/11")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "version", value = "版本号", paramType = "path", required = true, dataType = "integer", defaultValue = "v1"),
            @ApiImplicitParam(name = "id", value = "文件id", paramType = "path", required = true, dataType = "integer")
    })
    @DeleteMapping(value = "file/delete/{id}")
    @ApiVersion(1)
    public ResultInfo singleDelete(@PathVariable(value = "id") Long id){
        if (this.fileManagementService.deleteById(id)) {
            return ResultUtil.success();
        }
        return ResultUtil.fail();
    }

    /**
     * 批量删除
     * @param id　 多个id 用 , 隔开
     * @return
     */
    @ApiOperation(value = "删除多条数据",notes = "适用于批量删除数据 请求示例：127.0.0.1:18080/api/v1/file/batchDelete/11,12,13")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "version", value = "版本号", paramType = "path", required = true, dataType = "integer", defaultValue = "v1"),
            @ApiImplicitParam(name = "id", value = "文件id,多个id 用,隔开", paramType = "path", required = true, dataType = "string")
    })
    @DeleteMapping(value = "file/batchDelete/{id}")
    @ApiVersion(1)
    public ResultInfo batchDelete(@PathVariable(value = "id") String id){
        if (this.fileManagementService.deleteAllByIdIn(SystemUtils.getIds(id))) {
            return ResultUtil.success();
        }
        return ResultUtil.fail();
    }

    /**
     * 根据　一组　id　获取信息
     * @param id  多个id 用 , 隔开
     * @return
     */
    @ApiOperation(value = "根据文件id获取信息",notes = "适用于根据文件id获取信息 请求示例：127.0.0.1:18080/api/v1/file/details/11,12")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "version", value = "版本号", paramType = "path", required = true, dataType = "integer", defaultValue = "v1"),
            @ApiImplicitParam(name = "id", value = "文件id,多个id 用,隔开", paramType = "path", required = true, dataType = "string")
    })
    @GetMapping(value = "file/details/{id}")
    @ApiVersion(1)
    public ResultInfo findAllById(@PathVariable(value = "id") String id){
        return ResultUtil.success(this.fileManagementService.findByIdIn(SystemUtils.getIds(id)));
    }


}

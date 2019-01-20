package pers.liujunyi.files.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/***
 * 文件名称: FileDataDto.java
 * 文件描述: 文件数据 dto
 * 公 司:
 * 内容摘要:
 * 其他说明:
 * 完成日期:2019年01月17日
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
@ApiModel
@Data
@EqualsAndHashCode(callSuper = false)
public class FileDataDto implements Serializable {
    private static final long serialVersionUID = -4979656133062494676L;

    /** 所属系统编码  例如：1001 相册管理系统 */
    @ApiModelProperty(value = "系统编码")
    @Length(min = 0, max = 10, message = "systemCode 最大长度为10")
    private String systemCode;

    /** 所属系统业务模块编码 例如：10：相册管理  20：视频管理  30： 博文管理 */
    @ApiModelProperty(value = "所属系统业务模块编码")
    @Length(min = 0, max = 10, message = "businessCode 最大长度为10")
    private String businessCode;

    /** 所属系统业务模块中的字段  */
    @ApiModelProperty(value = "所属系统业务模块中的字段")
    @Length(min = 0, max = 32, message = "businessField 最大长度为32")
    private String businessField;

    /** 优先级 */
    @ApiModelProperty(value = "优先级")
    @Max(value = 127, message = "priority 数值最大值为127")
    private Byte priority;

    /** 描述信息 */
    @ApiModelProperty(value = "描述信息")
    @Length(min = 0, max = 100, message = "description 最大长度为100")
    private String description;

    /** 备注 */
    @ApiModelProperty(value = "备注")
    @Length(min = 0, max = 100, message = "remarks 最大长度为100")
    private String remarks;

    /** 上传者ID */
    @ApiModelProperty(value = "上传者ID ")
    @Min(value = 1, message = "uploaderId 必须是数字类型")
    private Long uploaderId;

    /** 上传者名称 */
    @ApiModelProperty(value = "上传者名称 ")
    @Length(min = 0, max = 32, message = "uploaderName 最大长度为32")
    private String uploaderName;

    /** 扩展字段1 */
    @ApiModelProperty(value = "扩展字段1")
    @Length(min = 0, max = 45, message = "extensionOne 最大长度为45")
    private String extensionOne;

    /** 扩展字段2 */
    @ApiModelProperty(value = "扩展字段2")
    @Length(min = 0, max = 45, message = "extensionTwo 最大长度为45")
    private String extensionTwo;

    /** 扩展字段3 */
    @ApiModelProperty(value = "扩展字段3")
    @Length(min = 0, max = 45, message = "extensionThree 最大长度为45")
    private String extensionThree;

    /** 扩展字段4 */
    @ApiModelProperty(value = "扩展字段4")
    @Length(min = 0, max = 45, message = "extensionFour 最大长度为45")
    private String extensionFour;

    @ApiModelProperty(value = "是否需要重命名上传文件名称 默认：true")
    private Boolean rename = true;
}

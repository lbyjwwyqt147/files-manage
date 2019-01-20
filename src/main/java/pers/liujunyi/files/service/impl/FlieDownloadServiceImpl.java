package pers.liujunyi.files.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.liujunyi.common.restful.ResultInfo;
import pers.liujunyi.common.restful.ResultUtil;
import pers.liujunyi.files.entity.FileManagement;
import pers.liujunyi.files.repository.FileManagementRepository;
import pers.liujunyi.files.service.FlieDownloadService;
import pers.liujunyi.files.util.FileUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/***
 * 文件名称: FlieDownloadServiceImpl.java
 * 文件描述: 文件下载 service  实现
 * 公 司:
 * 内容摘要:
 * 其他说明:
 * 完成日期:2019年01月17日
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
@Service
public class FlieDownloadServiceImpl implements FlieDownloadService {
    @Autowired
    private FileManagementRepository fileManagementRepository;

    @Override
    public ResultInfo downloadFile(Long id, HttpServletResponse response) {
        Optional<FileManagement> records = this.fileManagementRepository.findById(id);
        if (records != null) {
            FileManagement record = records.get();
            FileUtil.downloadFile(record.getFilePath(), record.getFileInitialName(), response);
        }
        return ResultUtil.success();
    }

    @Override
    public ResultInfo downloadZipFile(List<Long> ids,  HttpServletResponse response) {
        return null;
    }
}

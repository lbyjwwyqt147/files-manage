package pers.liujunyi.files.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pers.liujunyi.common.util.ThreadPoolExecutorFactory;
import pers.liujunyi.common.vo.file.FileDataVo;
import pers.liujunyi.files.datasource.DataSourceType;
import pers.liujunyi.files.datasource.TargetDataSource;
import pers.liujunyi.files.entity.FileManagement;
import pers.liujunyi.files.repository.FileManagementRepository;
import pers.liujunyi.files.service.FileManagementService;
import pers.liujunyi.files.util.FileUtil;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadPoolExecutor;

/***
 * 文件名称: FileManagementService.java
 * 文件描述: 文件管理 Service
 * 公 司:
 * 内容摘要:
 * 其他说明:
 * 完成日期:2019年01月17日
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
@Slf4j
@Service
public class FileManagementServiceImpl implements FileManagementService {
    private ThreadPoolExecutor threadPoolExecutor = ThreadPoolExecutorFactory.getThreadPoolExecutor();

    @Autowired
    private FileManagementRepository fileManagementRepository;

    @Override
    public Long insert(FileManagement record) {
        Long id = null;
        record.setUploadTime(new Date());
        FileManagement saveObj = this.fileManagementRepository.save(record);
        if (saveObj != null) {
            id = saveObj.getId();
        }
        return id;
    }

    @Override
    public List<FileManagement> insertBatch(List<FileManagement> record) {
        return this.fileManagementRepository.saveAll(record);
    }

    @Override
    public FileManagement saveRecord(FileManagement record) {
        return this.fileManagementRepository.save(record);
    }

    @Override
    public List<FileDataVo> saveFileData(List<FileManagement> records) {
        List<FileManagement> fileRecordList = this.fileManagementRepository.saveAll(records);
        List<FileDataVo> fileDataList =  new CopyOnWriteArrayList<>();
        if (!CollectionUtils.isEmpty(fileRecordList)) {
            for (FileManagement fileRecord : fileRecordList) {
                FileDataVo fileData = new FileDataVo();
                fileData.setId(fileRecord.getId());
                fileData.setFileInitialName(fileRecord.getFileInitialName());
                fileData.setFileName(fileRecord.getFileName());
                fileData.setFileSize(fileRecord.getFileSize());
                fileData.setFileCallAddress(fileRecord.getFileCallAddress());
                fileDataList.add(fileData);
            }
        } else {
            // 如果入库失败就删除上传文件
            this.deleteFile(records);
        }
        return fileDataList;
    }

    @Override
    public Boolean deleteAllByIdIn(List<Long> ids) {
        List<FileManagement> recordList = this.findByIdIn(ids);
        // 删除数据库纪录
        this.fileManagementRepository.deleteInBatch(recordList);
        // 删除磁盘上的文件
        this.deleteFile(recordList);
        return true;
    }

    @Override
    public Boolean deleteById(Long id) {
        FileManagement record = this.fileManagementRepository.getOne(id);
        // 删除数据库纪录
        this.fileManagementRepository.delete(record);
        // 删除磁盘上的文件
        FileUtil.delete(record.getFilePath());
        return true;
    }

    @TargetDataSource(dataSource = DataSourceType.SLAVE)
    @Override
    public List<FileManagement> findByIdIn(List<Long> ids) {
        return this.fileManagementRepository.findByIdIn(ids);
    }

    /**
     * 删除 磁盘上的文件
     * @param records
     */
    private  void  deleteFile(List<FileManagement> records) {
        // 删除磁盘上的文件
        threadPoolExecutor.execute(() ->{
            for (FileManagement fileRecord : records) {
                FileUtil.delete(fileRecord.getFilePath());
            }
        });
    }
}

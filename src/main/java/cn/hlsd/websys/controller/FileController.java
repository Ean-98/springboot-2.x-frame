package cn.hlsd.websys.controller;

import cn.hlsd.websys.entity.Result;
import cn.hlsd.websys.util.FileUtil;
import cn.hlsd.websys.util.ResultUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Ean
 * @version 0.1.0
 * @Description
 * @create 2020-08-19 09:59
 * @since 0.1.0
 **/
@Slf4j
@Api(tags = "文件处理控制器，包括上传下载等")
@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    FileUtil fileUtil;

    @PostMapping("/singleUpload")
    public Result singleUpload(@RequestParam(name = "file") MultipartFile file, String path) {
        String fix = FileUtil.getFix(file.getOriginalFilename());
        String resUrl = null;
        try {
            resUrl = fileUtil.singleUpload(path, file.getBytes(), fix);
        } catch (IOException e) {
            log.error("不允许上传空文件");
            return ResultUtil.error(-1, "不允许上传空文件");
        }
        if (!StringUtils.isBlank(resUrl)) {
            log.info("上传文件成功-->" + resUrl);
            return ResultUtil.success(resUrl);
        }
        return ResultUtil.error(-1, "上传文件失败");
    }
}

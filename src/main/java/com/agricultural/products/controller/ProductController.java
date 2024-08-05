package com.agricultural.products.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.agricultural.products.annotation.Log;
import com.agricultural.products.common.Result;
import com.agricultural.products.config.interceptor.AuthAccess;
import com.agricultural.products.entity.Fertilization;
import com.agricultural.products.entity.Machining;
import com.agricultural.products.entity.dto.FertilizationDTO;
import com.agricultural.products.entity.dto.MachiningDTO;
import com.agricultural.products.service.IFertilizationService;
import com.agricultural.products.service.IMachiningService;
import com.agricultural.products.utils.PageResult;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.agricultural.products.service.IProductService;
import com.agricultural.products.entity.Product;
import com.agricultural.products.entity.dto.ProductDTO;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 *
 * 农产品信息相关接口类
 *
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private IProductService productService;

    @Resource
    private IFertilizationService fertilizationService;

    @Resource
    private IMachiningService machiningService;


    /**
     * 保存农产品信息
     * @param product
     * @return
     */
    @PostMapping
    @Log(method = "保存", resource = "农产品信息")
    public Result save(@RequestBody Product product) {
        return Result.success(productService.saveOrUpdate(product));
    }

    /**
    * 删除单条农产品信息
    * @param id
    * @return
    */
    @DeleteMapping("/{id}")
    @Log(method = "删除", resource = "农产品信息")
    public Result delete(@PathVariable Integer id) {
        productService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除农产品信息
     * @param ids
     * @return
     */
    @PostMapping("/del/batch")
    @Log(method = "批量删除", resource = "农产品信息")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        productService.removeByIds(ids);
        return Result.success();
    }

    /**
    * 查询列表农产品信息
     * @param product
    * @return
    */
    @GetMapping
    public Result findList(Product product) {
        return Result.success(productService.list(product));
    }

    /**
    * 根据id获取农产品信息
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(productService.getById(id));
    }

    /**
     * 农产品信息分页数据
     * @param productDTO
     * @return
     */
    @GetMapping("/page")
    @Log(method = "查询", resource = "农产品信息")
    public Result findPage(ProductDTO productDTO) {
        return Result.success(new PageResult<>(productService.page(productDTO)));
    }


    /**
     * 生成溯源码
     * @param id
     * @param response
     * @throws IOException
     */
    @GetMapping("/generateQrcode")
    @AuthAccess
    public void generateQrcode(@RequestParam Integer id, HttpServletResponse response) throws IOException {
        ProductDTO dto = productService.getById(id);
        // 设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("qrcode.png", "UTF-8"));
        response.setContentType("application/octet-stream");
//        String content = "农产品类型：{}\n浇水次数：{}\n施肥次数：{}\n保质期：{}\n播种时间：{}\n收割时间：{}";
//        content = CharSequenceUtil.format(content, dto.getName(), dto.getWaterNum(), dto.getFertilizationNum(), dto.getShelfLife(), DateUtil.formatDateTime(dto.getSowingTime()), DateUtil.formatDateTime(dto.getHarvestTime()));

        String content = "农产品类型：{}\n保质期：{}\n播种时间：{}\n收割时间：{}\n生产场地：{}\n";
        content = CharSequenceUtil.format(content, dto.getName(), dto.getShelfLife(), DateUtil.formatDateTime(dto.getSowingTime()), DateUtil.formatDateTime(dto.getHarvestTime()),dto.getArea());


        Fertilization fertilization = new Fertilization();
        fertilization.setSid(dto.getSid());
        List<FertilizationDTO> fertilizationDTOList = fertilizationService.list(fertilization);
        if (CollUtil.isNotEmpty(fertilizationDTOList)){
            content = content + "施肥信息：\n";
            for (int i = 0; i < fertilizationDTOList.size(); i++) {
                content = content + (i+1) + "." + DateUtil.formatDateTime(fertilizationDTOList.get(i).getFertilizationTime()) + " " + fertilizationDTOList.get(i).getFertilizationer() + "施肥" + fertilizationDTOList.get(i).getFertilizerType() + "\n";
            }
        }

        Machining machining = new Machining();
        machining.setPid(dto.getId());
        List<MachiningDTO> machiningList = machiningService.list(machining);
        if (CollUtil.isNotEmpty(machiningList)){
            content = content + "加工信息：\n";
            for (int i = 0; i < machiningList.size(); i++) {
                content = content + (i+1) + "." + DateUtil.formatDateTime(machiningList.get(i).getMachiningTime()) + " " + machiningList.get(i).getMachiningStaff() + "对农产品进行" + machiningList.get(0).getMethod() + "加工\n";
            }
        }
        System.out.println(content);
        byte[] bytes = QrCodeUtil.generatePng(content, 300, 300);
        os.write(bytes);
        os.flush();
        os.close();
    }
}


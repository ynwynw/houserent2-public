package com.hcr.turtle.controller;

import com.hcr.turtle.entiey.Customer;
import com.hcr.turtle.entiey.Employee;
import com.hcr.turtle.util.CusUtil;
import com.hcr.turtle.util.EmpUtil;
import com.hcr.turtle.util.UploadUtil;
import com.hcr.turtle.service.CustomerService;
import com.hcr.turtle.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@SuppressWarnings("all")
public class EmpImgController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CustomerService customerService;

    /**
     * 员工头像上传
     * @param picture
     * @param request
     * @return
     */
    @RequestMapping("/uploadEmp")
    public UploadUtil upload(@RequestParam("picture") MultipartFile picture, HttpServletRequest request) {
        System.out.println("-----------------------------------");
        //获取文件在服务器的储存位置
        /*String path = request.getSession().getServletContext().getRealPath("/upload");*/
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("src/main/resources/static/resources/images/head").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("文件的保存路径：" + path);
        File filePath = new File(String.valueOf(path));
        if (!filePath.exists() && !filePath.isDirectory()) {
            System.out.println("目录不存在，创建目录:" + filePath);
            filePath.mkdir();
        }

        //设置文件新名称: 当前时间+文件名称（不包含格式）
        String fileName = EmpUtil.getEmpFromSession().getEusername()+".jpg";
        System.out.println("新文件名称：" + fileName);

        //在指定路径下创建一个文件
        File targetFile = new File(path, fileName);

        //将文件保存到服务器指定位置
        try {
            picture.transferTo(targetFile);
            //将文件在服务器的存储路径返回
            UploadUtil uploadUtil=new UploadUtil(true,"../../resources/images/head/"+fileName);
            Employee employee=new Employee();
            employee.setLogoimg("../../resources/images/head/"+fileName);
            employeeService.upEmpimgByid(employee);
            System.out.println("上传成功");
            return uploadUtil;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("上传失败");
            return new UploadUtil(false, "上传失败");
        }
    }

    /**
     * 用户头像上传
     */
    @RequestMapping("/uploadCus")
    public UploadUtil uploadCus(@RequestParam("picture") MultipartFile picture, HttpServletRequest request) {
        System.out.println("-----------------------------------");
        //获取文件在服务器的储存位置
        /*String path = request.getSession().getServletContext().getRealPath("/upload");*/
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("src/main/resources/static/resources/images/head").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("文件的保存路径：" + path);
        File filePath = new File(String.valueOf(path));
        if (!filePath.exists() && !filePath.isDirectory()) {
            System.out.println("目录不存在，创建目录:" + filePath);
            filePath.mkdir();
        }

        //设置文件新名称: 当前时间+文件名称（不包含格式）
        String fileName = CusUtil.getCusFromSession().getCnumber()+".jpg";
        System.out.println("新文件名称：" + fileName);

        //在指定路径下创建一个文件
        File targetFile = new File(path, fileName);

        //将文件保存到服务器指定位置
        try {
            picture.transferTo(targetFile);
            //将文件在服务器的存储路径返回
            UploadUtil uploadUtil=new UploadUtil(true,"../../resources/images/head/"+fileName);
            Customer customer=new Customer();
            customer.setCimg("../../resources/images/head/"+fileName);
            customerService.updateCusImg(customer);
            System.out.println("上传成功");
            return uploadUtil;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("上传失败");
            return new UploadUtil(false, "上传失败");
        }
    }



}

package com.slxy.www.controller;


import com.slxy.www.mapper.SelectUserBaseMapper;
import com.slxy.www.model.SelectUserBase;
import com.slxy.www.model.vo.SelectUserBaseVo;
import com.slxy.www.model.enums.EnumUserType;
import com.slxy.www.service.ISelectUserBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhengql
 * @since 2018-01-06
 */
@Controller
@RequestMapping("/selectUserBase")
@SessionAttributes(value = {"sessionUser","userType"})
public class SelectUserBaseController {

    @Autowired
    private ISelectUserBaseService selectUserBaseService;
    @Autowired
    private SelectUserBaseMapper selectUserBaseMapper;

    @RequestMapping("/login")
    public ModelAndView index(SelectUserBase userBase,ModelAndView  modelAndView) {
        SelectUserBase selectUserBase = selectUserBaseMapper.selectOne((userBase));
        if (ObjectUtils.isEmpty(selectUserBase)||!userBase.getUserPassword().equals(selectUserBase.getUserPassword())){
            modelAndView.setViewName("redirect:/login.jsp");
            modelAndView.addObject("msg","请重新登录");
            return modelAndView;
        }
        modelAndView.setViewName("main");
        modelAndView.addObject("sessionUser",selectUserBase);
        modelAndView.addObject("userType",selectUserBase.getUserType());
        return modelAndView;
    }

    /**
     * 管理员列表
     * @param modelAndView
     * @param userBaseVo
     * @return
     */
    @RequestMapping("/admList")
    public ModelAndView admList(ModelAndView  modelAndView,SelectUserBaseVo userBaseVo) {
        modelAndView.setViewName("adminModule/admList");
        userBaseVo.setUserType(EnumUserType.ADMIN.getValue());
        return selectUserBaseService.userList(modelAndView,userBaseVo);
    }

    /**
     * 初始化添加
     * @param modelAndView
     * @return
     */
    @RequestMapping("/initAddAdmin")
    public ModelAndView initAddAdmin(ModelAndView  modelAndView) {
        modelAndView.setViewName("adminModule/admAdd");
        return modelAndView;
    }


    /**
     * 管理员添加
     * @param userBase
     * @return
     */
    @RequestMapping("/admAdd")
    @ResponseBody
    public String admAdd(SelectUserBase userBase) {

        return selectUserBaseService.admAdd(userBase);
    }



    /***
     * admin删除
     * @param userBaseVo
     * @return
     */
    @RequestMapping("/admDelete")
    @ResponseBody
    public String admDelete(SelectUserBaseVo userBaseVo) {
        return selectUserBaseService.admDelete(userBaseVo);
    }

    /***
     * 管理员批量删除
     * @param selectedIDs
     * @return
     */
    @RequestMapping("/admDeleteAll")
    @ResponseBody
    public String admDeleteAll(Integer[] selectedIDs) {
        return selectUserBaseService.admDeleteAll(selectedIDs);
    }

    /**
     * adm启用禁用
     * @param userBaseVo
     * @return
     */
    @RequestMapping("/admAble")
    @ResponseBody
    public String admAble(SelectUserBaseVo userBaseVo) {
        return selectUserBaseService.admAble(userBaseVo);
    }


    /***
     * 管理员个人信息变更
     * @param modelAndView
     * @param userBaseVo
     * @return
     */
    @RequestMapping("/admSelfInfo")
    public ModelAndView admSelfInfo(ModelAndView  modelAndView,SelectUserBaseVo userBaseVo) {
        modelAndView.setViewName("/adminModule/admSelfInfo");
        return selectUserBaseService.stuInitAddAndUpdate(modelAndView,userBaseVo);
    }

    /******************************************    学生   *****************************************************/
    /**
     * 学生列表初始化
     * @param modelAndView
     * @param userBaseVo
     * @return
     */
    @RequestMapping("/stuList")
    public ModelAndView stuList(ModelAndView  modelAndView,SelectUserBaseVo userBaseVo) {
        userBaseVo.setUserType(EnumUserType.STUDENT.getValue());
        modelAndView.setViewName("/stuModule/stuList");
        return selectUserBaseService.userList(modelAndView,userBaseVo);
    }

    /**
     * 学生参数查询列表 异步生成列表
     * @param userBaseVo
     * @return
     */
    @RequestMapping("/stuListAjax")
    @ResponseBody
    public String stuListAjax(SelectUserBaseVo userBaseVo) {
        userBaseVo.setUserType(EnumUserType.STUDENT.getValue());
        return selectUserBaseService.stuListAjax(userBaseVo);
    }

    /**
     * 学生启用禁用
     * @param userBaseVo
     * @return
     */
    @RequestMapping("/stuAble")
    @ResponseBody
    public String stuAble(SelectUserBaseVo userBaseVo) {
        return selectUserBaseService.stuAble(userBaseVo);
    }

    /***
     * 学生删除
     * @param userBaseVo
     * @return
     */
    @RequestMapping("/stuDelete")
    @ResponseBody
    public String stuDelete(SelectUserBaseVo userBaseVo) {
        return selectUserBaseService.stuDelete(userBaseVo);
    }

    /***
     * 学生批量删除
     * @param selectedIDs
     * @return
     */
    @RequestMapping("/stuDeleteAll")
    @ResponseBody
    public String stuDeleteAll(Integer[] selectedIDs) {
        return selectUserBaseService.stuDeleteAll(selectedIDs);
    }



    /***
     * 学生个人信息变更
     * @param modelAndView
     * @param userBaseVo
     * @return
     */
    @RequestMapping("/stuSelfInfo")
    public ModelAndView stuSelfInfo(ModelAndView  modelAndView,SelectUserBaseVo userBaseVo) {
        modelAndView.setViewName("/stuModule/stuSelfInfo");
        return selectUserBaseService.stuInitAddAndUpdate(modelAndView,userBaseVo);
    }



    /***
     * 学生修改初始化
     * @param modelAndView
     * @param userBaseVo
     * @return
     */
    @RequestMapping("/stuInitUpdate")
    public ModelAndView stuInitUpdate(ModelAndView  modelAndView,SelectUserBaseVo userBaseVo) {
        modelAndView.setViewName("/stuModule/stuUpdate");
        return selectUserBaseService.stuInitAddAndUpdate(modelAndView,userBaseVo);
    }

    /**
     * 学生修改
     * @param userBase
     * @return
     */
    @RequestMapping("/stuUpdate")
    @ResponseBody
    public String stuUpdate(SelectUserBase userBase) {
        return selectUserBaseService.stuUpdate(userBase);
    }

    /**
     * 学生添加初始化
     * @param modelAndView
     * @param userBaseVo
     * @return
     */
    @RequestMapping("/stuInitAdd")
    public ModelAndView stuInitAdd(ModelAndView  modelAndView,SelectUserBaseVo userBaseVo) {

        modelAndView.setViewName("/stuModule/stuAdd");
        return selectUserBaseService.stuInitAddAndUpdate(modelAndView,userBaseVo);
    }

    /**
     * 学生添加
     * @param userBase
     * @return
     */
    @RequestMapping("/stuAdd")
    @ResponseBody
    public String stuAdd(SelectUserBase userBase) {
        return selectUserBaseService.stuAdd(userBase);
    }


    /***
     * 学生导入
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/stuUpload", method = RequestMethod.POST)
    @ResponseBody
    public String stuUpload(HttpServletRequest request) throws IOException {
        return selectUserBaseService.stuUpload(request);
    }

    /**
     * 根据专业查询班级数
     * @param userBase
     * @return
     */
    @RequestMapping(value = "/initClass", method = RequestMethod.POST)
    @ResponseBody
    public String initClass(SelectUserBase userBase) {
        return selectUserBaseService.initClass(userBase);
    }


    /**
     * 学生模板下载
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/stuFileDown")
    public void stuFileDown(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String fileName= "select_students.xls";
        selectUserBaseService.down(request,response,fileName);
    }

    /*********************************************************************************************************/
    /******************************************   教师   *****************************************************/
    /*********************************************************************************************************/

    /**
     * 教师模板下载
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/teaFileDown")
    public void teaFileDown(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String fileName= "select_teachers.xls";
        selectUserBaseService.down(request,response,fileName);
    }
    /**
     * 教师列表初始化
     * @param modelAndView
     * @param userBaseVo
     * @return
     */
    @RequestMapping("/teaList")
    public ModelAndView teaList(ModelAndView  modelAndView,SelectUserBaseVo userBaseVo) {
        userBaseVo.setUserType(EnumUserType.TEACHER.getValue());
        modelAndView.setViewName("/teaModule/teaList");
        return selectUserBaseService.userList(modelAndView,userBaseVo);
    }

    /**
     * 异步生成教师列表
     * @param userBaseVo
     * @return
     */
    @RequestMapping("/teaListAjax")
    @ResponseBody
    public String teaListAjax(SelectUserBaseVo userBaseVo) {
        userBaseVo.setUserType(EnumUserType.TEACHER.getValue());
        return selectUserBaseService.teaListAjax(userBaseVo);
    }

    /**
     * 教师启用禁用
     * @param userBase
     * @return
     */
    @RequestMapping("/teaAble")
    @ResponseBody
    public String teaAble(SelectUserBase userBase) {
        return selectUserBaseService.teaAble(userBase);
    }

    /**
     * 教师添加初始化
     * @param modelAndView
     * @param userBaseVo
     * @return
     */
    @RequestMapping("/teaInitAdd")
    public ModelAndView teaInitAdd(ModelAndView  modelAndView,SelectUserBaseVo userBaseVo) {
        modelAndView.setViewName("/teaModule/teaAdd");
        return selectUserBaseService.teaInitAddAndUpdate(modelAndView,userBaseVo);
    }

    /**
     * 教师添加
     * @param userBase
     * @return
     */
    @RequestMapping("/teaAdd")
    @ResponseBody
    public String teaAdd(SelectUserBase userBase) {
        return selectUserBaseService.teaAdd(userBase);
    }




    /***
     * 教师编辑初始化
     * @param modelAndView
     * @param userBaseVo
     * @return
     */
    @RequestMapping("/teaSelfInfo")
    public ModelAndView teaSelfInfo(ModelAndView  modelAndView,SelectUserBaseVo userBaseVo) {
        modelAndView.setViewName("/teaModule/teaSelfInfo");
        return selectUserBaseService.teaInitAddAndUpdate(modelAndView,userBaseVo);
    }


    /***
     * 教师编辑初始化
     * @param modelAndView
     * @param userBaseVo
     * @return
     */
    @RequestMapping("/teaInitUpdate")
    public ModelAndView teaInitUpdate(ModelAndView  modelAndView,SelectUserBaseVo userBaseVo) {
        modelAndView.setViewName("/teaModule/teaUpdate");
        return selectUserBaseService.teaInitAddAndUpdate(modelAndView,userBaseVo);
    }

    /***
     * 教师编辑
     * @param userBase
     * @return
     */
    @RequestMapping("/teaUpdate")
    @ResponseBody
    public String teaUpdate(SelectUserBase userBase) {
        return selectUserBaseService.teaUpdate(userBase);
    }

    /***
     * 教师删除
     * @param userBase
     * @return
     */
    @RequestMapping("/teaDelete")
    @ResponseBody
    public String teaDelete(SelectUserBase userBase) {
        return selectUserBaseService.teaDelete(userBase);
    }

    /**
     * 教师批量删除
     * @param selectedIDs
     * @return
     */
    @RequestMapping("/teaDeleteAll")
    @ResponseBody
    public String teaDeleteAll(Integer[] selectedIDs) {
        return selectUserBaseService.teaDeleteAll(selectedIDs);
    }

    /**
     * 教师详情
     * @param modelAndView
     * @param userBaseVo
     * @return
     */
    @RequestMapping("/teaDetails")
    public ModelAndView teaDetails(ModelAndView  modelAndView,SelectUserBaseVo userBaseVo) {
        modelAndView.setViewName("teaModule/teaDetails");
        return selectUserBaseService.teaInitAddAndUpdate(modelAndView,userBaseVo);
    }


    /***
     * 教师导入
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/teaUpload", method = RequestMethod.POST)
    @ResponseBody
    public String teaUpload(HttpServletRequest request) throws IOException {
        return selectUserBaseService.teaUpload(request);
    }



































































}


package com.jachs.containertomcat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jachs.containertomcat.vo.UserVo;

/**
 * @author zhanchaohan
 * 
 */
@Controller
public class LogController {
    
    @RequestMapping( "/hello" )
    public ModelAndView hello () {
        ModelAndView mv=new ModelAndView();
        mv.setViewName("hello");//设置视图名称
        
        
        
        String[]SArr=new String[] {"美国","俄罗斯","中国"};
        
        List<String>SList=new ArrayList<String>();
        SList.add ( "America" );
        SList.add ( "Russia" );
        SList.add ( "China" );
        
        UserVo user=new UserVo();
        user.setUserName ( "TestUser" );
        user.setUserAge ( 12 );
        user.setUserFriend ( SList );
        
        Map<String, Object>map=new HashMap<> ();
        map.put ( "SList", SList );
        map.put ( "user", user );
        
        mv.addObject("title", "我的页面标题");
        mv.addObject("SArr", SArr);
        mv.addObject("SList", SList);
        mv.addObject("user", user);
        mv.addObject("map", map);
        return mv;
    }
}

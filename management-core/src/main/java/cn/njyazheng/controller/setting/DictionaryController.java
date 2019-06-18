package cn.njyazheng.controller.setting;

import cn.njyazheng.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DictionaryController {
    @Autowired
    private PermissionService permissionService;
    @RequestMapping("/dictionary")
    public String toDictionaryPage(@AuthenticationPrincipal UserDetails userDetails, HttpServletRequest request, ModelMap modelMap){
        String username= userDetails.getUsername();
        String permissiontypes=permissionService.getPermissionTypes(username,request.getRequestURI());
        modelMap.put("permissiontypes",permissiontypes);
        return "setting/dictionary.html";
    }

}

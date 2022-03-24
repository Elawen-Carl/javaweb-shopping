package com.czklps.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.czklps.Pojo.Admin;
import com.czklps.Pojo.Category;
import com.czklps.Pojo.Product;
import com.czklps.Pojo.User;
import com.czklps.exception.*;
import com.czklps.service.*;
import com.czklps.service.Impl.*;
import com.czklps.utils.LayuiData;
import com.czklps.utils.WebUtils;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

public class AdminServlect extends BaseServlet{
    private UserService userService = new UserServiceImpl();
    private ProductService productService = new ProductServiceImpl();
    private AdminService adminService = new AdminServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();
    private OrderService orderService = new OrderServiceImpl();

    protected void cheoutLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("admin");

    }

    protected void queryAdmins(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uname = req.getParameter("uname");
        String phone = req.getParameter("phone");
        Integer pageNo = WebUtils.parseInt(req.getParameter("page"),0);
        Integer pageSize = WebUtils.parseInt(req.getParameter("limit"),10);
        List<Admin> admins = new ArrayList<Admin>();
        int count = adminService.queryAdmins(admins, uname, phone ,pageNo, pageSize);
        LayuiData layData = new LayuiData();
        for (Admin admin : admins) {
            JSONObject field = new JSONObject();
            field.put("id",admin.getId());
            field.put("name",admin.getName());
            field.put("password",admin.getPassword());
            field.put("phone",admin.getPhone());
            field.put("createtime",admin.getCreatetime());
            field.put("email",admin.getEmail());
            field.put("information",admin.getInformation());
            layData.setField(field);
        }
        layData.setCode(0);
        layData.setMsg("");
        layData.setCount(count);
        resp.getWriter().println(layData.getData());
    }

    protected void updateAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String information = req.getParameter("information");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        req.setAttribute("id",id);
        req.setAttribute("name",name);
        req.setAttribute("information",information);
        req.setAttribute("phone",phone);
        req.setAttribute("email",email);
        req.setAttribute("password",password);
        req.getRequestDispatcher("/admin/pages/admin/edit.jsp").forward(req, resp);
    }

    protected void updateAdminOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        try {
            if(!adminService.adminNameExist(name)){
                String password = req.getParameter("password");
                String information = req.getParameter("information");
                String phone = req.getParameter("phone");
                String email = req.getParameter("email");
                Integer id = Integer.parseInt(req.getParameter("id"));
                Admin a = new Admin();
                a.setInformation(information);
                a.setPassword(password);
                a.setEmail(email);
                a.setPhone(phone);
                a.setCreatetime(new Date());
                a.setName(name);
                a.setId(id);
                adminService.updata(a);
                resp.getWriter().println("successful");
            }
        } catch (AdminExistException e) {
            resp.getWriter().println("Unsuccessful");
        }
    }

    protected void deleteAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = WebUtils.parseInt(req.getParameter("id"), 0);
        Admin admin = new Admin();
        admin.setId(id);
        adminService.delete(admin);
    }

    protected void addAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        try {
            if(!adminService.adminNameExist(name)){
                String password = req.getParameter("password");
                String phone = req.getParameter("phone");
                String email = req.getParameter("email");
                String information = req.getParameter("information");
                Admin a = new Admin();
                a.setInformation(information);
                a.setPassword(password);
                a.setEmail(email);
                a.setPhone(phone);
                a.setCreatetime(new Date());
                a.setName(name);
                adminService.add(a);
                resp.getWriter().println("successful");
            }
        } catch (AdminExistException e) {
            resp.getWriter().println("Unsuccessful");
        }
    }

    protected void loginAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String pwd = req.getParameter("pwd");
        boolean isLogin = false;
        Admin admin = null;
        String msg = "";
        try {
            if((admin = adminService.login(name, pwd)) != null){
                isLogin = true;
            }
        } catch (AdminNotFoundException e) {
            req.setAttribute("msg","name not found ! please input correct!");
        } catch (PasswordCorrectException e) {
            req.setAttribute("msg","password incorrect !");
        }
        if(isLogin){
            HttpSession session = req.getSession();
            session.setAttribute("admin",admin);
            resp.sendRedirect(req.getContextPath() + "/admin/pages/index.jsp");
        }else{
            req.getRequestDispatcher("/admin/pages/login.jsp").forward(req, resp);
        }
    }

    protected void deleteCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Integer pid = Integer.parseInt(req.getParameter("pid"));
        boolean isLeaf = Boolean.parseBoolean(req.getParameter("isleaf"));
        LayuiData layuiData = new LayuiData();
        try {
            categoryService.deleteCategory(id,pid,isLeaf);
            layuiData.setCount(0);
            layuiData.setMsg("删除成功");
            layuiData.setCode(0);
            resp.getWriter().println(layuiData.getData());
        } catch (SQLException throwables) {
            layuiData.setCount(0);
            layuiData.setMsg("此类别下还有商品请将商品的分类修改后在进行删除！！");
            layuiData.setCode(1);
            resp.getWriter().println(layuiData.getData());
        }
    }

    protected void queryAllDataCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userCount = userService.queryTotalCount();
        int productCount = productService.queryTotalCount();
        int orderCount = orderService.queryTotalCount();

        LayuiData layuiData = new LayuiData();
        JSONObject field = new JSONObject();
        field.put("userCount",userCount);
        field.put("productCount",productCount);
        field.put("orderCount",orderCount);

        layuiData.setField(field);
        layuiData.setCount(userCount+productCount+orderCount);
        layuiData.setCount(0);
        layuiData.setMsg("");
        resp.getWriter().println(layuiData.getData());
    }

    protected void updateCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = WebUtils.parseInt(req.getParameter("id"),-1);
        String name = req.getParameter("name");
        String descr = req.getParameter("descr");
        Category c = new Category();
        c.setName(name);
        c.setDescr(descr);
        c.setId(id);
        categoryService.updateCategoryById(c);
    }

    protected void addCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer pid = Integer.parseInt(req.getParameter("pid"));
        String name = req.getParameter("name");
        String url = req.getParameter("url");
        String descr = req.getParameter("descr");
        Category c = new Category();
        c.setPid(pid);
        c.setName(name);
        c.setDescr(descr);
        categoryService.addCategory(c);
    }

    protected void queryCategories(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = categoryService.queryCategories();
        JSONArray jsonArray = new JSONArray();
        for (Category c : categories) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",c.getId());
            jsonObject.put("name",c.getName());
            jsonObject.put("pid",c.getPid());
            jsonObject.put("menuIcon", "layui-icon-set");
            jsonObject.put("createtime",c.getCreatetime());
            jsonObject.put("descr",c.getDescr());
            jsonObject.put("isleaf",c.isLeaf());
            jsonObject.put("checked",0);
            jsonObject.put("updateTime","2018/07/13 09:13:42");
            jsonArray.add(jsonObject);
        }
        JSONObject data = new JSONObject();
        data.put("code",0);
        data.put("count",categories.size());
        data.put("msg","");
        data.put("data",jsonArray);
        resp.getWriter().println(data);
    }

    protected void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        userService.deleteUserById(Integer.parseInt(id));
    }

    protected void queryUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uname = req.getParameter("uname");
        String phone = req.getParameter("phone");
        Integer pageNo = WebUtils.parseInt(req.getParameter("page"),0);
        Integer pageSize = WebUtils.parseInt(req.getParameter("limit"),10);
        List<User> users = new ArrayList<User>();
        int count = userService.queryUsers(users, uname, phone ,pageNo , pageSize);
        JSONArray jsonArray = new JSONArray();
        for (User user : users) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",user.getId());
            jsonObject.put("username",user.getUsername());
            jsonObject.put("password",user.getPassword());
            jsonObject.put("phone",user.getPhone());
            jsonObject.put("createtime",user.getCreateTime());
            jsonObject.put("email",user.getEmail());
            jsonArray.add(jsonObject);
        }
        JSONObject data = new JSONObject();
        data.put("data",jsonArray);
        data.put("code",0);
        data.put("msg","");
        data.put("count", count);
        resp.getWriter().println(data);
    }

    protected void saveUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        try {
            if(!userService.usernameExist(username)){
                String password = req.getParameter("password");
                String phone = req.getParameter("phone");
                String email = req.getParameter("email");
                User u = new User(null, username, password, phone, new Date(), email);
                userService.saveUser(u);
                resp.getWriter().println("successful");
            }
        } catch (UserExistException e) {
            resp.getWriter().println("Unsuccessful");
        }
    }

    protected void updateUserOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        try {
            if(!userService.usernameExist(username)){
                String password = req.getParameter("password");
                String phone = req.getParameter("phone");
                String email = req.getParameter("email");
                Integer id = Integer.parseInt(req.getParameter("id"));
                User u = new User(id, username, password, phone, null, email);
                userService.updateUserById(u);
                resp.getWriter().println("successful");
            }
        } catch (UserExistException e) {
            resp.getWriter().println("Unsuccessful");
        }
    }

    protected void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("id",id);
        req.getRequestDispatcher("/admin/pages/user/edit.jsp").forward(req, resp);
    }

    protected void queryProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        Integer categoryid = WebUtils.parseInt(req.getParameter("categoryid"),-1);
        Double lowprice = WebUtils.parseDouble(req.getParameter("lowprice"), 0.0);
        Double highprice = WebUtils.parseDouble(req.getParameter("highprice"),1000000000000000.0);
        Integer pageNo = WebUtils.parseInt(req.getParameter("page"),0);
        Integer pageSize = WebUtils.parseInt(req.getParameter("limit"),10);
        List<Product> products = productService.queryProducts(categoryid,keyword,lowprice,highprice,pageNo,pageSize);
        int totalCount = productService.queryTotalCount();
        JSONArray jsonArray = new JSONArray();
        for (Product product : products) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",product.getId());
            jsonObject.put("name",product.getName());
            jsonObject.put("price",product.getPrice());
            jsonObject.put("url",product.getUrl());
            jsonObject.put("descr",product.getDescr());
            jsonObject.put("createtime",product.getCreatetime());
            jsonObject.put("categoryid",product.getCategoryId());
            JSONArray category = new JSONArray();
            JSONObject cdata = new JSONObject();
            Category c = product.getCategory();
            cdata.put("id",c.getId());
            cdata.put("pid",c.getPid());
            cdata.put("isleaf",c.isLeaf());
            cdata.put("descr",c.getDescr());
            cdata.put("createtime",c.getCreatetime());
            cdata.put("grade",c.getGrade());
            cdata.put("name",c.getName());
            category.add(cdata);
            jsonObject.put("category",category);
            jsonArray.add(jsonObject);
        }
        JSONObject data = new JSONObject();
        data.put("data",jsonArray);
        data.put("code",0);
        data.put("msg","");
        data.put("count",totalCount);
        resp.getWriter().println(data);
    }

    protected void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        productService.deleteProductById(Integer.parseInt(id));
    }

    protected void updateProductOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product p = new Product();
        JSONArray jsonArray = new JSONArray();
        JSONObject object = new JSONObject();
        //判断表单上传是否为多段
        if(ServletFileUpload.isMultipartContent(req)){
//            创建工厂实例
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
//            创建文件上传
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            try {
                List<FileItem> list = servletFileUpload.parseRequest(req);
                for (FileItem fileItem : list) {
                    if(!fileItem.isFormField()){
                        fileItem.write(new File("E:\\Study\\Java\\java-project\\shopping\\src\\main\\webapp\\static\\images\\product\\"+fileItem.getName()));
                        p.setUrl("static/images/product/" + fileItem.getName());
                        object.put("url",p.getUrl());
                    } else {
                        if(fileItem.getFieldName().equals("name")){
                            p.setName(fileItem.getString("UTF-8"));
                            object.put("name",p.getName());
                        } else if(fileItem.getFieldName().equals("price")){
                            p.setPrice(new BigDecimal(fileItem.getString("UTF-8")));
                            object.put("price",p.getPrice());
                        } else if(fileItem.getFieldName().equals("descr")){
                            p.setDescr(fileItem.getString("UTF-8"));
                            object.put("descr",p.getDescr());
                        } else if(fileItem.getFieldName().equals("id")) {
                            p.setId(Integer.parseInt(fileItem.getString("UTF-8")));
                            object.put("id",p.getId());
                        } else if(fileItem.getFieldName().equals("categoryid")){
                            p.setCategoryId(WebUtils.parseInt(fileItem.getString("UTF-8"),1));
                            object.put("categoryid",p.getCategoryId());
                        } else if(fileItem.getFieldName().equals("url")){
                            p.setUrl(fileItem.getString("UTF-8"));
                            object.put("url",p.getUrl());
                        }
                    }
                }
                productService.updateProductById(p);
                jsonArray.add(object);
                JSONObject data = new JSONObject();
                data.put("code",1);
                data.put("msg","上传成功");
                data.put("data",jsonArray);
                resp.getWriter().println(data);
            } catch (Exception e) {
                e.printStackTrace();
                jsonArray.add(object);
                JSONObject data = new JSONObject();
                data.put("code",0);
                data.put("msg","上传失败");
                data.put("data",jsonArray);
                resp.getWriter().println(data);
            }
        } else {
            Integer id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            Double price = WebUtils.parseDouble(req.getParameter("price"),0.0);
            String url = req.getParameter("url");
            Integer categoryId = WebUtils.parseInt(req.getParameter("categoryid"),0);
            String descr = req.getParameter("descr");
            p.setId(id);
            p.setName(name);
            p.setPrice(new BigDecimal(price));
            p.setCategoryId(categoryId);
            p.setUrl(url);
            p.setDescr(descr);
            productService.updateProductById(p);

            jsonArray.add(object);
            JSONObject data = new JSONObject();
            data.put("code",0);
            data.put("msg","上传失败");
            data.put("data",jsonArray);
            resp.getWriter().println(data);
        }
    }

    protected void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        Double price = WebUtils.parseDouble(req.getParameter("price"),0.0);
        String url = req.getParameter("url");
        Integer categoryId = WebUtils.parseInt(req.getParameter("categoryid"),0);
        String descr = req.getParameter("descr");
        req.setAttribute("name",name);
        req.setAttribute("price",price);
        req.setAttribute("url",url);
        req.setAttribute("descr",descr);
        req.setAttribute("id",id);
        req.setAttribute("categoryid",categoryId);
        List<Category> categories = categoryService.queryCategories();
        req.setAttribute("categories",categories);
        req.getRequestDispatcher("/admin/pages/product/edit.jsp").forward(req, resp);
    }

    protected void addProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categoryid", req.getParameter("categoryid"));
        List<Category> categories = categoryService.queryCategories();
        req.setAttribute("categories",categories);
        req.getRequestDispatcher("/admin/pages/product/add.jsp").forward(req, resp);
    }

    protected void addProductOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product p = new Product();
        JSONArray jsonArray = new JSONArray();
        JSONObject object = new JSONObject();
        //判断表单上传是否为多段
        if(ServletFileUpload.isMultipartContent(req)){
//            创建工厂实例
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
//            创建文件上传
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            try {
                List<FileItem> list = servletFileUpload.parseRequest(req);
                for (FileItem fileItem : list) {
                    if(!fileItem.isFormField()){
                        fileItem.write(new File("E:\\Study\\Java\\java-project\\shopping\\src\\main\\webapp\\static\\images\\product\\"+fileItem.getName()));
                        p.setUrl("static/images/product/" + fileItem.getName());
                        object.put("url",p.getUrl());
                    } else {
                        if(fileItem.getFieldName().equals("name")){
                            p.setName(fileItem.getString("UTF-8"));
                            object.put("name",p.getName());
                        } else if(fileItem.getFieldName().equals("price")){
                            p.setPrice(new BigDecimal(fileItem.getString("UTF-8")));
                            object.put("price",p.getPrice());
                        } else if(fileItem.getFieldName().equals("descr")){
                            p.setDescr(fileItem.getString("UTF-8"));
                            object.put("descr",p.getDescr());
                        } else if(fileItem.getFieldName().equals("categoryid")){
                            p.setCategoryId(WebUtils.parseInt(fileItem.getString("UTF-8"),1));
                            object.put("categoryid",p.getCategoryId());
                        }
                    }
                }
                p.setCreatetime(new Date());
                productService.addProduct(p);
                jsonArray.add(object);
                JSONObject data = new JSONObject();
                data.put("code",1);
                data.put("msg","上传成功");
                data.put("data",jsonArray);
                resp.getWriter().println(data);
            } catch (Exception e) {
                e.printStackTrace();
                jsonArray.add(object);
                JSONObject data = new JSONObject();
                data.put("code",0);
                data.put("msg","上传失败");
                data.put("data",jsonArray);
                resp.getWriter().println(data);
            }
        }
    }
}

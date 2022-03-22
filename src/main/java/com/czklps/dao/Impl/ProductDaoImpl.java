package com.czklps.dao.Impl;

import com.czklps.Pojo.Category;
import com.czklps.Pojo.Product;
import com.czklps.dao.CategoryDao;
import com.czklps.dao.ProductDao;
import com.czklps.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private CategoryDao categoryDao = new CategoryDaoImpl();
    /**
     * 查询并返回所有商品
     * @return
     */
    @Override
    public List<Product> queryProducts() {
        Connection conn = DBUtils.getConn();
        String sql = "select product.id pid,product.name pname, product.url purl,product.price pprice,product.descr pdescr, product.createtime pctime, product.categoryid pcid," +
                            "category.id cid, category.pid cpid, category.name cname, category.isleaf cisleaf, category.grade cgrade, category.createtime cctime, category.descr cdescr" +
                    " from product join category on(product.categoryid = category.id)";
        Statement state = null;
        ResultSet rs = null;
        List<Product> products = new ArrayList<>();
        try {
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while(rs.next()){
                Product p = new Product();
                p.setId(rs.getInt("pid"));
                p.setName(rs.getString("pname"));
                p.setDescr(rs.getString("pdescr"));
                p.setPrice(rs.getBigDecimal("pprice"));
                p.setCreatetime(rs.getDate("pctime"));
                p.setUrl(rs.getString("purl"));
                p.setCategoryId(rs.getInt("pcid"));
                Category c = new Category();
                c.setId(rs.getInt("cid"));
                c.setPid(rs.getInt("cpid"));
                c.setDescr(rs.getString("cdescr"));
                c.setName(rs.getString("cname"));
                c.setCreatetime(rs.getDate("cctime"));
                c.setLeaf(rs.getInt("cisleaf") == 0 ? true : false);
                c.setGrade(rs.getInt("cgrade"));
                p.setCategory(c);
                products.add(p);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtils.closeRs(rs);
            DBUtils.closeState(state);
            DBUtils.closeConn(conn);
        }
        return products;
    }

    @Override
    public int queryTotalCount() {
        Connection conn = DBUtils.getConn();
        String sql = "select count(*) from product";
        ResultSet rs = null;
        Statement state = null;
        try {
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            rs.next();
            return rs.getInt("count(*)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtils.closeState(state);
            DBUtils.closeConn(conn);
        }
        return 0;
    }

    /**
     * 搜索查询 分页查询接口
     * @param categoryId 类别id
     * @param keyword 关键字
     * @param lowprice 最低价
     * @param highprice 最高价
     * @param pageNo 当前页码
     * @param pageSize 每页显示多少条数据
     * @return 返回查询到的产品
     */
    @Override
    public List<Product> queryProducts(Integer categoryId, String keyword, double lowprice, double highprice, Integer pageNo, Integer pageSize) {
        Connection conn = DBUtils.getConn();
        String sql = "select product.id pid,product.name pname, product.url purl,product.price pprice,product.descr pdescr, product.createtime pctime, product.categoryid pcid," +
                            "category.id cid, category.pid cpid, category.name cname, category.isleaf cisleaf, category.grade cgrade, category.createtime cctime, category.descr cdescr" +
                " from product join category on(product.categoryid = category.id)" +
                " where 1 = 1";
        Statement state = null;
        ResultSet rs = null;
        List<Product> products = new ArrayList<>();
        try {
            state = conn.createStatement();
            if(lowprice >= 0){
                sql += " and price > " + lowprice;
            }
            if(highprice > 0){
                sql += " and price < " + highprice;
            }
            if(categoryId != null && categoryId != -1){
                sql += " and categoryid = " + categoryId;
            }
            if(keyword != null && !keyword.trim().equals("")){
                sql += " and product.name like '%" + keyword + "%' or product.descr like '%" + keyword + "%'";
            }
            sql += " limit " + (pageNo - 1) * pageSize + "," + pageSize;
            rs = state.executeQuery(sql);
            while(rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("pid"));
                product.setName(rs.getString("pname"));
                product.setDescr(rs.getString("pdescr"));
                product.setPrice(rs.getBigDecimal("pprice"));
                product.setCreatetime(rs.getDate("pctime"));
                product.setUrl(rs.getString("purl"));
                product.setCategoryId(rs.getInt("pcid"));
                Category c = new Category();
                c.setId(rs.getInt("cid"));
                c.setPid(rs.getInt("cpid"));
                c.setDescr(rs.getString("cdescr"));
                c.setName(rs.getString("cname"));
                c.setCreatetime(rs.getDate("cctime"));
                c.setLeaf(rs.getInt("cisleaf") == 0 ? true : false);
                c.setGrade(rs.getInt("cgrade"));
                product.setCategory(c);
                products.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtils.closeRs(rs);
            DBUtils.closeState(state);
            DBUtils.closeConn(conn);
        }
        return products;
    }

    /**
     * 根据 id 删除商品 删除成功则返回影响的行数 失败则返回0
     * @param id
     * @return
     */
    @Override
    public int deleteProductById(Integer id) {
        Connection conn = DBUtils.getConn();
        String sql = "delete from `product` where id = ?";
        PreparedStatement pstate = null;
        try {
            conn.setAutoCommit(false);
            pstate = conn.prepareStatement(sql);
            pstate.setObject(1, id);
            int line = pstate.executeUpdate();
            conn.commit();
            return line;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            DBUtils.closeState(pstate);
            DBUtils.closeConn(conn);
        }
        return 0;
    }


    /**
     * 向数据库添加一个商品
     * @param p
     * @return
     */
    @Override
    public int insertProduct(Product p) {
        Connection conn = DBUtils.getConn();
        String sql = "insert into `product` values(null, ?, ?, ?, ?, ?,?)";
        PreparedStatement pstate = null;
        try {
            conn.setAutoCommit(false);
            pstate = conn.prepareStatement(sql);
            pstate.setObject(1,p.getName());
            pstate.setObject(2,p.getUrl());
            pstate.setObject(3,p.getPrice());
            pstate.setObject(4,p.getDescr());
            pstate.setObject(5,p.getCreatetime());
            pstate.setObject(6,p.getCategoryId());
            int line = pstate.executeUpdate();
            conn.commit();
            return line;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            DBUtils.closeState(pstate);
            DBUtils.closeConn(conn);
        }
        return 0;
    }

    /**
     * 根据 id 修改商品信息
     * @param p
     * @return
     */
    @Override
    public int updateProductById(Product p) {
        Connection conn = DBUtils.getConn();
        String sql = "update `product` set `name` = ?,`url` = ?,`price` = ?,`descr` = ?,`categoryid` = ?  where id = ?";
        PreparedStatement pstate = null;
        try {
            conn.setAutoCommit(false);
            pstate = conn.prepareStatement(sql);
            pstate.setObject(1, p.getName());
            pstate.setObject(2, p.getUrl());
            pstate.setObject(3, p.getPrice());
            pstate.setObject(4, p.getDescr());
            pstate.setObject(5, p.getCategoryId());
            pstate.setObject(6, p.getId());
            int line = pstate.executeUpdate();
            conn.commit();
            return line;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            DBUtils.closeState(pstate);
            DBUtils.closeConn(conn);
        }
        return 0;
    }

    /**
     * 根据商品 id 查询商品
     * @param id
     * @return
     */
    @Override
    public Product queryProductById(Integer id) {
        Connection conn = DBUtils.getConn();
        String sql = "select * from `product` where id = ?";
        PreparedStatement pstate = null;
        Product p = null;
        ResultSet rs = null;
        try {
            pstate = conn.prepareStatement(sql);
            pstate.setObject(1, id);
            rs = pstate.executeQuery();
            if(rs.next()){
                p = new Product();
                p.setId(Integer.parseInt(rs.getString("id")));
                p.setUrl(rs.getString("url"));
                p.setDescr(rs.getString("descr"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getBigDecimal("price"));
                return p;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtils.closeRs(rs);
            DBUtils.closeState(pstate);
            DBUtils.closeConn(conn);
        }
        return p;
    }

    @Override
    public int updateProductImgPath(Product p) {
        Connection conn = DBUtils.getConn();
        String sql = "update `product` set `url` = ? where id = ?";
        PreparedStatement pstate = null;
        try {
            conn.setAutoCommit(false);
            pstate = conn.prepareStatement(sql);
            pstate.setObject(1, p.getUrl());
            pstate.setObject(2, p.getId());
            int line = pstate.executeUpdate();
            conn.commit();
            return line;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            DBUtils.closeState(pstate);
            DBUtils.closeConn(conn);
        }
        return 0;
    }
}

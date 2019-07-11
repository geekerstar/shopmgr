package com.geekerstar.shop.service;

import com.geekerstar.shop.bean.Article;
import com.geekerstar.shop.bean.ArticleType;
import com.geekerstar.shop.bean.User;
import com.geekerstar.shop.repository.ArticleMapper;
import com.geekerstar.shop.repository.ArticleTypeMapper;
import com.geekerstar.shop.repository.UserMapper;
import com.geekerstar.shop.utils.Pager;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("shopService")
public class ShopServiceImpl implements ShopService {

    // 得到数据访问层对象
    @Resource
    private ArticleTypeMapper articleTypeMapper;

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private UserMapper userMapper;

    public List<ArticleType> getArticleTypes() {
        return articleTypeMapper.getArticleTypes();
    }

    public Map<String, Object> login(String loginName, String passWord) {
        Map<String, Object> results = new HashMap<String, Object>();
        // 1.判断参数是否为空的
        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(passWord)) {
            // 参数为空了
            results.put("code", 1);
            results.put("msg", "参数为空了");
        } else {
            // 根据登录名称去查询用户对象
            User user = userMapper.login(loginName);
            if (user != null) {
                // 判断密码
                if (user.getPassword().equals(passWord)) {
                    // 登陆成功了
                    // 应该将登陆成功的用户存入到Session会话中
                    results.put("code", 0);
                    results.put("msg", user);
                } else {
                    // 密码错误了
                    results.put("code", 2);
                    results.put("msg", "密码错误了");
                }
            } else {
                // 登陆名不存在
                results.put("code", 3);
                results.put("msg", "登陆名不存在");
            }

        }
        return results;
    }


    public List<ArticleType> loadFirstArticleTypes() {
        List<ArticleType> articleTypes = articleTypeMapper.getFirstArticleTypes();
        return articleTypes;
    }


    public List<ArticleType> loadSecondTypes(String typeCode) {
        List<ArticleType> articleTypes = articleTypeMapper.loadSecondTypes(typeCode + "%", typeCode.length() + 4);
        return articleTypes;
    }


    public void deleteById(String id) {
        articleMapper.deleteById(id);
    }


    public Article getArticleById(String id) {
        return articleMapper.getArticleById(id);
    }


    public void updateArticle(Article article) {
        articleMapper.update(article);
    }


    public void saveArticle(Article article) {
        article.setCreateDate(new Date());
        articleMapper.save(article);
    }


    public List<Article> searchArticles(String typeCode, String secondType
            , String title, Pager pager) {
        // 界面需要当前总共有多少条数据
        // 查询当前条件下总共有多少条数据
        int count = articleMapper.count(typeCode, secondType, title);
        pager.setTotalCount(count);
        return articleMapper.searchArticles(typeCode, secondType, title, pager);
    }


}

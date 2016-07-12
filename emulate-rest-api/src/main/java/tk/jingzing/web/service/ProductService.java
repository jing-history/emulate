package tk.jingzing.web.service;

import tk.jingzing.web.model.Product;

import java.util.List;

/**
 * ProductService : 商品业务接口
 * Created by Louis Wang on 2016/7/12.
 */

public interface ProductService {

    /**
     * 添加商品
     * @param product
     * @return
     */
    Product addModel(Product product);

    /**
     * 查看单个商品详情
     * @param id
     * @return
     */
    Product getModelById(String id);

    /**
     * 修改产品
     * @param product
     * @return
     */
    Product modifyModelById(Product product);

    /**
     * 删除指定产品
     * @param id
     */
    void removeModelById(String id);

    /**
     * 获取所有商品
     * @return
     */
    List<Product> getModels();

    /************** 收藏业务 **************/

    /**
     * 收藏商品
     * @param id
     * @return
     */
    List<Product> starProduct(String id);

    /**
     * 解除收藏商品
     * @param id
     * @return
     */
    List<Product> unStarProduct(String id);
}

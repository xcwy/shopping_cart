package io.reactivesw.shoppingcart.application;

import io.reactivesw.shoppingcart.application.grpc.ScGrpcService;
import io.reactivesw.shoppingcart.domain.model.ShoppingCart;
import io.reactivesw.shoppingcart.domain.model.ShoppingCartSku;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * business service for shopping cart list.
 * @author janeli
 */
@Service
public class GetSkuInfoListApp {

  /**
   * class logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(ScGrpcService.class);

  /**
   * get sku info service.
   */
  @Autowired
  private transient GetSkuInfoApp getSkuInfoApp;

  /**
   * get sku info for shopping cart.
   * @param itemList List ShoppingCart
   * @return List
   */
  public List<ShoppingCartSku> getShoppingCartSkuInfoList(List<ShoppingCart> itemList) {
    LOGGER.debug("app service: get product info list for shopping cart list {}", itemList);
    List<Long> skuIdList = this.getSkuIdList(itemList);
    List<ShoppingCartSku> skuInfoList = getSkuInfoApp.getSkuInfoList(skuIdList);
    return getSkuInfoApp.organizeShoppingCartSkuList(itemList, skuInfoList);
  }

  /**
   * get sku id list.
   * @param itemList ShoppingCart List
   * @return List long
   */
  private List<Long> getSkuIdList(List<ShoppingCart> itemList) {
    LOGGER.debug("app service: get sku id list of shopping cart list {}", itemList);
    List<Long> skuIdList =
        itemList.stream().map(ShoppingCart::getSkuId).collect(Collectors.toList());
    LOGGER.debug("app service: sku id list {}", skuIdList);
    return skuIdList;
  }

}
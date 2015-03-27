package beans;

/**
 *商品マスタテーブル用beans
 */
public class GoodsDTO implements DTO{
	private static final long serialVersionUID = -8779192381072311546L;
	private String goodsId;			//商品ID
	private String goodsName;		//商品名
	private String goodsGroupName;	//グループ名
	private Integer cost;			//仕入単価
	private Integer wholesale;		//販売単価

	/**
	 *　商品IDゲッター
	 * @return　商品ID
	 */
	public String getGoodsId() {
		return goodsId;
	}

	/**
	 *　商品IDセッター
	 * @param goodsId
	 */
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	/**
	 *　商品名ゲッター
	 * @return 商品名
	 */
	public String getGoodsName() {
		return goodsName;
	}

	/**
	 *　商品名セッター
	 * @param goodsName
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**
	 *　グループ名ゲッター
	 * @return　グループ名
	 */
	public String getGoodsGroupName() {
		return goodsGroupName;
	}

	/**
	 *　グループ名セッター
	 * @param goodsGroupName
	 */
	public void setGoodsGroupName(String goodsGroupName) {
		this.goodsGroupName = goodsGroupName;
	}

	/**
	 *　仕入単価ゲッター
	 * @return　仕入単価
	 */
	public Integer getCost() {
		return cost;
	}

	/**
	 *　仕入単価セッター
	 * @param cost
	 */
	public void setCost(String cost) {
		if(cost == null){
			this.cost = null;
		}else{
			this.cost = Integer.parseInt(cost);
		}
	}

	/**
	 *　販売単価ゲッター
	 * @return　販売単価
	 */
	public Integer getWholesale() {
		return wholesale;
	}

	/**
	 *　販売単価セッター
	 * @param wholesale
	 */
	public void setWholesale(String wholesale) {
		if(wholesale == null){
			this.wholesale = null;
		}else{
			this.wholesale = Integer.parseInt(wholesale);
		}
	}


}

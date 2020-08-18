package entity;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Item { 
	private String itemId;
	private String name;
	private String address;
	private Set<String> keywords;
	private String imageUrl;
	private String url;
	
	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		obj.put("item_id", itemId);
		obj.put("name", name);
		obj.put("address", address);
		obj.put("keywords", new JSONArray(keywords));
		obj.put("image_url", imageUrl);
		obj.put("url", url);
		return obj;
	}
}
 


//
//Method 2：
//package entity;
//
//import java.util.Set;
//
//public class Item {
//	private final String itemId;
//	private final String name;
//	private final String address;
//	private final Set<String> keywords;
//	private final String imageUrl;
//	private final String url;
//	
//	
//	//不要写成public，不给用户用，用户可能乱用
//	//为实现一个功能，只提供一种方法
//	//ItemBuilder不可以放在外面
//	private Item(ItemBuilder builder) {
//		//一一对应就可，没有顺序要求
//		this.itemId = builder.itemId;
//		this.name = builder.name;
//		this.address = builder.address;
//		this.imageUrl = builder.imageUrl;
//		this.url = builder.url;
//		this.keywords = builder.keywords;
//	}
//	
//	public String getItemId() {
//		return itemId;
//	}
//	public String getName() {
//		return name;
//	}
//	public String getAddress() {
//		return address;
//	}
//	public Set<String> getKeywords() {
//		return keywords;
//	}
//	public String getImageUrl() {
//		return imageUrl;
//	}
//	public String getUrl() {
//		return url;
//	}
//	
//	//Item需要达到只读的效果，所以不能再Item中用setter初始化，需要ItemBuilder
//	//Item用ItemBuilder创建完之后，没有setter，满足只读原则
//
//	//要是nested class，不要放在package中，因为要调用Item的private的构造函数
//	//要是static：ItemBuilder的instance在有Item的instance之前就要创建，要可以在Item的对象不存在之前
//	//就可以把ItemBuilder实例化
//	//要是public，在package之外也可以用
//	public static class ItemBuilder {
//		private String itemId;
//		private String name;
//		private String address;
//		private Set<String> keywords;
//		private String imageUrl;
//		private String url;
//		
//		public void setItemId(String itemId) {
//			this.itemId = itemId;
//		}
//
//		public void setName(String name) {
//			this.name = name;
//		}
//
//		public void setAddress(String address) {
//			this.address = address;
//		}
//
//		public void setImageUrl(String imageUrl) {
//			this.imageUrl = imageUrl;
//		}
//
//		public void setUrl(String url) {
//			this.url = url;
//		}
//
//		public void setKeywords(Set<String> keywords) {
//			this.keywords = keywords;
//		}
//
//		//一旦build之后就不会再变了，item和builder就没有关系了
//		//新建一个Item，deep copy，不是pass by reference，因此set之后再build才有用
//		//不重新set，再build会有残留值
//		public Item build() {
//			return new Item(this);
//		}
//	}	
//}

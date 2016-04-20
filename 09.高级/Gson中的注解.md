## 代码
	@Expose
    @SerializedName("message")
    private String message;
## 解析
1.使用@Expose可以区分实体中不想被序列化的属性

	@Expose标签的2个属性. 
  	1.1 deserialize (boolean) 反序列化 默认 true
    1.2 serialize  (boolean) 序列化 默认 true

    使用 new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	创建Gson对象，没有@Expose注释的属性将不会被序列化

	public class User {
		@Expose
		private String username;

		@Expose(serialize=false)// 这个表示不序列化这个属性
		private int age ;

		private List<String> list;

		public User(String username, int age) {
		super();
		this.username = username;
		this.age = age;
		}
		public String getUsername() {
		return username;
		}
		public void setUsername(String username) {
		this.username = username;
		}
		public int getAge() {
		return age;
		}
		public void setAge(int age) {
		this.age = age;
		}
		public List<String> getList() {
		return list;
		}
		public void setList(List<String> list) {
		this.list = list;
	}
	public static void main(String []args){
		User user = new User("lemon",27);
		List<String> list = new ArrayList<String>();
		list.add("l1");
		list.add("l2");
		user.setList(list);
		Gson g1 = new Gson();//使用 new Gson();
		
		System.out.println(g1.toJson(user)); //{"username":"lemon","age":27,"list":["l1","l2"]}
		//使用 new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		Gson g2 = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		System.out.println(g2.toJson(user)); //{"username":"lemon"}
	  }
	}

2.使用@SerializedName标签定义属性序列化后的名字

	@Expose
	@SerializedName("name")
	private String username;
	
	public static void main(String []args){
		User user = new User("lemon",27);
		List<String> list = new ArrayList<String>();
		list.add("l1");
		list.add("l2");
		user.setList(list);
		Gson g1 = new Gson();//使用 new Gson();
		System.out.println(g1.toJson(user));
		//输出结果：{"name":"lemon","age":27,"list":["l1","l2"]}
		 
		//使用 new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		Gson g2 = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		
		System.out.println(g2.toJson(user));
		//输出结果：{"name":"lemon"}
	}
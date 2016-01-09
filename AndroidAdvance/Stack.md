#Java.util.Stack
##示例代码：

	public class StackDemo {
		public static void main(String[] args) {
			Stack stack=new Stack();
			stack.push("0");
			stack.push(new Integer(1));
			stack.push(2.0);
			stack.push(new Date());
			
			System.out.println("stack.size()==>"+stack.size());
			System.out.println(stack);
			
			System.out.println("stack.pop()==>"+stack.pop());
			System.out.println("stack.peek()==>"+stack.peek());//peek()方法 查看栈顶对象而不移除它
			System.out.println("stack.pop()==>"+stack.pop());
		}
	}

##运行结果：

	stack.size()==>4
	[0, 1, 2.0, Tue Jul 21 22:50:40 CST 2009]
	stack.pop()==>Tue Jul 21 22:50:40 CST 2009
	stack.peek()==>2.0
	stack.pop()==>2.0

##文档中的解释
>Stack 类表示后进先出（LIFO）的对象堆栈。它通过五个操作对类 Vector 进行了扩展 ，允许将向量视为堆栈。它提供了通常的 push 和 pop 操作，以及取堆栈顶点的 peek 方法、测试堆栈是否为空的 empty 方法、在堆栈中查找项并确定到堆栈顶距离的 search 方法。 

	public class Stack<E>extends Vector<E>

##方法详解
### 构造方法
	public Stack()创建一个空堆栈。

### 普通方法
	public E push(E item)把项压入堆栈顶部。其作用与下面的方法完全相同： 
	 		addElement(item)
		参数：
			item - 压入堆栈的项。 
		返回：
			item 参数。
	--------------------------------------------------------------------------------

	public E pop()移除堆栈顶部的对象，并作为此函数的值返回该对象。 
		返回：
			堆栈顶部的对象（Vector 对象中的最后一项）。 
		抛出： 
			EmptyStackException - 如果堆栈为空。
	--------------------------------------------------------------------------------

	public E peek()查看堆栈顶部的对象，但不从堆栈中移除它。 
		返回：
			堆栈顶部的对象（Vector 对象的最后一项）。 
		抛出： 
			EmptyStackException - 如果堆栈为空。
	
	--------------------------------------------------------------------------------

	public boolean empty()测试堆栈是否为空。 
		返回：
			当且仅当堆栈中不含任何项时返回 true；否则返回 false。
	
	--------------------------------------------------------------------------------

	public int search(Object o)
		返回对象在堆栈中的位置，以 1 为基数。如果对象 o 是堆栈中的一个项，
		此方法返回距堆栈顶部最近的出现位置到堆栈顶部的距离；堆栈中最顶部项的距离为 1。
		使用 equals 方法比较 o 与堆栈中的项。 
		
		参数：
			o - 目标对象。 
		返回：
			对象到堆栈顶部的位置，以 1 为基数；返回值 -1 表示此对象不在堆栈中。

##项目中使用的方法
	/**
	 * 反回当前Tab显示中的Fragment
	 * @return
	 */
	protected Fragment getFragment() {
	    if (fragments.isEmpty()) {
	        return null;
	    }
		// java.util.Stack类的peek方法：查看栈顶对象而不移除它，返回值是栈顶元素
		// pop方法才是移除栈顶元素，返回值为移除的元素
	    return fragments.peek().fragment;
	}
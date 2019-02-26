Java的面试题:
1. == 和 equals的区别:
   基本数据类型的==是比较值相等
   类的==比较的内存地址,即是否是同一个对象
   如果两个对象equals,那么他们的hashcode一定相等
   如果不equals,那么hashcode可能相等
   如果hashcode相等,不一定equals.
   如果hashcode不相等,一定不equals
 2. int与integer的区别
 	int 是基本数据类型
 	integer对象,int的封装类
 3. String,StringBuffer,StringBuilder的区别
 	String:字符常量,不适用与经常修改值的情况,每次改变都相当于生成了一个对象
 	StringBuffer:字符串变量(线程安全)
 	StringBuilder: 字符串变量(线程不安全),效率高于StringBuffer
 4.什么是内部类,内部类的作用
 	内部类可直接访问外部类的属性
 	Java内部类主要分为成员内部类,局部内部类,匿名内部类,静态内部类(不能使用任何外部类非静态的额成员变量和方法)
 5.进程和线程的区别:
 	进程是CPU资源分配的最小单元,线程是cpu调度的最小单元
 	进程之间不能共享资源,线程共享所在进程的地址空间和其他资源
 	一个进程内可以拥有多个线程,进程可以开始进程,也可以开启线程
 	一个线程只能属于一个进程,线程可以直接使用同进程的资源,线程依赖于进程而存在
 6.final,finally finalize的区别:
 	final: 修饰类,成员变量,成员方法,类不能被继承,成员变量不能被修改,成员方法不能被重写
 	finally: 与try...catch共同使用,确保无论是否出现了异常都能被调用到
 	finalize:类的方法,垃圾回收之前会调用该方法,子类可以重写finalize()方法实现对资源的回收

 7.Serializable 和 Parcelable的区别:
 	Serializable Java  序列化接口在硬盘上读写,读写过程中会有大量的临时变量生成,内部执行大量的io操作,效率较低
 	Parcelable Android 序列化接口,效率高,使用麻烦,在内存中读写,对象不能保存到磁盘中
 8.静态属性和静态方法是否可以被继承,是否可以被重写,为什么
 	可以继承,不能被重写,而是被隐藏了,
 	如果子类里面有静态方法和静态属性,那么这个时候父类的静态方法或者属性称之为"隐藏"
 	如果想调用父类的静态方法和属性,直接通过父类.方法或者变量名完成

 9. 成员内部类,静态内部类,局部内部类和匿名内部类的理解,以及项目中的使用

 	java的内部类主要分为成员内部类,局部内部类(嵌套在方法和作用域内)
 	匿名内部类(没有构造方法),静态内部类(static修饰的类,不能使用任何外部非静态的成员变量和方法)
 	使用内部类是因为每个内部类都能独立继承一个接口的实现,所以无论外部类是否已经继承了某个接口的实现,对于内部类来说都是没有影响的


 10. String 转换成 Integer的方式 原理
 	 Integer.parseInt(string);
 	 Integer.toString();

 11. 那些情况下的对象会被垃圾回收机制处理掉

 	 1. 所有实例都没有活动线程访问
 	 2. 没有被其他任何实例访问的循环引用实例
 	 3. Java中有不同的引用类型,采用标记计数方式来

  12. 静态代理和动态代理的区别, 什么场景使用
  		静态代理:由开发人员编写或者工具生成的源代码,在对其进行编译.在程序运行前,代理类的.class
  		文件就已经存在了,
  		动态代理:在程序运行时,运用反射机制动态创建而成
  13. Java 中实现多态的机制是什么?
  	 方法的重写override和重载overloading是Java多态的不同表现
  	 重写是父类和子类之间多态的一种表现
  	 重载是一个类多态性的一种表现


   14. 说说对Java反射的理解:
   		Java反射是在运行状态中,对任意一个类,都能够知道这个类的所有属性和方法,
   		对于任意一个对象,都能够调用它的任意一个方法和属性.

   15. Java中的String的了解

   		String是用final进行修饰的,是不可更改,不可继承的常量
   		
   		## 设计成不可变原因:

   		1. 字符串池的需求,字符串池是方法区的一块特殊的存储区域,当一个字符串已经创建了

   		并且在该字符串池中,该字符串的引用会立即返回给变量,而不会重新创建一个字符串再将引用返回个变量,
   		如果字符串不是不可变的,那么改变一个引用的字符串将会导致另一个引用出现脏数据


   	    2. 允许字符串缓存哈希码:
   	      HashMap,String的不变性保证了哈希码的唯一,这就意味着每次使用时不必重新计算一次哈希码.效率提高

   	    3. 安全
 		  
 		  如果String不是不变的,网络连接,文件将会被改变导致一系列安全威胁,由于反射中的参数都是字符串,.
 		  同样这些字符串如果被篡改了也会导致一系列安全问题


 	16. Object类的equal和hashCode方法重写为什么?

 		首先equals 和 hashCode关系是这样的
 		1.如果两个对象相同(即equals比较返回true),那么他们的hashcode值一定相同
 		2.如果两个对象的hashcode相同,他们不一定相同(即用equals比较返回false)

 		由于为了提高程序的效率才实现了hashcode方法,先进行hashcode比较
 		如果不同,就没有必要进行equals比较,就大大减少了equals比较次数,

 	17. List,Set,Map的比较
 		Set是最简单的一种集合,集合中的对象不按特定的方式排序,并且没有重复对象.
 			HashSet: 按照哈希算法来存取集合中的对象,存取速度比较快
 			TreeSet: 实现了SortedSet接口,能够对集合中的对象进行排序

 		List:元素以线性方式存储,集合中可以存放重复对象

 			ArrayList:代表长度可以改变的数组,可以对元素进行随机的访问,向ArrayList中插入与删除元素速度慢
 			LinkedList: 在实现中采用链表数据结构,插入和删除速度快,访问速度慢

 		Map: 是一种把键对象和值对象映射的集合,它的每一个元素都包含一对键对象和值对象,从Map检索元素,只要给出键对象
 		就能返回对应的值对象

 		   	HashMap: Map基于散列表的实现,插入和查询"键值对"的开销是固定的.可以通过构造设置容量和
 		   	负载因子以调整容器的性能.

 		   	LinkedHashMap:类似HashMap,但是迭代遍历它时,取得键值对的顺序是插入次序,或者最近最少使用的次序

   18.ArrayMap和HashMap的对比  



   18.1: 接口和抽象类的区别
	
     	1. 从实现方式来说,子类通过extends关键字来继承抽象类,如果子类不是抽象类,就必须实现抽象类中的所有抽象方法,
	      子类通过implements关键字来实现这个接口,需要实现接口中的所有方法

	    2.接口和抽象类的属性来说,子类不能多继承抽象类,但是子类可以多实现接口

	    3.从构造来说:接口没有自己的构造,然后抽象类可以有自己的构造

	    4.从访问修饰符来说:抽象类中的抽象方法可以被public protected default关键字修饰,然后接口中的方法默认是被pubic修复并不能修改


   19. 线程和进程的区别:
   		线程是进程的子集,一个进程可以有多个线程,每条线程并行执行不同的任务,不同的进程使用不同的内存空间,
   		而所有的线程共享一片相同的内容空间,每个线程都拥有单独的栈内存用来存储本地数据

   	20.开启线程的三种方式
   		分别为继承Thread,实现Runnable接口以及Callable接口和使用线程池

    21. Thread的 run() 和 start()方法区别
    	start()方法用来启动新创建的线程,start()会调用内部Runnable的run方法执行一个任务
    	当你调用了run方法的时候,只会在原来的线程中调用,不会有新的线程启动.

    22. 如何控制某个方法允许并发访问线程的个数


    23. 在Java中wait和sleep的不同
     	 Java程序中wait和sleep都会造成某种形式的暂停,但是wait()方法用于线程间通信,只能在同步方法和同步代码块中
     	 使用,sleep可以在任何地方使用,sleep是线程类的方法,调用会暂停线程指定的睡眠时间,
     	 但是监控依然持有,不会释放对象锁,wait是Object的方法,调用会放弃锁,进入等待队列,调用了
     	 notify/notifyAll方法唤醒指定的线程,再次获得对象锁才会进入运行状态.
    24.什么导致线程阻塞.线程如何关闭
       1.线程执行了Thread.sleep()方法,线程放弃CPU,睡眠然后恢复运行
       2.线程执行一段同步代码,由于无法获得相关的同步锁,进入阻塞状态,获得了同步锁,才能进行运行
       3.线程执行了一个对象的wait(),进入阻塞状态,只有等到其他线程执行了该对象的notify或者notifyAll方法,才能将其唤醒
       4.线程执行了io操作,会因为等待相关的资源进入阻塞状态.
    
    25.如何保证线程安全
      1. synchronized
      2. Object方法中的wait,notify
      3. ThreadLocal机制 (重点看看)

    26.如何线程的同步
      1.synchronized关键字修饰的方法
      2.synchronized关键字修饰的语句块
      3.使用特殊域变量volatile实现线程同步

     27.synchronized关键字----类锁,方法锁.重入锁的理解
     Java的对象锁和类锁作用的区别在于,对象锁用于对象实例或者是对象的实例方法,
     类锁则用于类的静态方法或者是一个类的class对象上,类的实例可以有多个,但是每个类
     只有一个class对象,所以不同对象实例的对象锁是互相干扰的,但是每个类只有一个类锁.

     28.synchronized和volatile关键字的区别

      本质区别:volatile关键字解决的是内存可见性的问题.
      		   synchronized关键字解决的是执行控制的问题
      		  1. volatile本质告诉Jvm当前变量在寄存器中的值是不确定的,需要从主存中读取,synchronized则是锁定了
      		  当前变量,只有当前线程可以访问该变量,其他线程被阻塞
      		  2.volatile仅能使用在变量级别,synchronized则可以使用在变量,方法和类级别
      		  3.volatile仅能实现的修改可见性,不能保证原子性,而synchronized则可以保证
      		  变量的修改可见性和原子性
      		  4.volatile不会造成线程的阻塞,synchronized可能会造成线程的阻塞
      		  5.volatile标记的变量不会被编译器优化,synchronized标记的变量可以被编译器优化.

   29. 有个三个线程T1,T2,T3怎么确保他们顺序执行
   		通过线程的join()方法在一个方法中启动另外一个线程,另外一个线程完成该线程继续执行,启动最后一个(T3调用T2,T2调用T1),这样T1就会先完成而T3最后完成

   30.AsyncTask工作原理:
   		是Android提供的一种轻量级的异步任务类,
   		可以在线程池中执行后台任务,然后把执行的进度和最终结果传递给主线程进行Ui更新,
   		其实AsyncTask内部封装了Thread和Handler.

   		AsyncTask提供了四个核心方法,
   		onPreExecute(): 该方法是在主线程中执行的,在执行异步任务之前会被调用,
   		doInBackgroud(String...param):这个方法是在线程池中执行的,此方法用于执行异步任务,
   		在这个方法中可以通过publishProgress方法来更新任务的进度,publishProgress方法会通过handler发送消息会调用
   		onProgressUpdate()方法,另外任务的结果返回给onPostExecute();
   		onProgressUpdate方法:在主线程中执行,主要用于任务进度更新的时候,该方法会被调用
   		onPostExecute方法:在主线程中执行,在异步任务执行完毕之后,该方法会被调用,


   		从源码中可以得知:AsyncTask的execute()方法内部调用了executeOnExecutor()方法,
   		而该方法中的出入一个sDefaultExecutor,这是一个串行的线程池,它的execute方法是被synchronized关键字修饰的,onPreExecute方法在executeOnExecutor()方法中执行的.
   		然后onPreExecute方法在executeOnExecutor()方法中执行的.
   		而AsyncTask的出入的参数被封装成了FutureTask这是一个并发类,然后交由线程池的execute方法去处理,
   		而executor方法首先将FutureTask添加到Task队列,
   		如果这个时候没有任务,就会调用sheduleNext()方法,执行下一个任务,如果有任务,则执行完之后调用scheduleNext();
   		执行下一个任务,直至所有任务被执行完毕, AsyncTask的构造方法中有一个call()方法,
   		由于这个方法会被FutureTask的run方法,因此最终这个call方法会在线程池中运行,
   		而doBackground方法会在这里调用.最终计算的结果会通过调用postResult(result)传递消息,该方法通过handler来发送消息,
   		通过发送一个MESSAGE_POST_RESULT类型的消息,handler在处理到这种消息会回调onPostExecute方法.


   	31.Binder的工作机制:
   		Binder是Android的一个类,实现了IBinder接口,从IPC角度来说,Binder是Android的一种跨进程通信方式的
   		一种手段.从FrameWork角度来说,Binder是ServiceManager的桥梁,从应用层角度来说,
   		Binder是客户端与服务端进行通信的媒介

   		首先介绍一个DESCRIPTOR这个字符串: 它一般以当前Binder的类名作为唯一标识,以便binder调用查询
   		方法找到这个我们定义的这个binder

   		其次asInterface()方法用于将服务端的Binder对象转换为客户端所需要的AIDL接口对象,
   		这种转换过程是区分进程的,如果客户端和服务端位于同一个进程中,那么这个方法会返回服务端的
   		stub对象本身,否则会返回系统封装后的Stub.Proxy对象

   		asBinder():用于返回当前Binder对象.

   		onTransact():该方法运行在服务端额Bindre线程池中,当客户端发起跨进程通信请求的时候,
   		远程请求通过系统底层封装后交于该方法处理,注意这个方法中传入了相应的参数,
   		int类型的code,Parcel类型的data,Parcel类型的relpy,服务端通过code可以确定
   		客户端所请求的目标方法是什么,接着从data中取出目标方法所需要的参数,然后执行目标方法,
   		当目标方法执行完毕后,就向reply中写入返回值.这个方法有返回值的,如果返回false,则客户端会请求失败.
   		我们可以进行一些安全验证, 比如说给Binder设置死亡代理,死亡代理是由DeathRecipient来实现,
   		只要重写binderDied()方法即可

   		linkToDeath():为Binder对象设置死亡代理
   		unlinkToDeath():将设置的死亡代理标志清除

   		具体做法是我们在客户端绑定服务成功之后,调用linkToDeath()方法该方法中传入DeathRecipient
   		在定义个DeathRecipient中重写的binderDied方法调用unlinkToDeath()清除代理标记,然后重新绑定服务.

   		Binder工作机制需要注意的一些问题:
   		1.当客户端发起请求时,由于当前线程被挂起,知道服务端返回数据,如果这个远程方法很耗时的话,
   		那么是不能够在UI线程中,也就是主线程中发起这个远程请求的,可能会导致客户端ANR,这个时候可以]
   		将请求工作放在子线程中进行
   		2.由于Service的Binder方法是运行在线程池中,所以服务端本身定义的接口方法就是在执行大量的耗时操作,这个时候不要在服务端方法中
   		开线程去进行异步任务.
   
    32.view的事件分发和view的工作原理

    	Android自定义view实现的三部曲:onMeasure,onLayout,onDraw,
    	view的绘制从viewRoot的perfromTraversal方法开始的,经过了measure,layout,draw方法才能够将
    	view绘制出来,其中measure是用来测量宽高的,layout是确定view在父容器上的摆放位置的,
    	draw是将view绘制在屏幕上的.

    	measure过程:
    		onMeasure方法中有个setMeasureDimension方法来设置view的宽高测量值,
    		而setMeasureSpec有一个getDefaultSize()方法作为参数,
    		一般情况下,我们只关注at_most和exactly两种情况,geDefaultSize()返回值就是
    		MeasureSpec中的SpecSize,而这个值就是View测量后的大小
    		还有一种未指定大小的情况,一般是系统内部测量过程,需要考虑view的背景等因素


    		以上view的测量过程,那么ViewGroup的绘制过程:
    		对于ViewGroup来说,除了完成自身的measure过程,还要去遍历调用子类的measure方法
    		,各个子元素在递归执行这个过程,ViewGroup是一个抽象的类,没有提供自己的
    		onMeasure方法,但是提供了一个measureChildren的方法,
    		measureChild方法的思想就是抽取子元素的布局参数,然后通过getChildMeasureSpec
    		来计算子元素的MeasureSpec,然后子元素调用measure方法进行测量,由于ViewGroup子类有不同的布局方式
    		导致他们的测量细节不一样,所以ViewGroup不能像View一样调用onMeasure()方法进行测量


    	考点:在Activity的生命周期中是没有办法正确获取View的宽高,原因是View没有测量完成
    		1. 在onWindowFocuschanged方法中获取------该方法view已经获取到了焦点了,代表已经初始化完毕
    		2. View.post()方法,通过消息队列来处理
    		3. 使用viewTreeObserver的回调来完成
    		4. 通过view.measure方式来测量,因为这个还跟父view的布局有关,因此测量结果不准

    	layout过程:
    		是ViewGroup用来确定子元素的位置.当ViewGroup的位置被确定了后,
    		在其onLayout中会遍历所有的子元素并调用子元素的layout方法,然后在layout方法
    		onLayout方法又会被调用,layout方法用来确定view的位置,而onLayout方法
    		用来确定子元素的位置.
    		大致流程:首先会调用setFrame方法来设定View的四个顶点位置,
    		然后View的四个顶点确定之后,那么View在父容器的位置就确定了,
    		接着就会调用onLayout方法.

    	draw过程:
    		一般分这么几步:
    		绘制背景,
    		绘制自己
    		绘制children
    		绘制装饰

    	 内部会调用dispatchDraw方法来传递绘制过程,dispatchDraw会遍历调用所有子元素的draw方法,
    	 draw事件就一层层传递下去.


   Android中的性能优化:

   		Android手机上,过多的使用内存,会容易导致oom,过多的使用cpu资源,会导致手机卡顿,甚至 导致anr.

   		可以从布局优化,绘制优化,内存泄露优化,响应速度优化,bitmap优化,线程优化进行一些性能上的优化

   		启动优化:影响启动速度的原因主要有高耗时的任务,复杂的View层级,主题和Activity的配置
   		 一般数据库的初始化,第三方框架的初始化,大文件读取,MultiDex的加载
   		 导致CPU阻塞
   		 比如说 Glide以及其他图片加载框架的初始化,这个过程都是比较耗时的.
   		 那么解决方式就是在Application的onCreate方法利用工作线程调用GlideApp.get();


   		1.布局优化: 
   		解决方式:
   		  1. 删除无用空间和层级
   		  2. 选择性能较低的ViewGroup,如RelativeLayout,如果可以选择RelativeLayout也可以使用
   		  LinearLayout,就优先使用LinearLayout,因为RelativeLayout功能较为复杂,占用更多CPU资源
   		  3. 使用标签<include>重用布局,<Merge>减少层级,<ViewStub>使用时才加载

   		 2.绘制优化:
   		 	绘制优化指的是view在ondraw方法中,避免执行执行复杂的操作以及创建对象(paint的实例化不要写在onDraw方法)

   		 3.内存优化: 内存泄露的优化

   		 4.响应优化:

   		 5.bitmap优化:
   		 	等比例压缩图片
   		 	不用的图片,及时recycle掉

   		 6. 线程优化
   		 	使用线程池来管理和复用线程,避免程序中有大量的线程,同时可以控制线程的
   		 	并发数,避免相互抢占资源而造成阻塞

   		 7.其他优化:
   		 	1.少用枚举,枚举占用空间大,建议使用静态常量
   		 	2.使用Android特有的数据结构,如SparseArray来代替HashMap
   		 	3.适当使用软引用和弱引用.
         
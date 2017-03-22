### 主要特性
####这是一套开发的基础框架 封装了一些基类和最常用工具类的封装
整个框架来源于网络上的一些整理和自己的理解 。都是很基础并且实用的东西
适合初学者的项目框架搭建使用

1.Activity与Fragment之间的一句代码互相转换
2.AppManager对Activity加载到堆栈中的管理 防止内存溢出
3.ActivityUtil对Activity跳转的封装 以及预设跳转动画
4.Fragment接收onActivityResult返回的数据
5.SharedPreferences的封装以及存入数据的AES加密
6.Log打印的封装

在开发过程中可能会遇到功能模块的调整，Activity需要转换为Fragment或者是Fragment需要转换为Activity。 在这个时候我们可能要改动大量的代码来完成， 如果我们在一开始就定义好Activity与Fragment的是要用方法是一致的， 这个时候我们就只需要替换我继承的父类就行了。 这个时候就需要定义一个好的BaseActivity与BaseFragment。
###一、Activity与Fragment之间的一句代码互相转换
#### Fragment代码
    public class TestFragment extends BaseFragment implements View.OnClickListener{
        Button TestOne, TestTwo;
    
        @Override
        public void onInit(Bundle savedInstanceState) {
            setContentView(R.layout.activity_test);
            initUI();
            initEvent();
        }
    
        private void initUI() {
            TestOne = getId(R.id.TestOne);
            TestTwo = getId(R.id.TestTwo);
        }
    
        private void initEvent() {
            TestOne.setOnClickListener(this);
            TestTwo.setOnClickListener(this);
        }
    
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.TestOne:
                    ActivityUtil.next(getActivity(), AlbumActivity.class);
                    break;
                case R.id.TestTwo:
                    ActivityUtil.next(getActivity(), AlbumActivity.class);
                    break;
                default:
                    break;
            }
        }
    }
#### Activity代码
    public class TestActivity extends BaseActivity implements View.OnClickListener {
        Button TestOne, TestTwo;
    
        @Override
        public void onInit(Bundle savedInstanceState) {
            setContentView(R.layout.activity_test);
            initUI();
            initEvent();
        }
    
        private void initUI() {
            TestOne = getId(R.id.TestOne);
            TestTwo = getId(R.id.TestTwo);
        }
    
        private void initEvent() {
            TestOne.setOnClickListener(this);
            TestTwo.setOnClickListener(this);
        }
    
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.TestOne:
                    ActivityUtil.next(getActivity(), AlbumActivity.class);
                    break;
                case R.id.TestTwo:
                    ActivityUtil.next(getActivity(), AlbumActivity.class);
                    break;
                default:
                    break;
            }
        }
    }
###结论
根据上面的代码可以看出 我们只需要通过改变继承父类就可以实现Activity与Fragment的转换 。
我们在Fragment里面实现了Activity的setContentView方法

onCreate(Bundle savedInstanceState)替换为onInit(Bundle savedInstanceState)

findViewById（int id）替换为getId（int id）并且不需要类型的转换


###二、AppManager对Activity加载到堆栈中的管理
这个类不必多说了 。
创建Activity的时候讲activity压入堆栈 结束的时候弹出堆栈删除堆栈数据。
方便管理已开启的Activity
做了一些优化 防止内存溢出

###三、ActivityUtil对Activity跳转的封装 以及预设跳转动画
    			//普通跳转
                ActivityUtil.next(getActivity(), AlbumActivity.class);
                //带参数跳转
                Bundle extras = new Bundle();
                extras.putString("key","传递数据");
                ActivityUtil.next(getActivity(), AlbumActivity.class,extras);
                //带返回值跳转
                ActivityUtil.next(getActivity(), AlbumActivity.class,extras,100);
                //跳转并结束当前Activity
                ActivityUtil.next(getActivity(), AlbumActivity.class,true);
                //跳转并修改默认切换动画
                ActivityUtil.next(getActivity(), AlbumActivity.class,R.anim.in_from_right,
                        R.anim.out_to_left);
                //返回
                ActivityUtil.goBack(getActivity());
                //带参返回
                ActivityUtil.goBackWithResult(getActivity(),100,extras); 
				.......


可以对跳转动画统一规范的管理

其它的我就不一一解说了  下载一份源码 自己琢磨一下吧   
欢迎提问 欢迎纠正 欢迎吐槽

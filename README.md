

# SpannableText

### 一、简述

#### 1.功能简介

SpannableText是一款超简单实用的可点击文本类，主要用于实现项目中《用户协议》和《隐私政策》的点击跳转；只需要传入目标文字以及目标链接，通过监听事件即可实现目标跳转，提高开发者的工作效率。

#### 2.函数说明

设置参数：文本内容，目标文字1，目标文字2，目标链接1，目标链接2

```java
setParam(String textContent, String target1, String target2, String url1, String url2)
```

支持设置单个目标参数

```java
setParam(String textContent, String target, String url)
```

设置目标文字样式：颜色ID，是否需要下划线【默认为蓝色，不显示下划线】

```java
setTargetStyle(int color, boolean underLine)
```

设置要显示的TextView控件

```java
setTextView(TextView textView)
```

ITextListener目标文字监听接口：回调点击的目标链接url

```java
onClickText(String url)
```



### 二、使用示例

#### 1.添加依赖

在项目的build.gradle中添加：

```java
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
}
```

在app目录下的build.gradle添加

```css
dependencies {
	        implementation 'com.github.JohnWa97:SpannableText:1.0.5'
	}
```

#### 2.在MainActivity中使用

```java
public class MainActivity extends AppCompatActivity implements ITextListener {

    private TextView textView;
    private String url1 = "https://www.baidu.com";
    private String url2 = "https://www.hao123.com/";
    private String content = "动物们是大自然怀抱中孕育出的精灵是相片中一个个灵动的倩影是和我们人类朝夕相处的好朋友。" +"正是因为有了各种各样的小动物，《用户协议》和《隐私政策》美丽的地球才显得生机勃勃。我的动物朋友小狗就是我记忆中最忠实的伙伴。";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.main_textView);
        initSpannableText();
    }

    //初始化文本类，设置参数
    private void initSpannableText() {
        SpannableText spannableText = new SpannableText(this,this);
        spannableText.setParam(content,"《用户协议》","《隐私政策》", url1, url2);
        spannableText.setTextView(textView);
    }

    //实现文本点击接口
    @Override
    public void onClickText(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
```

#### 3.效果示例

![](https://raw.githubusercontent.com/JohnWa97/SpannableText/master/GIF.gif)
